<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.nyu.controller.UploaderServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <center>
    	<h2>
    		${requestScope.message}
    	</h2>
    </center>
</head> 
<body style="width:100%;height:14.5%;margin:0px 0px 0px 0px">

	<div class="content-area-right"><h2 class="entry-title"><span>AUDIENCE</span></h2>
	<div class="site-content upload-sec">
	<div class="upl-file">
	<form action="UploaderServlet" method="post" enctype="multipart/form-data">
		<input type="hidden" name="surveyId" value="${requestScope.surveyId}">
		<input type="file" name="uploadFile"> <p class="submit-butt-1"><button type="submit">Upload</button></p></div>
	</form>
	<form action="">
		<div class="clear"></div>
		<div class="submit-upld"><button type="submit"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/newsearchtemplate.jsp">Submit</a></button></div>
	</form>
	</div><!---- .site-content ---->
	</div><!---- .content-area-right ---->
		<div class="clear"></div>


</body>
</html>