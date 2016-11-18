package com.nyu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.nyu.dto.LoginUser;
import com.nyu.util.DatabaseUtil;


public class LoginUserDAO {

	private DatabaseUtil dbUtil = null;
	
	public LoginUserDAO() {
		// TODO Auto-generated constructor stub
		dbUtil = DatabaseUtil.getDatabaseUtil();
	}
	
	public List getAllLoginUsers(){
		
		return null;
	}
	
	public boolean addLoginUser(LoginUser loginUser){
		try{
			Connection conn = this.dbUtil.getConnection();
			Date registerDate = loginUser.getRegisterDate();
			String registerDateString = (registerDate != null ? "'"+dbUtil.getSqlDateFormat().format(registerDate)+"'": null);
			String query  = "insert into LoginUser(User_userId, password, isActive, registerDate, createdBy, lastUpdatedBy, createdOn, "
					+ "lastUpdatedOn) values("+loginUser.getUserId()+", '"+loginUser.getPassword()+"', "+loginUser.isActive()
					+", "+registerDateString+", "+"0, "+"0"+", "+"null"+", "+"null"+")";
							
			Statement stmt  = conn.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException ex){
			System.out.println("Exception while adding login user: "+ex.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean deleteUser(LoginUser loginUser){
		return true;
	}
	
	public boolean updateUser(LoginUser loginUser){
		return true;
	}
	
	public boolean makeUserInactive(LoginUser loginUser){
		return true;
	}
}
