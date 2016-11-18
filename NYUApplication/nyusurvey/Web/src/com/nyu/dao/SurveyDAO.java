package com.nyu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nyu.dto.Survey;
import com.nyu.dto.UserObject;
import com.nyu.util.DatabaseUtil;



public class SurveyDAO {
	
	private DatabaseUtil dbUtil = null;
	
	public SurveyDAO() {
		// TODO Auto-generated constructor stub
		dbUtil = DatabaseUtil.getDatabaseUtil();
	}
	
	public List getAllSurveys(){
		List surveys = new ArrayList();
		try {
			Connection conn = dbUtil.getConnection();
			String query = "select * from Survey";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Survey survey = new Survey();
				survey.setSurveyId(rs.getLong("surveyId"));
				survey.setType(rs.getString("S_Type"));
				survey.setTitle(rs.getString("title"));
				survey.setMessage(rs.getString("message"));
				survey.setResultLink(rs.getString("resultLink"));
				survey.setStatus(rs.getString("S_Status"));
				survey.setExpirationDate(rs.getString("expirationDate"));
                surveys.add(survey);
			}
			
		}catch(SQLException e) {
			System.out.println("Exception : "+e.getMessage());
		}
		return surveys;
	}
	
	public Map addSurvey(Survey survey){
		Map resultMap = new HashMap();
		try{
			Connection conn = dbUtil.getConnection();
			Long newSurveyId = dbUtil.getNextSequenceIdLong("Survey", "surveyId");
			Date expDate = survey.getExpirationDate();
			String expirationDate = expDate == null ? "null" : dbUtil.getSqlDateFormat().format(expDate);  
			String query  = "insert into Survey(surveyId, S_Type, title, message, resultLink, S_Status, expirationDate, createdBy, "
			+ "lastUpdatedBy, createdOn, lastUpdatedOn) values("+newSurveyId.longValue()+", '"+survey.getType()
			+"', '"+survey.getTitle()+"', '"+survey.getMessage()+"', '"+survey.getResultLink()+"','"+survey.getStatus()+"', "
			+(expirationDate.equalsIgnoreCase("null") ? expirationDate+", " :"'"+expirationDate+"', ")+"0"+", "+"0"
			+", "+"null"+", "+"null"+")";
			
			Statement stmt  = conn.createStatement();
			stmt.executeUpdate(query);
			resultMap.put("success", new Boolean(true));
			resultMap.put("message", "Post added successfully");
		}catch(SQLException exp){
			System.out.println("Exception while adding survey: "+exp.getMessage());
			resultMap.put("success", new Boolean(false));
			resultMap.put("message", "Error in adding post "+exp.getMessage());
		}
		return resultMap;
	}
	
	public boolean deleteSurvey(Survey survey){
		return true;
	}
	
	public Map updateSurvey(Survey survey){
		Map resultMap = new HashMap();
		try{
			Connection conn = dbUtil.getConnection();
			Date expDate = survey.getExpirationDate();
			String expirationDate = expDate == null ? "null" : dbUtil.getSqlDateFormat().format(expDate); 
			String query = "update Survey set S_Type='"+survey.getType()+"', title='"+survey.getTitle()+"', message='"+survey.getMessage()+"', resultLink='"+survey.getResultLink()+"', S_Status='"+survey.getStatus()
			+"', expirationDate="+(expirationDate.equalsIgnoreCase("null") ? expirationDate :"'"+expirationDate+"'")+" where surveyId="+survey.getSurveyId();
			
			Statement stmt  = conn.createStatement();
			int update = stmt.executeUpdate(query);
			resultMap.put("success", new Boolean(true));
			resultMap.put("message", "Post updated successfully");
		}catch(SQLException exp){
			System.out.println("Exception while updating user = "+exp.getMessage());
			resultMap.put("success", new Boolean(false));
			resultMap.put("message", "Error in updating "+exp.getMessage());
		}
		return resultMap;
	
	}
	
	public boolean deleteSurvey(String surveyId){
		try {
			Connection conn = dbUtil.getConnection();
			String query = "delete from Survey where surveyId="+surveyId;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Survey getSurvey(String surveyId){
		try {
			Connection conn = dbUtil.getConnection();
			String query = "select * from Survey where surveyId="+surveyId;
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			// Set survey properties
			Survey survey = new Survey();
			survey.setSurveyId(rs.getLong("surveyId"));
            survey.setType(rs.getString("S_Type"));
            survey.setTitle(rs.getString("title"));
            survey.setMessage(rs.getString("message"));
            survey.setResultLink(rs.getString("resultLink"));
            survey.setStatus(rs.getString("S_Status"));
            survey.setExpirationDate(rs.getDate("expirationDate"));

            //close the result set
            rs.close();
       
            return survey;
		} catch (SQLException e) {
			System.out.println("Exception : "+e.getMessage());
			return null;
		}

	}
	
}
