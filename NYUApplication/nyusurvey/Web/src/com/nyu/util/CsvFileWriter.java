package com.nyu.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.nyu.dto.UserObject;

public class CsvFileWriter {
	
	//Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";
	//CSV file header
	private static Object [] FILE_HEADER = {};
	
	private static List<Object> data = null;

	public static void writeCsvFile(String fileName, String type) {
		
		FileWriter fileWriter = null;
		
		CSVPrinter csvFilePrinter = null;
		
		//Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			
			//initialize FileWriter object
			fileWriter = new FileWriter(fileName);
			
			//initialize CSVPrinter object 
	        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
	        
	        //Create CSV file header
	        csvFilePrinter.printRecord(FILE_HEADER);
			
	        if(type.equalsIgnoreCase("User")){
	        	for (Object userData : data) {
	        		UserObject user = (UserObject) userData;
					List<String> userDataRecord = new ArrayList<String>();
					userDataRecord.add(user.getN_Number());
					userDataRecord.add(user.getGender());
					userDataRecord.add(user.getSport1());
		            userDataRecord.add(user.getSport2());
		            userDataRecord.add(user.getSport3());
					userDataRecord.add(user.getFirstName());
		            userDataRecord.add(user.getLastName());
		            userDataRecord.add(user.getType());
		            userDataRecord.add(user.getEmail());
		            userDataRecord.add(user.getPhone());
		            userDataRecord.add(user.getMobile());
		            userDataRecord.add(user.getCollege());
		            userDataRecord.add(user.getDepartment());
		            userDataRecord.add(user.getYear());
		            userDataRecord.add(user.getAddress());
		            userDataRecord.add(user.getCity());
		            userDataRecord.add(user.getState());
		            userDataRecord.add(user.getZip());
		            userDataRecord.add(user.getStatus());
		            
		            csvFilePrinter.printRecord(userDataRecord);
				}
	        }
	        System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
			}
		}
	}
	
	public static void setFileHeaderMapping(String[] fileHeaders){
		CsvFileWriter.FILE_HEADER = fileHeaders;
	}

	public static void setData(List<Object> data) {
		CsvFileWriter.data = data;
	}
	
	 
}
