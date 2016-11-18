package com.nyu.dto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.nyu.util.DatabaseUtil;

public class SurveyUser {
	
	private Long surveyUserId;
	private Long surveyId;
	private Long userId;
	private String status;
	private String link = null;
	private Timestamp linkExpiration = null; 
	private Long createdBy;
	private Long lastUpdatedBy;
	private Timestamp createdOn;
	private Timestamp lastUpdatedOn;

	public SurveyUser() {
		// TODO Auto-generated constructor stub
	}

	public Long getSurveyUserId() {
		return surveyUserId;
	}

	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public UserObject getRelatedUser(){
		try{
			DatabaseUtil dbUtil = DatabaseUtil.getDatabaseUtil();
			Connection conn = dbUtil.getConnection();
			String query = "select * from User where userId="+this.getUserId();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			//set the props to intended user
			UserObject user = new UserObject();
			user.setUserId(rs.getLong("userId"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setN_Number(rs.getString("N_Number"));
            user.setGender(rs.getString("gender"));
            user.setSport1(rs.getString("sport1"));
            user.setSport2(rs.getString("sport2"));
            user.setSport3(rs.getString("sport3"));
            user.setType(rs.getString("U_Type"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setMobile(rs.getString("mobile"));
            user.setCollege(rs.getString("college"));
            user.setDepartment(rs.getString("department"));
            user.setYear(rs.getString("U_Year"));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setState(rs.getString("state"));
            user.setZip(rs.getString("zip"));
            user.setStatus(rs.getString("U_Status"));
            user.setDateAdded(rs.getDate("dateAdded"));
            //close result set
            rs.close();
            
            return user;
			
		}catch(SQLException ex){
			return null;
		}
		
	}
	
	public Survey getRelatedSurvey(){
		try{
			DatabaseUtil dbUtil = DatabaseUtil.getDatabaseUtil();
			Connection conn = dbUtil.getConnection();
			String query = "select * from Survey where surveyId="+this.getSurveyId();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			//set the props to intended survey
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
			
		}catch(SQLException ex){
			return null;
		}
		
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Timestamp getLinkExpiration() {
		return linkExpiration;
	}

	public void setLinkExpiration(Timestamp linkExpiration) {
		this.linkExpiration = linkExpiration;
	}	
}
