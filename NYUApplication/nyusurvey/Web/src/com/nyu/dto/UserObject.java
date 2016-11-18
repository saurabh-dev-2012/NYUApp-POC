package com.nyu.dto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nyu.util.DatabaseUtil;

public class UserObject {
	
	private Long userId;
	private String N_Number;
	private String email;
	private String firstName;
	private String lastName;
	private String type;
	private String gender;
	private String sport1;
	private String sport2;
	private String sport3;
	private String phone;
	private String mobile;
	private String college;
	private String department;
	private String year;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String status;
	private Date dateAdded;
	private Long createdBy;
	private Long lastUpdatedBy;
	private Timestamp createdOn;
	private Timestamp lastUpdatedOn;
	private Map csvFileHeaders = null;
	
	public UserObject() {
		// TODO Auto-generated constructor stub
		this.csvFileHeaders = new HashMap();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSport1() {
		return sport1;
	}

	public void setSport1(String sport1) {
		this.sport1 = sport1;
	}

	public String getSport2() {
		return sport2;
	}

	public void setSport2(String sport2) {
		this.sport2 = sport2;
	}

	public String getSport3() {
		return sport3;
	}

	public void setSport3(String sport3) {
		this.sport3 = sport3;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getN_Number() {
		return N_Number;
	}

	public void setN_Number(String n_Number) {
		this.N_Number = n_Number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Map getCsvFileHeaders() {
		return csvFileHeaders;
	}

	public void setCsvFileHeaders() {
		this.csvFileHeaders.put("N_Number", "N-number");
		this.csvFileHeaders.put("gender", "Gender");
		this.csvFileHeaders.put("sport1", "Sport1");
		this.csvFileHeaders.put("sport2", "Sport2");
		this.csvFileHeaders.put("sport3", "Sport3");
		this.csvFileHeaders.put("firstName", "Firstname ");
		this.csvFileHeaders.put("lastName", "Lastname");
		this.csvFileHeaders.put("type", "Type");
		this.csvFileHeaders.put("email", "Email");
		this.csvFileHeaders.put("phone", "Phone Number");
		this.csvFileHeaders.put("mobile", "Mobile Number");
		this.csvFileHeaders.put("college", "College");
		this.csvFileHeaders.put("department", "Department");
		this.csvFileHeaders.put("year", "Year");
		this.csvFileHeaders.put("address", "Address");
		this.csvFileHeaders.put("city", "City");
		this.csvFileHeaders.put("state", "State");
		this.csvFileHeaders.put("zip", "Zip");
		this.csvFileHeaders.put("status", "Status");
	}

	public String getDateAddedString() {
		if(dateAdded != null){
			DateFormat sqlDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			return sqlDateFormat.format(dateAdded);
		}
		return "";
	}
	
	public boolean equals(UserObject user){
		return this.userId.equals(user.getUserId()); 
	}
	
	public LoginUser getRelativeLoginUser(){
		LoginUser loginUser = null;
		try{
			DatabaseUtil dbUtil = DatabaseUtil.getDatabaseUtil();
			Connection conn = dbUtil.getConnection();
			String query = "select * from LoginUser where User_userId="+this.getUserId();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			//set the props to intended SurveyUser
			loginUser = new LoginUser();
			loginUser.setUserId(rs.getLong("User_userId"));
			loginUser.setPassword(rs.getString("password"));
			loginUser.setRegisterDate(rs.getDate("registerDate"));
			loginUser.setActive(rs.getBoolean("isActive"));
			loginUser.setCreatedOn(rs.getTimestamp("createdOn"));
			loginUser.setLastUpdatedOn(rs.getTimestamp("lastUpdatedOn"));
			loginUser.setCreatedBy(rs.getLong("createdBy"));
			loginUser.setLastUpdatedBy(rs.getLong("lastUpdatedBy"));    
	 		
		    //close result set
            rs.close();
         			
		}catch(SQLException ex){
			System.out.println("Exception : "+ex.getMessage());
		}
		
		return loginUser;
	}
}
