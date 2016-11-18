package com.nyu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import com.nyu.dto.LoginUser;
import com.nyu.dto.UserObject;
import com.nyu.util.DatabaseUtil;
import com.nyu.util.NYUUtils;

public class UserObjectDAO {
	
	private DatabaseUtil dbUtil = null;
	private int noOfRecords = 0;
	public static final String [] FILE_HEADER_MAPPING = {"N-number", "Gender", "Sport1", "Sport2","Sport3","Firstname "
	,"Lastname", "Type", "Email", "Phone Number", "Mobile Number", "College", "Department", "Year", "Address", "City", "State", "Zip", "Status"
	};

	public UserObjectDAO() {
		// TODO Auto-generated constructor stub
		dbUtil = DatabaseUtil.getDatabaseUtil();
	}
	//gets a list of all records in User entity
	public List getAllUsers(){
		List users = new ArrayList();
		try {
			Connection conn = dbUtil.getConnection();
			String query = "select * from User";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				UserObject user = new UserObject();
				user.setUserId(rs.getLong("userId"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setN_Number(rs.getString("N_Number"));
                user.setGender(rs.getString("gender"));
                user.setSport1(rs.getString("sport1"));
                user.setSport2(rs.getString("sport2"));
                user.setSport3(rs.getString("sport3"));
                user.setType(rs.getString("U_Type"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setMobile(rs.getString("mobile"));
                user.setCollege(rs.getString("college"));
                user.setDepartment(rs.getString("department"));
                user.setYear(rs.getString("U_Year"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setStatus(rs.getString("U_Status"));
                user.setDateAdded(rs.getDate("dateAdded"));
                users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Exception : "+e.getMessage());
			
		}
		return users;		
	}
	
	public boolean addUser(UserObject user){
		try {
			Connection conn = dbUtil.getConnection();
			Long newUserId = dbUtil.getNextSequenceIdLong("User", "userId");
			user.setUserId(newUserId);
			String query  = "insert into User(userId, email, N_Number, firstName, lastName, U_Type, gender, "
			+ "sport1, sport2, sport3, phone, mobile, college, department, U_Year, address, city, state, zip, "
			+ "U_Status, dateAdded, createdBy, lastUpdatedBy, createdOn, lastUpdatedOn) values("+newUserId.longValue()+", '"+user.getEmail()
			+"', '"+user.getN_Number()+"', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getType()+"', '"+user.getGender()
			+"', '"+user.getSport1()+"', '"+user.getSport2()+"', '"+user.getSport3()+"', '"+user.getPhone()+"', '"+user.getMobile()
			+"', '"+user.getCollege()+"', '"+user.getDepartment()+"', "+user.getYear()+", '"+user.getAddress()+"', '"+user.getCity()+"', '"+user.getState()
			+"', '"+user.getZip()+"', '"+user.getStatus()+"', '"+dbUtil.getSqlDateFormat().format(new Date())+"', "+"0"+", "+"0"
			+", "+"null"+", "+"null"+")";
					
			Statement stmt  = conn.createStatement();
			stmt.executeUpdate(query);
			
			//add corresponding inactive login user
			String newPassword = NYUUtils.generateRandomPassword();
			LoginUserDAO logUserDao = new LoginUserDAO();
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(newUserId);
			loginUser.setPassword(newPassword);
			loginUser.setActive(false);// will be active after registration.
			loginUser.setRegisterDate(null);
			loginUser.setCreatedBy(new Long(0));
			loginUser.setLastUpdatedBy(new Long(0));
			loginUser.setCreatedOn(null);
			loginUser.setLastUpdatedOn(null);
			logUserDao.addLoginUser(loginUser);
			
			//send mail to user for registration
			String textMessageToUser = NYUUtils.messageForNewUser+" Password: "+newPassword;
			NYUUtils.sendMail(user.getEmail(), "New User Registration", textMessageToUser); 
			
		} catch (SQLException e) {
			System.out.println("Exception while adding user: "+e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean deleteUser(UserObject user){
		return true;
	}
	
	public boolean updateUser(UserObject user){
		try{
			Connection conn = dbUtil.getConnection();
			String query = "update User set email='"+user.getEmail()+"', N_Number='"+user.getN_Number()+"', firstName='"+user.getFirstName()+"', lastName='"+user.getLastName()
			+"', U_Type='"+user.getType()+"', mobile='"+user.getMobile()+"', college='"+user.getCollege()+"', U_Year="+user.getYear()+", address='"+user.getAddress()
			+"', U_Status='"+user.getStatus()+"', sport1='"+user.getSport1()+"', sport2='"+user.getSport2()+"', sport3='"+user.getSport3()+"' where userId="+user.getUserId();
			Statement stmt  = conn.createStatement();
			int update = stmt.executeUpdate(query);
		}catch(SQLException exp){
			System.out.println("Exception while updating user = "+exp.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean makeUserInactive(UserObject user){
		return true;
	}
	
	public void addUsers(List users){
		ListIterator<UserObject> userIterator = users.listIterator();
		while(userIterator.hasNext()){
			UserObject user = userIterator.next();
			this.addUser(user);
		}
	}
	
	public List getUsersByAnd(Map<String, String> fields){
		List users = new ArrayList();
		try {
			Connection conn = dbUtil.getConnection();
			String query = "";
			Iterator entries = fields.entrySet().iterator();
			if(fields.entrySet().size() > 0){
				query += "select * from User where ";
			}else{
				query += "select * from User";
			}
			int count = 0;
			while (entries.hasNext()) {
			  Entry thisEntry = (Entry) entries.next();
			  count++;
			  String key = (String) thisEntry.getKey();
			  String value = (String) thisEntry.getValue();
			  if(count <= (fields.entrySet().size() - 1)){
				  query += key+"='"+value+"'"+" and ";
			  }else{
				  query += key+"='"+value+"'";
			  }
			}
					
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				UserObject user = new UserObject();
				user.setUserId(rs.getLong("userId"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setN_Number(rs.getString("N_Number"));
                user.setGender(rs.getString("gender"));
                user.setSport1(rs.getString("sport1"));
                user.setSport2(rs.getString("sport2"));
                user.setSport3(rs.getString("sport3"));
                user.setType(rs.getString("U_Type"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setMobile(rs.getString("mobile"));
                user.setCollege(rs.getString("college"));
                user.setDepartment(rs.getString("department"));
                user.setYear(rs.getString("U_Year"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setStatus(rs.getString("U_Status"));
                user.setDateAdded(rs.getDate("dateAdded"));
                users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Exception : "+e.getMessage());
			
		}
		return users;
	}
	
	public boolean deleteUser(String userId){
		try {
			Connection conn = dbUtil.getConnection();
			Statement stmt = conn.createStatement();
			//delete respective login user
			String loginUserQuery = "delete from LoginUser where User_userId="+userId;
			stmt.executeUpdate(loginUserQuery);
			
			//delete respective survey user
			String surveyUserQuery = "delete from SurveyUser where userId="+userId;
			stmt.executeUpdate(surveyUserQuery);
			
			String query = "delete from User where userId="+userId;
			stmt.executeUpdate(query);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public UserObject getUser(String userId){
		try {
			Connection conn = dbUtil.getConnection();
			String query = "select * from User where userId="+userId;
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			// Set user properties
			UserObject user = new UserObject();
			user.setUserId(rs.getLong("userId"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setN_Number(rs.getString("N_Number"));
            user.setGender(rs.getString("gender"));
            user.setSport1(rs.getString("sport1"));
            user.setSport2(rs.getString("sport2"));
            user.setSport3(rs.getString("sport3"));
            user.setType(rs.getString("U_Type"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setMobile(rs.getString("mobile"));
            user.setCollege(rs.getString("college"));
            user.setDepartment(rs.getString("department"));
            user.setYear(rs.getString("U_Year"));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setState(rs.getString("state"));
            user.setZip(rs.getString("zip"));
            user.setStatus(rs.getString("U_Status"));
            user.setDateAdded(rs.getDate("dateAdded"));
            //close the result set
            rs.close();
            
            return user;
			
		} catch (SQLException e) {
			System.out.println("Exception : "+e.getMessage());
			return null;
		}

	}
	
	public UserObject getUserByEmail(String email){
		try {
			Connection conn = dbUtil.getConnection();
			String query = "select * from User where email='"+email+"'";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			// Set user properties
			UserObject user = new UserObject();
			user.setUserId(rs.getLong("userId"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setN_Number(rs.getString("N_Number"));
            user.setGender(rs.getString("gender"));
            user.setSport1(rs.getString("sport1"));
            user.setSport2(rs.getString("sport2"));
            user.setSport3(rs.getString("sport3"));
            user.setType(rs.getString("U_Type"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setMobile(rs.getString("mobile"));
            user.setCollege(rs.getString("college"));
            user.setDepartment(rs.getString("department"));
            user.setYear(rs.getString("U_Year"));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setState(rs.getString("state"));
            user.setZip(rs.getString("zip"));
            user.setStatus(rs.getString("U_Status"));
            user.setDateAdded(rs.getDate("dateAdded"));
            //close the result set
            rs.close();
            
            return user;
			
		} catch (SQLException e) {
			System.out.println("Exception : "+e.getMessage());
			return null;
		}

	}
	
	public List getUsersByAndWithPaging(Map<String, String> fields, int offset, int noOfRecords){
		List users = new ArrayList();
		try {
			Connection conn = dbUtil.getConnection();
			String query = "";
			Iterator entries = fields.entrySet().iterator();
			if(fields.entrySet().size() > 0){
				query += "select SQL_CALC_FOUND_ROWS * from User where ";
			}else{
				query += "select SQL_CALC_FOUND_ROWS * from User";
			}
			int count = 0;
			while (entries.hasNext()) {
			  Entry thisEntry = (Entry) entries.next();
			  count++;
			  String key = (String) thisEntry.getKey();
			  String value = (String) thisEntry.getValue();
			  if(count <= (fields.entrySet().size() - 1)){
				  query += key+"='"+value+"'"+" and ";
			  }else{
				  query += key+"='"+value+"'";
			  }
			}
			query +=  " limit "+ offset + ", " + noOfRecords;		
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				UserObject user = new UserObject();
				user.setUserId(rs.getLong("userId"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setN_Number(rs.getString("N_Number"));
                user.setGender(rs.getString("gender"));
                user.setSport1(rs.getString("sport1"));
                user.setSport2(rs.getString("sport2"));
                user.setSport3(rs.getString("sport3"));
                user.setType(rs.getString("U_Type"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setMobile(rs.getString("mobile"));
                user.setCollege(rs.getString("college"));
                user.setDepartment(rs.getString("department"));
                user.setYear(rs.getString("U_Year"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setStatus(rs.getString("U_Status"));
                user.setDateAdded(rs.getDate("dateAdded"));
                users.add(user);
			}
			rs.close();
			
			rs = stmt.executeQuery("SELECT FOUND_ROWS()");
			if(rs.next())
				this.noOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("Exception : "+e.getMessage());
			
		}
		return users;
	}
	
	public List<UserObject> viewAllUsers(int offset, int noOfRecords)
	{
		String query = "select SQL_CALC_FOUND_ROWS * from User limit "
				 + offset + ", " + noOfRecords;
		List<UserObject> list = new ArrayList<UserObject>();
		UserObject user = null;
		Connection connection = dbUtil.getConnection();;
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				user = new UserObject();
				user.setUserId(rs.getLong("userId"));
	            user.setFirstName(rs.getString("firstName"));
	            user.setLastName(rs.getString("lastName"));
	            user.setN_Number(rs.getString("N_Number"));
	            user.setGender(rs.getString("gender"));
	            user.setSport1(rs.getString("sport1"));
	            user.setSport2(rs.getString("sport2"));
	            user.setSport3(rs.getString("sport3"));
	            user.setType(rs.getString("U_Type"));
	            user.setEmail(rs.getString("email"));
	            user.setPhone(rs.getString("phone"));
	            user.setMobile(rs.getString("mobile"));
	            user.setCollege(rs.getString("college"));
	            user.setDepartment(rs.getString("department"));
	            user.setYear(rs.getString("U_Year"));
	            user.setAddress(rs.getString("address"));
	            user.setCity(rs.getString("city"));
	            user.setState(rs.getString("state"));
	            user.setZip(rs.getString("zip"));
	            user.setStatus(rs.getString("U_Status"));
	            user.setDateAdded(rs.getDate("dateAdded"));
				list.add(user);
			}
			rs.close();
			
			rs = stmt.executeQuery("SELECT FOUND_ROWS()");
			if(rs.next())
				this.noOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			
		}
		return list;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
}
