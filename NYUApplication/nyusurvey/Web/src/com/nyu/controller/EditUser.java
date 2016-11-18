package com.nyu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nyu.dao.UserObjectDAO;
import com.nyu.dto.UserObject;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deleteUser = request.getParameter("deleteUser");
		String editUser = request.getParameter("editUser");
		String saveUser = request.getParameter("saveUser");
		String copyUser = request.getParameter("copyUser");
		String userId = request.getParameter("userId");
		if(deleteUser != null && deleteUser.trim().equalsIgnoreCase("true")){
			if(userId != null && !userId.trim().isEmpty()){
				UserObjectDAO userDao = new UserObjectDAO();
				boolean deteled = userDao.deleteUser(userId);
				List users = userDao.getAllUsers();
				if(deteled){
					request.setAttribute("deleteMessage", "User deleted successfully");
				}else{
					request.setAttribute("deleteMessage", "User couldn't get deleted");
				}
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/newsearchtemplate.jsp").forward(request, response);
			}
		}
		if(editUser != null && editUser.trim().equalsIgnoreCase("true")){
			if(userId != null && !userId.trim().isEmpty()){
				UserObjectDAO userDao = new UserObjectDAO();
				UserObject user = userDao.getUser(userId);
				if(user != null){
					request.setAttribute("user", user);
				}
				getServletContext().getRequestDispatcher("/editaddusertemplate.jsp").forward(request, response);
			}
		}
		if(saveUser != null && saveUser.trim().equalsIgnoreCase("true")){
			
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
				boolean updated = userDao.updateUser(user);
			}else{
				//add new user
				userDao.addUser(user);			
			}
			List users = userDao.getAllUsers();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/newsearchtemplate.jsp").forward(request, response);
		}
		if(copyUser != null && copyUser.trim().equalsIgnoreCase("true")){
			if(userId != null && !userId.trim().isEmpty()){
				UserObjectDAO userDao = new UserObjectDAO();
				UserObject user = userDao.getUser(userId);
				if(user != null){
					request.setAttribute("user", user);
					request.setAttribute("copyUser", true);
					request.setAttribute("userId", null);
					request.setAttribute("mail", "");
				}
				getServletContext().getRequestDispatcher("/editaddusertemplate.jsp").forward(request, response);
			}
		}
	}

}
