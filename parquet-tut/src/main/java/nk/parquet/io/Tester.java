package nk.parquet.io;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import parquet.hadoop.ParquetWriter;
import parquet.hadoop.metadata.CompressionCodecName;
import nk.parquet_tut.avro.Address;
import nk.parquet_tut.avro.Person;

public class Tester {
	private static final NumberFormat formatter = new DecimalFormat("#");
	public static void main(String[]args) throws IOException{
		MParquetWriter<Person> writer = new MParquetWriter<Person>(Person.SCHEMA$, ParquetWriter.DEFAULT_PAGE_SIZE,
				ParquetWriter.DEFAULT_BLOCK_SIZE, args[0], CompressionCodecName.SNAPPY);
		int numRecordToInsert = Integer.parseInt(args[1]);
		int i = 0;
		do{
			writer.write(generatePerson());
			System.out.print(String.format("\r[%s%%] done!!",calculatePercent(i,numRecordToInsert)));
		}while(i++<numRecordToInsert);
		writer.cleanUp();
		
		
	}
	
	public static String calculatePercent(float i ,float total){
		float percent = i/total*100;
		return formatter.format(percent);
	}
	
	public static Person generatePerson(){
		Random random = new Random();
		String fName = getString();
		String lName = getString();
		String dob = ""+random.nextInt(30)+random.nextInt(12)+random.nextInt(99);
		String sex = random.nextInt(1)==0?"M":"F";
		String city = getString();
		String state = getString();
		String counString = getString();
		String street = getString();
		int zip = random.nextInt(999999);
		
		Address address = Address.newBuilder().setStreet(street)
				.setCity(city).setState(state).setCountry(counString)
				.setZip(zip).build();
		Person person = Person.newBuilder().setFirstName(fName)
				.setLastName(lName).setDob(dob).setAddress(address)
				.setSex(sex).build();
		return person;
	}
	
	public static String getString(){
		Random random = new Random();
		return ""+(char)(random.nextInt(26)+65)+(char)(random.nextInt(26)+65)+(char)(random.nextInt(26)+65);
	}
}
