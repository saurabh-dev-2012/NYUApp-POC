package com.nyu.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.nyu.dao.UserObjectDAO;
import com.nyu.dto.SurveyUser;
import com.nyu.dto.UserObject;

public class CSVFileReader {
	
	//CSV file header
    private static String [] FILE_HEADER_MAPPING = {};
    private static Long surveyId = null;
	
	public static List readCsvFile(String fileName, String type) {

		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		List dtos = new ArrayList();		
		//Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
     
        try {
            
            //initialize FileReader object
            fileReader = new FileReader(fileName);
            
            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            
            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            ListIterator<CSVRecord> recordsIterator = csvRecords.listIterator();
            //Read the CSV file records starting from the second record to skip the header
            while(recordsIterator.hasNext()) {
            	if(recordsIterator.nextIndex() == 0){
            		recordsIterator.next();
            	}else{
            		CSVRecord record = recordsIterator.next();
                	//Create a new object and fill its data
                	if(type.equalsIgnoreCase("User")){
                		UserObject user = new UserObject();
                		//DatabaseUtil dbUtil = DatabaseUtil.getDatabaseUtil();
                		user.setCsvFileHeaders();
                		//user.setUserId(dbUtil.getNextSequenceIdLong("User", "userId"));
                        user.setFirstName((String)record.get((String)user.getCsvFileHeaders().get("firstName")));
                        user.setLastName((String)record.get((String)user.getCsvFileHeaders().get("lastName")));
                        user.setN_Number((String)record.get((String)user.getCsvFileHeaders().get("N_Number")));
                        user.setGender((String)record.get((String)user.getCsvFileHeaders().get("gender")));
                        user.setSport1((String)record.get((String)user.getCsvFileHeaders().get("sport1")));
                        user.setSport2((String)record.get((String)user.getCsvFileHeaders().get("sport2")));
                        user.setSport3((String)record.get((String)user.getCsvFileHeaders().get("sport3")));
                        user.setType((String)record.get((String)user.getCsvFileHeaders().get("type")));
                        user.setEmail((String)record.get((String)user.getCsvFileHeaders().get("email")));
                        user.setPhone((String)record.get((String)user.getCsvFileHeaders().get("phone")));
                        user.setMobile((String)record.get((String)user.getCsvFileHeaders().get("mobile")));
                        user.setCollege((String)record.get((String)user.getCsvFileHeaders().get("college")));
                        user.setDepartment((String)record.get((String)user.getCsvFileHeaders().get("department")));
                        user.setYear((String)record.get((String)user.getCsvFileHeaders().get("year")));
                        user.setAddress((String)record.get((String)user.getCsvFileHeaders().get("address")));
                        user.setCity((String)record.get((String)user.getCsvFileHeaders().get("city")));
                        user.setState((String)record.get((String)user.getCsvFileHeaders().get("state")));
                        user.setZip((String)record.get((String)user.getCsvFileHeaders().get("zip")));
                        user.setStatus((String)record.get((String)user.getCsvFileHeaders().get("status")));
                        dtos.add(user);
                        
                	}else if(type.equalsIgnoreCase("SurveyUser")){
                		UserObject user = new UserObjectDAO().getUserByEmail(record.get("Email"));
                		if(user != null){
                			SurveyUser surveyUser = new SurveyUser();
                            surveyUser.setSurveyId(CSVFileReader.surveyId);
                            surveyUser.setUserId(user.getUserId());
                            surveyUser.setStatus(record.get("Status"));
                            surveyUser.setLink(record.get("Link"));
                            String linkExpStr = record.get("Link Expiration");
                            Timestamp newExpiration = null;
                            if(linkExpStr != null && !linkExpStr.trim().isEmpty()){
                            	java.util.Date temp = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(linkExpStr);
                            	newExpiration = new Timestamp(temp.getTime());
                            }
                            surveyUser.setLinkExpiration(newExpiration);
                            surveyUser.setCreatedBy(new Long(0));
                			surveyUser.setLastUpdatedBy(new Long(0));
                			surveyUser.setCreatedOn(null);
                			surveyUser.setLastUpdatedOn(null);
                            dtos.add(surveyUser);
                		}
                  	}
            	}
           }
            
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
        return dtos;
	}
	
	public static void setFileHeaderMapping(String[] fileHeaders){
		CSVFileReader.FILE_HEADER_MAPPING = fileHeaders;
	}

	public static void setSurveyId(Long surveyId) {
		CSVFileReader.surveyId = surveyId;
	}	

}
