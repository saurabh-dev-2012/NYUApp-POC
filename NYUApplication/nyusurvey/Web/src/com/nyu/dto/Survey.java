package com.nyu.dto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nyu.util.DatabaseUtil;

public class Survey {
	
	private Long surveyId;
	private String type;
	private String title;
	private String message;
	private String resultLink;
	private String status;
	private Date expirationDate;
	private Long createdBy;
	private Long lastUpdatedBy;
	private Timestamp createdOn;
	private Timestamp lastUpdatedOn;

	public Survey() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResultLink() {
		return resultLink;
	}

	public void setResultLink(String resultLink) {
		this.resultLink = resultLink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		if(expirationDate == null || expirationDate.trim().isEmpty()){
			this.expirationDate = null;
		}else{
			try {
				this.expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
			} catch (ParseException e) {
				this.expirationDate = null;
			}
		}
		
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
	
	public String getExpDateString() {
		if(expirationDate != null){
			DateFormat sqlDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			return sqlDateFormat.format(expirationDate);
		}
		return "";
	}
	
	public List<SurveyUser> getSurveyUsers(){
		List<SurveyUser> surveyUsers = new ArrayList<SurveyUser>();
		try{
			DatabaseUtil dbUtil = DatabaseUtil.getDatabaseUtil();
			Connection conn = dbUtil.getConnection();
			String query = "select * from SurveyUser where surveyId="+this.getSurveyId();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				//set the props to intended SurveyUser
				SurveyUser user = new SurveyUser();
				user.setSurveyUserId(rs.getLong("surveyUserId"));
				user.setSurveyId(rs.getLong("surveyId"));
				user.setUserId(rs.getLong("userId"));
	            user.setStatus(rs.getString("SU_Status"));
	            user.setCreatedOn(rs.getTimestamp("createdOn"));
	            user.setLastUpdatedOn(rs.getTimestamp("lastUpdatedOn"));
	            user.setCreatedBy(rs.getLong("createdBy"));
	            user.setLastUpdatedBy(rs.getLong("lastUpdatedBy"));
	            
	            surveyUsers.add(user);
			}
		    //close result set
            rs.close();
         			
		}catch(SQLException ex){
			System.out.println("Exception : "+ex.getMessage());
		}
		return surveyUsers;
	}

}
