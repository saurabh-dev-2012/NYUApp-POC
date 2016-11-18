<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript">
function doAudience() {
	document.editForm.saveAudience.value = 'true';
    document.editForm.submit();
}
</script>
<html>
<body style="width:100%;height:14.5%;margin:0px 0px 0px 0px">
	<form name="editForm" action="EditAudience" method="get">	
	<div class="content-area-right"><h2 class="entry-title"><span>EDIT AUDIENCE </span>/ ADD AUDIENCE FORM</h2>
	<div class="site-content edit-add-user">
	<div class="edit-left-input">
	<input type="hidden" name="userId" value="${requestScope.user.userId}"></input>
	<input type="hidden" name="surveyId" value="${requestScope.surveyId}"></input>
	<input type="hidden" name="saveAudience" value="false"></input>
	<p class="First"><label>First</label><input type="text" name="firstName" value="${requestScope.user.firstName}"></p>
	<p class="Last"><label>Last</label><input type="text" name="lastName" value="${requestScope.user.lastName}"></p>
	<p class="Type"><label>Type</label><input type="text" name="type" value="${requestScope.user.type}"></p>
	<p class="Email"><label>Email</label><input type="text" name="email" value="${requestScope.user.email}"></p>
	<p class="Mobile"><label>Mobile</label><input type="text" name="mobile" value="${requestScope.user.mobile}"></p>
	<p class="Collage"><label>College</label><input type="text" name="college" value="${requestScope.user.college}"></p>
	<p class="Year"><label>Year</label><input type="number" name="year" maxlength="4" value="${requestScope.user.year}"></p>
	</div>
	<div class="edit-right-input">
	<p class="Address"><label>Address</label><input type="text" name="address" value="${requestScope.user.address}"></p>
	<p class="Status"><label>Status</label><select name="status"><option>Active</option><option>Deactive</option></select></p>
	<p class="Sports-1"><label>Sports 1</label><select name="sport1"><option></option><option <c:if test="${requestScope.user.sport1  == 'Cricket'}">selected</c:if>>Cricket</option><option <c:if test="${requestScope.user.sport1  == 'Tennis'}">selected</c:if>>Tennis</option><option <c:if test="${requestScope.user.sport1  == 'Football'}">selected</c:if>>Football</option><option <c:if test="${requestScope.user.sport1  == 'Rugby'}">selected</c:if>>Rugby</option></select></p>
	<p class="Sports-2"><label>Sports 2</label><select name="sport2"><option></option><option <c:if test="${requestScope.user.sport2  == 'Cricket'}">selected</c:if>>Cricket</option><option <c:if test="${requestScope.user.sport2  == 'Tennis'}">selected</c:if>>Tennis</option><option <c:if test="${requestScope.user.sport2  == 'Football'}">selected</c:if>>Football</option><option <c:if test="${requestScope.user.sport2  == 'Rugby'}">selected</c:if>>Rugby</option></select></p>
	<p class="Sports-3"><label>Sports 3</label><select name="sport3"><option></option><option <c:if test="${requestScope.user.sport3  == 'Cricket'}">selected</c:if>>Cricket</option><option <c:if test="${requestScope.user.sport3  == 'Tennis'}">selected</c:if>>Tennis</option><option <c:if test="${requestScope.user.sport3  == 'Football'}">selected</c:if>>Football</option><option <c:if test="${requestScope.user.sport3  == 'Rugby'}">selected</c:if>>Rugby</option></select></p>
	<p class="Number"><label>Number</label><input type="text" name="N_Number" value="${requestScope.user.n_Number}"></p>
	</div><div class="clear"></div>
	<div class="submit-upld"><button type="submit" onclick="javascript:doAudience()">Save</button><button>Cancel</button><button>Reset</button></div>
	</div><!---- .site-content ---->
	</div><!---- .content-area-right ---->
		<div class="clear"></div>
	</form>
</body>
</html>