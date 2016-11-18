package com.nyu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nyu.dao.UserObjectDAO;
import com.nyu.dto.UserObject;
import com.nyu.util.CsvFileWriter;

/**
 * Servlet implementation class DownloaderServlet
 */
@WebServlet("/DownloaderServlet")
public class DownloaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String type = request.getParameter("type");
		
		if(type.equalsIgnoreCase("User")){
			
			UserObjectDAO userDao = new UserObjectDAO();
			List users = userDao.getAllUsers();
			// file name/path for export and download.
			String fileName = System.getProperty("user.home")+"/user.csv";
			CsvFileWriter fileWriter = new CsvFileWriter();
			fileWriter.setData(users);
			fileWriter.setFileHeaderMapping(UserObjectDAO.FILE_HEADER_MAPPING);
			fileWriter.writeCsvFile(fileName, "User");
			
			// reads input file from an absolute path
			File downloadFile = new File(fileName);
	        FileInputStream inStream = new FileInputStream(downloadFile);
	   	        
	        // obtains ServletContext
	        ServletContext context = getServletContext();
	         
	        // gets MIME type of the file
	        String mimeType = context.getMimeType(fileName);
	        if (mimeType == null) {        
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	         
	        // modifies response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	         
	        // forces download
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	         
	        // obtains response's output stream
	        OutputStream outStream = response.getOutputStream();
	         
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	         
	        while ((bytesRead = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	        inStream.close();
	        outStream.close();
		}
	}

}
