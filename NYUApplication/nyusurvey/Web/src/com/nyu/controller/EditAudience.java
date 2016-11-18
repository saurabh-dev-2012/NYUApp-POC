package com.nyu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nyu.dao.SurveyDAO;
import com.nyu.dao.SurveyUserDAO;
import com.nyu.dao.UserObjectDAO;
import com.nyu.dto.Survey;
import com.nyu.dto.SurveyUser;
import com.nyu.dto.UserObject;
import com.nyu.util.DatabaseUtil;

/**
 * Servlet implementation class EditAudience
 */
@WebServlet("/EditAudience")
public class EditAudience extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAudience() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deleteAudience = request.getParameter("deleteAudience");
		String editAudience = request.getParameter("editAudience");
		String saveAudience = request.getParameter("saveAudience");
		String editDone = request.getParameter("editDone");
		String searchAudience = request.getParameter("searchAudience");
		String userId = request.getParameter("userId");
		String surveyId = request.getParameter("surveyId");
		if(deleteAudience != null && deleteAudience.trim().equalsIgnoreCase("true")){
			if((userId != null && !userId.trim().isEmpty()) && (surveyId != null && !surveyId.trim().isEmpty())){
				SurveyUserDAO surveyUserDao = new SurveyUserDAO();
				boolean deteled = surveyUserDao.deleteSurveyUser(new Long(surveyId), new Long(userId));
				//get all survey users after delete
				List<UserObject> users = surveyUserDao.getRelatedUsers(surveyUserDao.getSurveyUsers(new Long(surveyId)));
				if(deteled){
					request.setAttribute("deleteMessage", "User deleted successfully");
				}else{
					request.setAttribute("deleteMessage", "User couldn't get deleted");
				}
				request.setAttribute("users", users);
				request.setAttribute("surveyId", surveyId);
				getServletContext().getRequestDispatcher("/currentaudiencetemplate.jsp").forward(request, response);
			}
		}
		if(editAudience != null && editAudience.trim().equalsIgnoreCase("true")){
			if(userId != null && !userId.trim().isEmpty()){
				UserObjectDAO userDao = new UserObjectDAO();
				UserObject user = userDao.getUser(userId);
				if(user != null){
					request.setAttribute("user", user);
					request.setAttribute("surveyId", surveyId);
				}
				getServletContext().getRequestDispatcher("/editaudiencetemplate.jsp").forward(request, response);
			}
		}
		if(saveAudience != null && saveAudience.trim().equalsIgnoreCase("true")){
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String type = request.getParameter("type");
			String email = request.getParameter("email");
			String sports = request.getParameter("sports");
			String mobile = request.getParameter("mobile");
			String college = request.getParameter("college");
			String year = request.getParameter("year");
			String address = request.getParameter("address");
			String status = request.getParameter("status");
			String sport1 = request.getParameter("sport1");
			String sport2 = request.getParameter("sport2");
			String sport3 = request.getParameter("sport3");
			String N_Number = request.getParameter("N_Number");
			
			UserObjectDAO userDao = new UserObjectDAO();
			//set user properties from parameters 
			UserObject user = new UserObject();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setType(type);
			user.setEmail(email);
			user.setSport1(sport1);
			user.setSport2(sport2);
			user.setSport3(sport3);
			user.setMobile(mobile);
			user.setCollege(college);
			user.setYear(year);
			user.setAddress(address);
			user.setStatus(status);
			user.setN_Number(N_Number);
			if(userId != null && !userId.trim().isEmpty()){
				user.setUserId(new Long(userId));
			}
			
			//update user
			if(userId != null && !userId.trim().isEmpty()){
				SurveyUserDAO surveyUserDao = new SurveyUserDAO();
				List<UserObject> users = surveyUserDao.getRelatedUsers(surveyUserDao.getSurveyUsers(new Long(surveyId)));
				
				boolean updated = userDao.updateUser(user);
				request.setAttribute("users", users);
				request.setAttribute("surveyId", surveyId);
				getServletContext().getRequestDispatcher("/currentaudiencetemplate.jsp").forward(request, response);
			}else{
				//add new user
				boolean added = userDao.addUser(user);
				if(added){
					//add new survey user
					DatabaseUtil dbUtil = DatabaseUtil.getDatabaseUtil();
					SurveyUser newSurveyUser = new SurveyUser();
					newSurveyUser.setSurveyUserId(dbUtil.getNextSequenceIdLong("SurveyUser", "surveyUserId"));
					newSurveyUser.setSurveyId(new Long(surveyId));
					newSurveyUser.setUserId(user.getUserId());
					newSurveyUser.setStatus("Active");
					newSurveyUser.setCreatedBy(new Long(0));
					newSurveyUser.setLastUpdatedBy(new Long(0));
					newSurveyUser.setCreatedOn(null);
					newSurveyUser.setLastUpdatedOn(null);
					
					new SurveyUserDAO().addSurveyUser(newSurveyUser);
					
				}else{
					request.setAttribute("headerMessage", "Survey User could not be added");
				}
				request.setAttribute("surveys", new SurveyDAO().getAllSurveys());
				getServletContext().getRequestDispatcher("/surveytemplate.jsp").forward(request, response);
			}
			
		}
		if(editDone != null && editDone.trim().equalsIgnoreCase("true")){
			List<Survey> surveys = new SurveyDAO().getAllSurveys();
			request.setAttribute("surveys", surveys);
			getServletContext().getRequestDispatcher("/surveytemplate.jsp").forward(request, response);
		}
		if(searchAudience != null && searchAudience.trim().equalsIgnoreCase("true")){
			if(surveyId != null && !surveyId.trim().isEmpty()){
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String status = request.getParameter("status");
				String sports = request.getParameter("sports");
							
				Map serachFields = new HashMap();
				if(firstName != null && !firstName.trim().isEmpty()){
					serachFields.put("firstName", firstName);
				}if(lastName != null && !lastName.trim().isEmpty()){
					serachFields.put("lastName", lastName);
				}if(email != null && !email.trim().isEmpty()){
					serachFields.put("email", email);
				}if(status != null && !status.trim().isEmpty()){
					serachFields.put("U_Status", status);
				}if(sports != null && !sports.trim().isEmpty()){
					serachFields.put("sport1", sports);
				}
				// search users by fields
				List<UserObject> users = new UserObjectDAO().getUsersByAnd(serachFields);
				
				SurveyUserDAO surveyUserDao = new SurveyUserDAO();
				List<UserObject> surveyUsers = surveyUserDao.getRelatedUsers(surveyUserDao.getSurveyUsers(new Long(surveyId)));
				List<UserObject> searchedUsers = new ArrayList<UserObject>();
				// compare users and show result
				for(UserObject user: users){
					for(UserObject surveyUser: surveyUsers){
						if(user.equals(surveyUser)){
							searchedUsers.add(surveyUser);
						}
					}
				}
				request.setAttribute("surveyId", surveyId);
				request.setAttribute("users", searchedUsers);
			}
			
			getServletContext().getRequestDispatcher("/currentaudiencetemplate.jsp").forward(request, response);
			
		}
	}

}
