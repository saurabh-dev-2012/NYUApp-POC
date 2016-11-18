package com.nyu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nyu.biz.Administration;
import com.nyu.dao.SurveyDAO;
import com.nyu.dao.UserObjectDAO;
import com.nyu.dto.UserObject;

/**
 * Servlet implementation class UserSearch
 */
@WebServlet("/UserSearch")
public class UserSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			UserObjectDAO userDao = new UserObjectDAO();
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String status = request.getParameter("status");
			String sports = request.getParameter("sports");
			String searchFromExisting = request.getParameter("searchFromExisting");
			
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
			int page = 1;
			int recordsPerPage = 5;
			if(request.getParameter("page") != null && !(request.getParameter("page").trim().isEmpty()))
				page = Integer.parseInt(request.getParameter("page"));
			// search users by fields
			List users = userDao.getUsersByAndWithPaging(serachFields,(page-1)*recordsPerPage, recordsPerPage);
			int noOfRecords = userDao.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
			request.setAttribute("users", users);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			
			if(searchFromExisting != null && searchFromExisting.trim().equalsIgnoreCase("true")){
				getServletContext().getRequestDispatcher("/existinguserstemplate.jsp").forward(request, response);
			}else{
				getServletContext().getRequestDispatcher("/newsearchtemplate.jsp").forward(request, response);
			}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserObjectDAO userDao = new UserObjectDAO();
		String addToPosting = request.getParameter("addToPosting");
		String surveyId = request.getParameter("surveyId");
		String searchAll = request.getParameter("searchAll");
		if(addToPosting != null && addToPosting.trim().equalsIgnoreCase("true")){
			List<UserObject> users = userDao.getAllUsers();
			ListIterator<UserObject> userIterator = users.listIterator();
			if(surveyId != null && !surveyId.trim().isEmpty()){
				List<UserObject> existingUsers = new ArrayList<UserObject>();
				Administration admin = new Administration();
				while(userIterator.hasNext()){
					UserObject user = userIterator.next();
					String check = request.getParameter("select-"+user.getUserId());
					if(check != null && check.trim().equalsIgnoreCase("on")){
						existingUsers.add(user);
					}
				}
				admin.addExistingUsersToSurvey(new Long(surveyId), existingUsers);
			}
			request.setAttribute("surveys", new SurveyDAO().getAllSurveys());
			getServletContext().getRequestDispatcher("/surveytemplate.jsp").forward(request, response);
		}else if(searchAll != null && searchAll.trim().equalsIgnoreCase("true")){
			int page = 1;
			int recordsPerPage = 5;
			if(request.getParameter("page") != null && !(request.getParameter("page").trim().isEmpty()))
				page = Integer.parseInt(request.getParameter("page"));
			List users = userDao.viewAllUsers((page-1)*recordsPerPage, recordsPerPage);
			int noOfRecords = userDao.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
			request.setAttribute("users", users);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			
			getServletContext().getRequestDispatcher("/newsearchtemplate.jsp").forward(request, response);
		}else{
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String status = request.getParameter("status");
			String sports = request.getParameter("sports");
			String searchFromExisting = request.getParameter("searchFromExisting");
			
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
			
			if(searchFromExisting != null && searchFromExisting.trim().equalsIgnoreCase("true")){
				int page = 1;
				int recordsPerPage = 5;
				// search users by fields
				if(request.getParameter("page") != null && !(request.getParameter("page").trim().isEmpty()))
					page = Integer.parseInt(request.getParameter("page"));
				// search users by fields
				List users = userDao.getUsersByAndWithPaging(serachFields,(page-1)*recordsPerPage, recordsPerPage);
				int noOfRecords = userDao.getNoOfRecords();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				
				request.setAttribute("users", users);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("surveyId", surveyId);
				getServletContext().getRequestDispatcher("/existinguserstemplate.jsp").forward(request, response);
			}else{
				request.setAttribute("surveys", new SurveyDAO().getAllSurveys());
				getServletContext().getRequestDispatcher("/surveytemplate.jsp").forward(request, response);
			}
		}

	}
}
