package nk.parquet.mapreduce;

import java.io.IOException;
import java.util.List;

import nk.parquet_tut.avro.Person;

import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.google.common.collect.Lists;

import parquet.avro.AvroParquetInputFormat;
import parquet.column.ColumnReader;
import parquet.example.data.Group;
import parquet.example.data.simple.SimpleGroupFactory;
import parquet.filter.ColumnPredicates;
import parquet.filter.ColumnRecordFilter;
import parquet.filter.RecordFilter;
import parquet.filter.UnboundRecordFilter;
import parquet.hadoop.example.ExampleOutputFormat;
import parquet.hadoop.example.GroupWriteSupport;
import parquet.hadoop.util.ContextUtil;
import parquet.schema.MessageTypeParser;

public class AgeGroupCounterPushdownJob extends Configured implements Tool {
	private final static String writeSchema = "message stockavg { \n"+
			"required binary country;\n"+
			"required int32 count;\n"+
			"}";
	public static class MM extends Mapper<Void, Person, Text, Text>{
		@Override
		public void map(Void key, Person value, Context context) throws IOException, InterruptedException{
			if(value!=null){
				context.write(new Text(value.getAddress().getCountry().toString()), new Text(value.getFirstName().toString()));
			}
		}
	}
	
	public static class MR extends Reducer<Text, Text, Void, Group>{
		private SimpleGroupFactory factory;
		
		@Override
		public void setup(Context context){
			factory = new SimpleGroupFactory(GroupWriteSupport.getSchema(ContextUtil.getConfiguration(context)));
		}
		@Override
		public void reduce(Text key,Iterable<Text>value, Context context) throws IOException, InterruptedException{
			int count = 0;
			for(Text text : value)
				++count;
			Group group = factory.newGroup().append("symbol", key.toString()).
					append("count",count);
			context.write(null,group);
		}
	}
	
	
	public static class GenderFilter implements UnboundRecordFilter{
		private UnboundRecordFilter filter;
		public GenderFilter(){
			filter = ColumnRecordFilter.column("sex", ColumnPredicates.equalTo("M"));
		}
		public RecordFilter bind(Iterable<ColumnReader> arg0) {
			 return filter.bind(arg0);
		}
		
	}
	
	public int run(String[] args) throws Exception {
		Job job = new Job(getConf());
		job.setJobName(getClass().getSimpleName());
		job.setJarByClass(AgeGroupCounterPushdownJob.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setInputFormatClass(AvroParquetInputFormat.class);
		AvroParquetInputFormat.addInputPath(job, new Path(args[0]));
		AvroParquetInputFormat.setUnboundRecordFilter(job, GenderFilter.class);
		
		Schema projectedSchema = Schema.createRecord(Person.SCHEMA$.getName(),Person.SCHEMA$.getDoc(), Person.SCHEMA$.getNamespace(),
				false);
		List<Schema.Field> fields = Lists.newArrayList();
		for(Schema.Field field : Person.SCHEMA$.getFields()){
			if(field.name().equals("firstName")||"address".equals(field.name())){
				System.out.println("adding: "+field.name());
				fields.add(new Schema.Field(field.name(),field.schema(),field.doc(),field.defaultValue(),field.order()));
			}
		}
		
		projectedSchema.setFields(fields);
		AvroParquetInputFormat.setRequestedProjection(job, projectedSchema);
		
		job.setOutputFormatClass(ExampleOutputFormat.class);
		ExampleOutputFormat.setSchema(job, MessageTypeParser.parseMessageType(writeSchema));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(MM.class);
		job.setReducerClass(MR.class);
		return job.waitForCompletion(true)?0:1;
	}
	

	public static void main(String[]args) throws Exception{
		long start = System.currentTimeMillis();
		int r = ToolRunner.run(new AgeGroupCounterPushdownJob(),args);
		System.out.println("Time taken: "+(System.currentTimeMillis()-start)+" Ms");
		System.exit(r);
	}

}
