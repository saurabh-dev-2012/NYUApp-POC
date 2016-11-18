<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript">
function doLogin() {
	document.editForm.saveUser.value = 'true';
    document.editForm.submit();
}
function doReset() {
    document.loginForm.reset();
}
</script>
<html>
<head>
	<center>
    	<h2>
    		${requestScope.headerMessage}
    	</h2>
    </center>
</head>

<body style="width:100%;height:27%;margin:0px 0px 0px 0px">
  	<div class="purple-logo-menu-left"><div class="site-logo"><a href="logintemplate.jsp"><img src="images/logo-main.jpg"></a></div></div>
 	<div class="content-area-right"><h2 class="entry-title">LOGIN</h2>
	<div class="site-content">
		<form name="loginForm" action="LoginAdmin" method="post">	
			<div class="edit-left-input">
				<label>Username</label><input type="text" name="username"></p>
				<label>Password</label><input type="password" name="password"></p>
			</div>
			<div class="clear"></div>
			<div class="submit-upld"><button type="submit" onclick="javascript:doLogin()">Login</button><button type="button" onclick="javascript:doReset()">Reset</button></div>
			<div class="clear"></div>
		</form>
	</div><!---- .site-content ---->
	</div>

</div>
</div>
	<div class="clear"></div>
</body>
</html>