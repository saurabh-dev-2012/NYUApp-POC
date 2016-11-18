<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript">
function doEditDone() {
	document.editAudienceForm.editDone.value = 'true';
    document.editAudienceForm.submit();
}
function doSearchAudience() {
	document.editAudienceForm.searchAudience.value = 'true';
    document.editAudienceForm.submit();
}
</script>
<html>
<body style="width:99.5%;height:14.5%;margin:0px 0px 0px 0px">
		<form action="EditAudience" method="get" name="editAudienceForm">
			<input type="hidden" name="surveyId" value="${requestScope.surveyId}">
			<input type="hidden" name="editDone" value="false">
			<input type="hidden" name="searchAudience" value="false">
			<div class="content-area-right"><h2 class="entry-title" style="margin-bottom:0;"><span>EDIT CURRENT AUDIENCE</span><span class="import-xport"><button type="submit" onclick="javascript:doEditDone()">Done</button></span></h2>
			<div class="site-content new-search">
			<li class="butt-reset-search"></li>
			<ul class="gray-search">
			<li class="title">Search:</li>
			<li class="First"><label>First</label><input type="text" name="firstName" value=""></li>
			<li class="Last"><label>Last</label><input type="text" name="lastName" value=""></li>
			<li class="Email"><label>Email</label><input type="email" name="email" value=""></li>
			<li class="Status"><label>Status</label><select name="status" ><option>Active</option><option>Deactive</option></select></li>
			<li class="Sports"><label>Sports</label><select name="sports" ><option></option><option>Cricket</option><option>Football</option><option>Rugbi</option></select></li>
			<div class="clear"></div>
			<li class="butt-reset-search"><button type="submit" onclick="javascript:doSearchAudience()">Search</button><button style="margin-right:0;">Reset</button></li>
			<div class="clear"></div>
			</ul>
			<div class="clear"></div>
			
			<ul class="heading-strip-purple">
			<li class="Date">Date</li>
			<li class="First">First</li>
			<li class="Last">Last</li>
			<li class="Email">Email</li>
			<li class="Status">Status</li>
			<li class="Sport">Sport</li>
			<li class="Delete">Delete</li>
			<li class="Edit">Edit</li>
			<div class="clear"></div>
			</ul>	
			
			<c:forEach var="user" items="${requestScope.users}">
			    <ul class="search-files-details">
					<li class="Date"><input type="text" value="${user.dateAddedString}"/></li>
					<li class="First"><input type="text" value="${user.firstName}"/></li>
					<li class="Last"><input type="text" value="${user.lastName}"/></li>
					<li class="Email"><input type="email" value="${user.email}"></li>
					<li class="Status"><input type="text" value="${user.status}"/></li>
					<li class="Sport"><input type="text" value="${user.sport1}"/></li>
					<li class="Delete"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditAudience?deleteAudience=true&userId=${user.userId}&surveyId=${requestScope.surveyId}"><img src="images/icon-delete.png"></a></li>
					<li class="Edit"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditAudience?editAudience=true&userId=${user.userId}&surveyId=${requestScope.surveyId}"><img src="images/icon-edit.png"></a></li>
					<div class="clear"></div>
				</ul>
			</c:forEach>
			
			</div>
			</div>
				<div class="clear"></div>
		</form>	
</body>
</html>