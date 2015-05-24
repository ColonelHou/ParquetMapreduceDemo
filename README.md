# ParquetMapreduceDemo

Demonstrates how to use parquet as input/output format in mapreduce with Avro as a data model.

Parquet is a columnar storage format with very efficient data encoding techniques. 
Avro is a compact serialization system.

#Object Models and Storage Formats
 Object Model - is in memory representation of data. Avro supports many object models including Avro, Thrift and Google          Protobuf.
 Storage Format - Serialized representation of data model. Parquet is a storage format which serializes data in columnar format.
 Avro and Thrift also have their own storage format which stores data in row oriented formats.
 Parquet Object Model Converters - are responsible for converting objectt model to parquet's data types and vice versa. Parquet  comes bundled with many converters such as avro,thrift, google protobuf etc. to encourage wide adoption of parquet.
 
 
 This sample mapreduce application reads a parquet file with avro object model Person and find the number of men in each     country. We are using Predicate pushdown to filter records on the basis of gender to include only men and Projection pushdown to reduce I/O.
 
 Preparation to run the Job:- 
  1. Build the project by using maven package to get the jar file 'parquet-tut-0.0.1-SNAPSHOT-jar-with-dependencies.jar'
  2. Generate sample dataset for the job by using nk.parquet.io.Tester class by providing the path and number of records to       generate as command-line arguments. This will create a parquet file on given path. This file you will use as input for       running the job. You can decide this number depending on how large file you need for testing.
  3. export HADOOP_CLASSPATH=parquet-tut-0.0.1-SNAPSHOT-jar-with-dependencies
  4. hadoop nk.parquet.mapreduce.nk.parquet.mapreduce.AgeGroupCounterPushdownJob generatedParquetFilePath dstPath

 Possible issues:-
 Parquet is a memory intensive storage format. You may get Heap spache error if your map/reduce task jvm does not have sufficient memory. In case you get this issue try increasing the task jvm heap size. In my case I used 300mb of heap space for both map & reduce jvms using the following:
 hadoop nk.parquet.mapreduce.nk.parquet.mapreduce.AgeGroupCounterPushdownJob -D mapreduce.map.java.opts=-Xmx300m -D mapreduce.reduce.java.opts=-Xmx300m generatedParquetFilePath dstPath

You may also need to increase your container memory size if this is less than you are providing for task jvms by setting the property mapreduce.map.memory.mb=1024 mapreduce.reduce.memory.mb=1024  for instance.
 
  
