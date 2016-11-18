package com.nyu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nyu.dao.SurveyDAO;
import com.nyu.dao.UserObjectDAO;

/**
 * Servlet implementation class SurveySearch
 */
@WebServlet("/SurveySearch")
public class SurveySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveySearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String surveySearch = request.getParameter("surveySearch");
		if(surveySearch != null && surveySearch.equalsIgnoreCase("true")){
			SurveyDAO surveyDao = new SurveyDAO();

			// search users by fields
			List surveys = surveyDao.getAllSurveys();
			request.setAttribute("surveys", surveys);
			getServletContext().getRequestDispatcher("/surveytemplate.jsp").forward(request, response);
		}
	}

}
