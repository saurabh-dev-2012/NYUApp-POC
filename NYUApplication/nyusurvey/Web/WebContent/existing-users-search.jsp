<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript">
function doAddToPosting() {
	document.userSearch.addToPosting.value = "true";
	document.userSearch.submit();
}
function doSearch() {
	document.userSearch.searchFromExisting.value = "true";
	document.userSearch.submit();
}
function doCheckAll() {
	var checkedValue = document.getElementById('masterCheck').checked;
	var elements = document.getElementsByClassName("users");
	for(var i=0; i<elements.length; i++){
		var checkbox = elements[i];
		checkbox.checked = checkedValue;
	}
	//alert('in do check');
}
</script>
<html>
<body style="width:99.5%;height:14.5%;margin:0px 0px 0px 0px">
		<form name="userSearch" action="UserSearch" method="post">
			<input type="hidden" name="addToPosting" value="false">
			<input type="hidden" name="searchFromExisting" value="false">
			<input type="hidden" name="surveyId" value="${requestScope.surveyId}">
			<div class="content-area-right"><h2 class="entry-title" style="margin-bottom:0;"><span>EXISTING USERS</span><span class="import-xport"><button type="submit" onclick="javascript:doAddToPosting()">Add To Posting</button></span></h2></div>
			<div class="site-content new-search">
			<ul class="gray-search">
			<li class="title">Search:</li>
			<li class="First"><label>First</label><input type="text" name="firstName" value=""></li>
			<li class="Last"><label>Last</label><input type="text" name="lastName" value=""></li>
			<li class="Email"><label>Email</label><input type="email" name="email" value=""></li>
			<li class="Status"><label>Status</label><select name="status" ><option>Active</option><option>Deactive</option></select></li>
			<li class="Sports"><label>Sports</label><select name="sports" ><option></option><option>Cricket</option><option>Football</option><option>Rugbi</option></select></li>
			<div class="clear"></div>
			<li class="butt-reset-search"><button type="submit" onclick="javascript:doSearch()">Search</button><button style="margin-right:0;">Reset</button></li>
			<div class="clear"></div>
			</ul>
			
			<ul class="heading-strip-purple">
			<li class="check"><input type="checkbox" id="masterCheck" onchange="javascript:doCheckAll()"/></li>
			<li class="Date">Date</li>
			<li class="First">First</li>
			<li class="Last">Last</li>
			<li class="Email">Email</li>
			<li class="Status">Status</li>
			<li class="Sport">Sport</li>
			<div class="clear"></div>
			</ul>	
			<c:forEach var="user" items="${requestScope.users}">
			    <ul class="search-files-details">
					<li class="check"><input type="checkbox" name="select-${user.userId}" class="users"/></li>
					<li class="Date"><input type="text" value="${user.dateAddedString}"/></li>
					<li class="First"><input type="text" value="${user.firstName}"/></li>
					<li class="Last"><input type="text" value="${user.lastName}"/></li>
					<li class="Email"><input type="email" value="${user.email}"></li>
					<li class="Status"><input type="text" value="${user.status}"/></li>
					<li class="Sport"><input type="text" value="${user.sport1}"/></li>
					<div class="clear"></div>
				</ul>
			</c:forEach>
			</div>
			</div>
				<div class="clear"></div>
		</form>	
</body>
</html>