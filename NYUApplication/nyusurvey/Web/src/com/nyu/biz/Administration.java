package com.nyu.biz;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.nyu.dao.SurveyUserDAO;
import com.nyu.dao.UserObjectDAO;
import com.nyu.dto.Survey;
import com.nyu.dto.SurveyUser;
import com.nyu.dto.UserObject;
import com.nyu.util.CSVFileReader;
import com.nyu.util.DatabaseUtil;

public class Administration {
	
	private DatabaseUtil dbUtil =null;

	public Administration() {
		// TODO Auto-generated constructor stub
		dbUtil = DatabaseUtil.getDatabaseUtil();
	}
	
	public void generatePassword(Long userId){
		
	}
	
	public void createSurveyLink(Long surveyId){
		
	}
	// send mail and notify user for registration.
	public void notifyUserForRegistration(UserObject user){
		
	}
	
	public String exportUsersToCSV(List users){
		String filePath = "";
		return filePath;
	}
	/* This method reads a csv file and stores a list of UserObjects in db.
	 *@Params: filePath(absolute path of the csv file) 
	 **/
	public void uploadListOfCSVUsers(String filePath){
		UserObjectDAO userDao = new UserObjectDAO();
		CSVFileReader.setFileHeaderMapping(UserObjectDAO.FILE_HEADER_MAPPING);
		List users = CSVFileReader.readCsvFile(filePath, "User");	
		userDao.addUsers(users);
	}
	
	private void addCSVSurveyUsers(Long surveyId, List users){
		SurveyUserDAO surveyUserDao = new SurveyUserDAO();
		ListIterator userIterator = users.listIterator();
		while(userIterator.hasNext()){
			UserObject sudoUser = (UserObject) userIterator.next();
			UserObject user = new UserObjectDAO().getUserByEmail(sudoUser.getEmail());
			SurveyUser surveyUser = new SurveyUser();
			surveyUser.setSurveyId(surveyId);
			surveyUser.setUserId(user.getUserId());
			surveyUser.setSurveyUserId(dbUtil.getNextSequenceIdLong("SurveyUser", "surveyUserId"));
			surveyUser.setStatus("Active");
			surveyUser.setCreatedBy(new Long(0));
			surveyUser.setLastUpdatedBy(new Long(0));
			surveyUser.setCreatedOn(null);
			surveyUser.setLastUpdatedOn(null);
			//add SurveyUser to DB
			surveyUserDao.addSurveyUser(surveyUser);
		}
	}
	
	public void addSurvey(Map fields){
		
	}
	
	public List fetchEmailHistoryList(){
		return null;
	}
	
	public List getIncompleteSurveyUsers(){
		return null;
	}
	
	public void sendReminders(){
		
	}
	
	public void uploadCSVUsersAsSurveyAudience(String filePath, Long surveyId){
		UserObjectDAO userDao = new UserObjectDAO();
		CSVFileReader.setFileHeaderMapping(SurveyUserDAO.FILE_HEADER_MAPPING);
		CSVFileReader.setSurveyId(surveyId);
		List surveyUsers = CSVFileReader.readCsvFile(filePath, "SurveyUser");	
		//userDao.addUsers(users);
		// add survey users in DB
		new SurveyUserDAO().addSurveyUsers(surveyUsers);
				
	}
	
	public void addExistingUsersToSurvey(Long surveyId, List users){
		SurveyUserDAO surveyUserDao = new SurveyUserDAO();
		ListIterator userIterator = users.listIterator();
		while(userIterator.hasNext()){
			UserObject user = (UserObject) userIterator.next();
			SurveyUser surveyUser = new SurveyUser();
			surveyUser.setSurveyId(surveyId);
			surveyUser.setUserId(user.getUserId());
			surveyUser.setSurveyUserId(dbUtil.getNextSequenceIdLong("SurveyUser", "surveyUserId"));
			surveyUser.setStatus("Active");
			surveyUser.setCreatedBy(new Long(0));
			surveyUser.setLastUpdatedBy(new Long(0));
			surveyUser.setCreatedOn(null);
			surveyUser.setLastUpdatedOn(null);
			//add SurveyUser to DB
			surveyUserDao.addSurveyUser(surveyUser);
		}
	}
}
