package com.nyu.controller;

import java.io.IOException;
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
import com.nyu.dto.UserObject;

/**
 * Servlet implementation class EditSurvey
 */
@WebServlet("/EditSurvey")
public class EditSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String saveSurvey = request.getParameter("saveSurvey");
		String editSurvey = request.getParameter("editSurvey");
		String deleteSurvey = request.getParameter("deleteSurvey");
		String surveyId = request.getParameter("surveyId");
		String surveyAudience = request.getParameter("surveyAudience");
		String changeAudience = request.getParameter("changeAudience");
		String copySurvey = request.getParameter("copySurvey");
		
		if(saveSurvey != null && saveSurvey.trim().equalsIgnoreCase("true")){
			String type = request.getParameter("type");
			String title = request.getParameter("title");
			String message = request.getParameter("message");
			String resultLink = request.getParameter("resultLink");
			String status = request.getParameter("status");
			String expirationDate = request.getParameter("expirationDate");
			
			SurveyDAO surveyDao = new SurveyDAO();
			//set user properties from parameters 
			Survey survey = new Survey();
			survey.setType(type);
			survey.setTitle(title);
			survey.setMessage(message);
			survey.setResultLink(resultLink);
			survey.setStatus(status);
			survey.setExpirationDate(expirationDate);
			
			String saveMessage = "";
			Map result = null;
			//update Survey
			if(surveyId != null && !surveyId.trim().isEmpty()){
				survey.setSurveyId(new Long(surveyId));
				result = surveyDao.updateSurvey(survey);
				saveMessage = (String) result.get("message");
			}else{
				//add new survey
				result = surveyDao.addSurvey(survey);
				saveMessage = (String) result.get("message");
			}
			request.setAttribute("survey", survey);
			request.setAttribute("saveMessage", saveMessage);
			getServletContext().getRequestDispatcher("/addnewposttemplate.jsp").forward(request, response);

		}else if(deleteSurvey != null && deleteSurvey.trim().equalsIgnoreCase("true")){
			if(surveyId != null && !surveyId.trim().isEmpty()){
				SurveyDAO surveyDao = new SurveyDAO();
				boolean deteled = surveyDao.deleteSurvey(surveyId);
				List surveys = surveyDao.getAllSurveys();
				if(deteled){
					request.setAttribute("deleteMessage", "Servey deleted successfully");
				}else{
					request.setAttribute("deleteMessage", "Survey couldn't get deleted");
				}
				request.setAttribute("surveys", surveys);
				getServletContext().getRequestDispatcher("/surveytemplate.jsp").forward(request, response);
			}
		}else if(editSurvey != null && editSurvey.trim().equalsIgnoreCase("true")){
			if(surveyId != null && !surveyId.trim().isEmpty()){
				SurveyDAO surveyDao = new SurveyDAO();
				Survey survey = surveyDao.getSurvey(surveyId);
				if(survey != null){
					request.setAttribute("survey", survey);
				}
				getServletContext().getRequestDispatcher("/addnewposttemplate.jsp").forward(request, response);
			}
			
		}else if(copySurvey != null && copySurvey.trim().equalsIgnoreCase("true")){
			if(surveyId != null && !surveyId.trim().isEmpty()){
				SurveyDAO surveyDao = new SurveyDAO();
				Survey survey = surveyDao.getSurvey(surveyId);
				if(survey != null){
					request.setAttribute("survey", survey);
				}
				getServletContext().getRequestDispatcher("/addnewposttemplate.jsp").forward(request, response);
			}
			
		}else if((changeAudience != null && changeAudience.trim().equalsIgnoreCase("true")) && (surveyId != null && !surveyId.trim().isEmpty())){
			if(surveyAudience != null && surveyAudience.trim().equalsIgnoreCase("Import")){
				request.setAttribute("surveyId", surveyId);
				getServletContext().getRequestDispatcher("/uploadtemplate.jsp").forward(request, response);
			}else if(surveyAudience != null && surveyAudience.trim().equalsIgnoreCase("Existing Users")){
				request.setAttribute("surveyId", surveyId);
				request.setAttribute("users", new UserObjectDAO().getAllUsers());
				getServletContext().getRequestDispatcher("/existinguserstemplate.jsp").forward(request, response);
			}else if(surveyAudience != null && surveyAudience.trim().equalsIgnoreCase("Edit")){
				request.setAttribute("surveyId", surveyId);
				request.setAttribute("users", new SurveyUserDAO().getAllSurveyUsers(new Long(surveyId)));
				getServletContext().getRequestDispatcher("/currentaudiencetemplate.jsp").forward(request, response);
			}else if(surveyAudience != null && surveyAudience.trim().equalsIgnoreCase("Add User")){
				request.setAttribute("surveyId", surveyId);
				getServletContext().getRequestDispatcher("/editaudiencetemplate.jsp").forward(request, response);
			}else{
				getServletContext().getRequestDispatcher("/surveytemplate.jsp").forward(request, response);
			}
		}
		
		
	}

}
