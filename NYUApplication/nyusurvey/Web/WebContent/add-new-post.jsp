<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<script language="JavaScript">
function doSave() {
	document.addSurveyForm.saveSurvey.value = 'true';
    document.addSurveyForm.submit();
}
</script>
<html>
<head>
	<center>
    	<h2>
    		${requestScope.saveMessage}
    	</h2>
    </center>
</head>
<body style="width:99.5%;height:14.5%;margin:0px 0px 0px 0px">
<form name="addSurveyForm" action="EditSurvey" method="get">
	<div class="content-area-right"><h2 class="entry-title"><span>ADD</span>/ NEW POSTING</h2>
	<div class="site-content add-posting">
	<div class="edit-left-input">
	<input type="hidden" name="surveyId" value="${requestScope.survey.surveyId}"></input>
	<input type="hidden" name="saveSurvey" value="false"></input>
	
	<p class="Type"><label>Type</label>
		<select name="type">
			<option <c:if test="${requestScope.survey.type  == 'Survey'}">selected </c:if>>Survey</option>
			<option <c:if test="${requestScope.survey.type  == 'Announcement'}">selected </c:if>>Announcement</option>
			<option <c:if test="${requestScope.survey.type  == 'News'}">selected </c:if>>News</option>
		</select>
	</p>
	<p class="Title"><label>Title</label><input type="text" name="title" value="${requestScope.survey.title}"></p>
	<p class="Message"><label>Message</label><input type="textarea" name="message" value="${requestScope.survey.message}"></p>
	<p class="Result-Link"><label>Result Link</label><input type="text" name="resultLink" value="${requestScope.survey.resultLink}"></p>
	<p class="Status"><label>Status</label><select name="status"><option>Active</option><option>Deactive</option></select></p>
	<p class="Exp-Date"><label>Expiration Date</label><input type="date" name="expirationDate" value="${requestScope.survey.expirationDate}"></p>
	</div>
	<div class="clear"></div>
	<div class="submit-upld"><button type="submit" onclick="javascript:doSave()">Save</button><button onclick="#"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/SurveySearch?surveySearch=true">Cancel</a></button><button>Reset</button></div>
	</div><!---- .site-content ---->
	</div><!---- .content-area-right ---->
	<div class="clear"></div>
</form>
</body>
</html>