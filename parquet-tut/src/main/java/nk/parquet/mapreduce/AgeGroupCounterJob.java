package nk.parquet.mapreduce;

import java.io.IOException;

import nk.parquet_tut.avro.Person;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import parquet.avro.AvroParquetInputFormat;
import parquet.example.data.Group;
import parquet.example.data.simple.SimpleGroupFactory;
import parquet.hadoop.example.ExampleOutputFormat;
import parquet.hadoop.example.GroupWriteSupport;
import parquet.hadoop.util.ContextUtil;
import parquet.schema.MessageTypeParser;

public class AgeGroupCounterJob extends Configured implements Tool{
	private final static String writeSchema = "message stockavg { \n"+
			"required binary country;\n"+
			"required int32 count;\n"+
			"}";
	
	public static class MM extends Mapper<Void, Person, Text, Text>{
		@Override
		public void map(Void key, Person value,Context context) throws IOException, InterruptedException{
			if(value.getSex().toString().equalsIgnoreCase("M")){
				context.write(new Text(value.getAddress().getCountry().toString()), new Text(value.getFirstName().toString()));
			}
		}
	}
	
	public static class MR extends Reducer<Text, Text, Void, Group>{
		private SimpleGroupFactory factory;
		
		@Override
		public void setup(Context context){
			factory  = new SimpleGroupFactory(GroupWriteSupport.getSchema(ContextUtil.getConfiguration(context)));
		}
		
		@Override
		public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException{
			int count = 0;
			for(Text val:value)
				++count;
			Group group = factory.newGroup().append("country",key.toString()).
					append("count",count);
			context.write(null,group);
		}
	}

	public int run(String[] args) throws Exception {
		Job job   = new Job(getConf());
		job.setJobName(this.getClass().getSimpleName());
		job.setJarByClass(getClass());
		
		job.setInputFormatClass(AvroParquetInputFormat.class);
		AvroParquetInputFormat.setInputPaths(job, new Path(args[0]));
		
		job.setOutputFormatClass(ExampleOutputFormat.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		ExampleOutputFormat.setSchema(job, MessageTypeParser.parseMessageType(writeSchema));
		
		job.setMapperClass(MM.class);
		job.setReducerClass(MR.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		int r = job.waitForCompletion(true)?0:1;
		return r;
	}
	
	public static void main(String[]args) throws Exception{
	
	        int r = ToolRunner.run(new AgeGroupCounterJob(),args);
		System.exit(r);
	}
}
