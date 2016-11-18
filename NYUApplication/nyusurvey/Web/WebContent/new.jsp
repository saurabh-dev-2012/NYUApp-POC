<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript">
function doChangeAudience(surveyId) {
	var audiencName = "audience-"+surveyId;
	document.surveyForm.changeAudience.value = "true";
	document.surveyForm.surveyAudience.value = document.surveyForm.elements[audiencName].value;
	document.surveyForm.surveyId.value = surveyId;
	document.surveyForm.submit();
	
}
</script>
<html>
<body style="width:99.5%;height:14.5%;margin:0px 0px 0px 0px">
<form name="surveyForm" action="EditSurvey" method="get">
<input type="hidden" name="changeAudience" value="false">
<input type="hidden" name="surveyAudience" value="">
<input type="hidden" name="surveyId" value="">
<div class="content-area-right"><h2 class="entry-title" style="margin-bottom:0;"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/addnewposttemplate.jsp">NEW</a></h2>
<div class="site-content new">
<ul class="heading-strip-purple">
<li class="check"><input type="checkbox" /></li>
<li class="Date">Date</li>
<li class="Title">Title</li>
<li class="Send">Send</li>
<li class="Result">Result</li>
<li class="Status">Status</li>
<li class="Audience">Audience</li>
<li class="Copy">Copy</li>
<li class="Delete">Delete</li>
<li class="Edit">Edit</li>
<div class="clear"></div>
</ul>
<c:forEach var="survey" items="${requestScope.surveys}">
	<ul class="loaded-files-details">
		<li class="check"><input type="checkbox" /></li>
		<li class="Date"><input type="text" value="${survey.expDateString}"/></li>
		<li class="Title"><input type="text" value="${survey.title}"/></li>
		<li class="Send"><img src="images/icon-send.png"></li>
		<li class="Result"><input type="text" value="Result"/></li>
		<li class="Status"><input type="text" value="${survey.status}"/></li>
		<li class="Audience"><select onmouseup="javascript:doChangeAudience(${survey.surveyId})" name="audience-${survey.surveyId}"><option>Import</option><option>Existing Users</option><option>Add User</option><option>Edit</option></select></li>
		<li class="Copy"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditSurvey?copySurvey=true&surveyId=${survey.surveyId}"><img src="images/icon-copy.png"></a></li>
		<li class="Delete"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditSurvey?deleteSurvey=true&surveyId=${survey.surveyId}"><img src="images/icon-delete.png"></a></li>
		<li class="Edit"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditSurvey?editSurvey=true&surveyId=${survey.surveyId}"><img src="images/icon-edit.png"></a></li>
		<div class="clear"></div>
	</ul>
</c:forEach>


</div><!---- .site-content ---->
</div><!---- .content-area-right ---->
	<div class="clear"></div>
</form>

</body>
</html>