package nk.parquet.io;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.CompressionCodec;

import parquet.avro.AvroParquetWriter;
import parquet.hadoop.metadata.CompressionCodecName;

public class MParquetWriter<T extends IndexedRecord> {
	private AvroParquetWriter<T> writer;
	
	public MParquetWriter(Schema schema,int pageSize,int bufferSize,String path,CompressionCodecName codec) throws IOException{
		writer = new AvroParquetWriter<T>(new Path(path), schema,codec,bufferSize,pageSize);
	}
	
	public void write(T data) throws IOException{
		writer.write(data);
	}
	
	public void cleanUp() throws IOException{
		writer.close();
	}
	 
}
