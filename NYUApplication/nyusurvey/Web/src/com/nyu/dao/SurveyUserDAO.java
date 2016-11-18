package com.nyu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.nyu.dto.SurveyUser;
import com.nyu.dto.UserObject;
import com.nyu.util.DatabaseUtil;



public class SurveyUserDAO {

	private DatabaseUtil dbUtil = null;
	
	public static final String [] FILE_HEADER_MAPPING = {"Response ID", "Last Name", "First Name", "External Data Reference","Email","Status"
			, "End Date", "Link", "Link Expiration"
	};
	
	public SurveyUserDAO() {
		// TODO Auto-generated constructor stub
		dbUtil = DatabaseUtil.getDatabaseUtil();
	}
	
	public List<UserObject> getAllSurveyUsers(Long surveyId){
		return this.getRelatedUsers(this.getSurveyUsers(surveyId));
	}
	
	public boolean addSurveyUser(SurveyUser surveyUser){
		try{
			Connection conn = dbUtil.getConnection();
			Timestamp linkExp = surveyUser.getLinkExpiration();
			String linkExpStr = linkExp != null ? "'"+dbUtil.getSqlDateTimeFormat().format(new Date(linkExp.getTime()))+"'" : null;
			String query  = "insert into SurveyUser(surveyUserId, surveyId, userId, SU_Status, SU_Link, linkExpiration, createdBy, lastUpdatedBy, createdOn, lastUpdatedOn) "
			+ "values("+surveyUser.getSurveyUserId()+", "+surveyUser.getSurveyId()+", "+surveyUser.getUserId()+", '"+surveyUser.getStatus()+"', '"+surveyUser.getLink()+"', "+linkExpStr+", "+surveyUser.getCreatedBy()
			+", "+surveyUser.getLastUpdatedBy()+", "+surveyUser.getCreatedOn()+", "+surveyUser.getLastUpdatedOn()+")";
			
			Statement stmt  = conn.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException exp){
			System.out.println("Exception while adding survey user: "+exp.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean deleteSurveyUser(SurveyUser surveyUser){
		return true;
	}
	
	public boolean updateSurveyUser(SurveyUser surveyUser){
		return true;
	}
	
	public List<UserObject> getRelatedUsers(List<SurveyUser> surveyUsers){
		List<UserObject> users = new ArrayList<UserObject>();
		ListIterator<SurveyUser> iterator = surveyUsers.listIterator();
		while(iterator.hasNext()){
			SurveyUser surveyUser = iterator.next();
			users.add(surveyUser.getRelatedUser());
		}
		return users;
	}
	
	public boolean deleteSurveyUser(Long surveyId, Long userId){
		try{
			Connection conn = dbUtil.getConnection();
			String query = "delete from SurveyUser where surveyId="+surveyId+" and userId="+userId;
			Statement stmt  = conn.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException exp){
			System.out.println("Exception while deleting survey user : "+exp.getMessage());
			return false;
		}
		return true;
	}
	
	public List<SurveyUser> getSurveyUsers(Long surveyId){
		List<SurveyUser> surveyUsers = new ArrayList<SurveyUser>();
		try{
			DatabaseUtil dbUtil = DatabaseUtil.getDatabaseUtil();
			Connection conn = dbUtil.getConnection();
			String query = "select * from SurveyUser where surveyId="+surveyId;
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

	public void addSurveyUsers(List surveyUsers) {
		// TODO Auto-generated method stub
		ListIterator<SurveyUser> surveyUserIterator = surveyUsers.listIterator();
		while(surveyUserIterator.hasNext()){
			SurveyUser surveyUser = surveyUserIterator.next();
			surveyUser.setSurveyUserId(dbUtil.getNextSequenceIdLong("SurveyUser", "surveyUserId"));
			this.addSurveyUser(surveyUser);
		}
	}
}
