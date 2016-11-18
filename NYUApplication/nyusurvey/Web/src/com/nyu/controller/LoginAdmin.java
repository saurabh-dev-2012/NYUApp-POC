package com.nyu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nyu.dao.UserObjectDAO;
import com.nyu.dto.LoginUser;
import com.nyu.dto.UserObject;

/**
 * Servlet implementation class LoginAdmin
 */
@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		boolean authenticated = false;
		if((userName != null && !userName.trim().isEmpty()) && (password != null && !password.trim().isEmpty())){
			UserObject user = new UserObjectDAO().getUserByEmail(userName);
			if(user != null){
				LoginUser loginUser = user.getRelativeLoginUser();
				if(loginUser != null && loginUser.getPassword().equalsIgnoreCase(password)){
					authenticated = true;
				}
			}
			if(authenticated){
				HttpSession session = request.getSession();
				session.setAttribute("activeUser", user);
				getServletContext().getRequestDispatcher("/UserSearch?searchAll=true").forward(request, response);
			}else{
				request.setAttribute("headerMessage", "User is not authorized");
				getServletContext().getRequestDispatcher("/logintemplate.jsp").forward(request, response);
			}
	
		}else{
			request.setAttribute("headerMessage", "Please enter Username and Password.");
			getServletContext().getRequestDispatcher("/logintemplate.jsp").forward(request, response);
		}
		
	}

}
