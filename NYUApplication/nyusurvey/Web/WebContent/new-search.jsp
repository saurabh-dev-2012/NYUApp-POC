<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<script language="JavaScript">
function pageLoad(pageValue) {
	document.userSearchForm.page.value = pageValue;
    document.userSearchForm.submit();
}
</script>
<html>
<body style="width:99.5%;height:14.5%;margin:0px 0px 0px 0px">
		<form action="UserSearch" method="get" name=userSearchForm>
		<input type="hidden" name="page" value="">
		<div class="content-area-right"><h2 class="entry-title" style="margin-bottom:0;"><span>NEW</span><span class="import-xport"><button onclick="#"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/uploadtemplate.jsp">Import</a></button><button onclick="#"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/DownloaderServlet?type=User">Export</a></button></span></h2>
		<div class="site-content new-search">
		<ul class="gray-search">
		<li class="title">Search:</li>
		<li class="First"><label>First</label><input type="text" name="firstName" value=""></li>
		<li class="Last"><label>Last</label><input type="text" name="lastName" value=""></li>
		<li class="Email"><label>Email</label><input type="email" name="email" value=""></li>
		<li class="Status"><label>Status</label><select name="status" ><option>Active</option><option>Deactive</option></select></li>
		<li class="Sports"><label>Sports</label><select name="sports" ><option></option><option>Cricket</option><option>Football</option><option>Rugbi</option></select></li>
		<div class="clear"></div>
		<li class="butt-reset-search"><button type="submit">Search</button><button style="margin-right:0;">Reset</button></li>
		<div class="clear"></div>
		</ul>
		</form>	
		<ul class="heading-strip-purple">
		<li class="check"><input type="checkbox" /></li>
		<li class="Date">Date</li>
		<li class="First">First</li>
		<li class="Last">Last</li>
		<li class="Email">Email</li>
		<li class="Status">Status</li>
		<li class="Sport">Sport</li>
		<li class="Copy">Copy</li>
		<li class="Delete">Delete</li>
		<li class="Edit">Edit</li>
		<div class="clear"></div>
		</ul>	
		<c:forEach var="user" items="${requestScope.users}">
		    <ul class="search-files-details">
				<li class="check"><input type="checkbox" /></li>
				<li class="Date"><input type="text" value="${user.dateAddedString}"/></li>
				<li class="First"><input type="text" value="${user.firstName}"/></li>
				<li class="Last"><input type="text" value="${user.lastName}"/></li>
				<li class="Email"><input type="email" value="${user.email}"></li>
				<li class="Status"><input type="text" value="${user.status}"/></li>
				<li class="Sport"><input type="text" value="${user.sport1}"/></li>
				<li class="Copy"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditUser?copyUser=true&userId=${user.userId}"><img src="images/icon-copy.png"></a></li>
				<li class="Delete"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditUser?deleteUser=true&userId=${user.userId}"><img src="images/icon-delete.png"></a></li>
				<li class="Edit"><a href="<%= getServletConfig().getServletContext().getContextPath() %>/EditUser?editUser=true&userId=${user.userId}"><img src="images/icon-edit.png"></a></li>
				<div class="clear"></div>
			</ul>
		</c:forEach>
		<div class="clear"></div>
		<footer>
			<div class="pagination-centered">
			  	<c:if test="${requestScope.currentPage != 1}">
					<td><a href="javascript:pageLoad(${requestScope.currentPage - 1})">Previous</a></td>
				</c:if>
			
				<%--For displaying Page numbers. 
				The when condition does not display a link for the current page--%>
				<ul class="pagination-centered">
					<c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
						<c:choose>
							<c:when test="${requestScope.currentPage eq i}">
								<td>${i}</td>
							</c:when>
							<c:otherwise>
								<td><a href="javascript:pageLoad(${i})">${i}</a></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<%--For displaying Next link --%>
				<c:if test="${currentPage lt noOfPages}">
					<td><a href="javascript:pageLoad(${currentPage + 1})">Next</a></td>
				</c:if>
			</div>
		</footer>
</body>
</html>