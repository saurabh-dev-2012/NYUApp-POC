package com.nyu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import com.nyu.util.NYUUtils;

/**
 * Servlet implementation class TinyMCEReader
 */
@WebServlet("/TinyMCEReader")
public class TinyMCEReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TinyMCEReader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = request.getParameter("tinyTextArea");
		System.out.println("Tiny Text = "+text);
		//text +="Saurabh's test for appending";
		request.setAttribute("textToShow", text);
		//System.out.println("textToShow = "+text);
		//String emailtext = StringEscapeUtils.escapeHtml4(text);
		NYUUtils.sendMail("saurabh.pathak@arvan.in", "TestMail", text);
		getServletContext().getRequestDispatcher("/test.jsp").forward(request, response);
		
	}

}
