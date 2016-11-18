package com.nyu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DatabaseUtil {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/NYUSurvey";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection conn = null;
    private Statement stmt = null;
    private static DatabaseUtil singleton = null;
    private DateFormat sqlDateFormat = null;
    private DateFormat sqlDateTimeFormat = null;
    
   
	private DatabaseUtil() {
		// TODO Auto-generated constructor stub
		initConnection();
		sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		sqlDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}
	
	public static DatabaseUtil getDatabaseUtil(){
		if(singleton == null){
			return singleton = new DatabaseUtil();
		}else{
			return singleton;
		}
	}
	
	private void initConnection(){

		   try{
		      //Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
	          //Open a connection
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

     	   }catch(SQLException se){
			  System.out.println("Exception: "+se.getMessage()); 
		      se.printStackTrace();
		   } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
				System.out.println("Exception: "+e.getMessage());
				e.printStackTrace();
		   }
	}

	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public Statement getStatement() {
		return stmt;
	}

	public void setStatement(Statement stmt) {
		this.stmt = stmt;
	}
	
	public synchronized Long getNextSequenceIdLong(String tableName, String idName){
		Long newId = new Long(-1);
		String query = "select"+" "+idName+" from "+tableName+" order by "+idName+" desc";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			boolean rowsInResultSet = rs.first();
			if(rowsInResultSet){
				Long lastId = rs.getLong(idName);
				newId = lastId + 1;
			}else{
				newId = new Long(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newId;
	}

	public DateFormat getSqlDateFormat() {
		return sqlDateFormat;
	}

	public DateFormat getSqlDateTimeFormat() {
		return sqlDateTimeFormat;
	}
	
	
}
