<html>
<style>
span.user-pic{
border-radius: 100%;
    float: left;
    height: 80px;
    margin: 0px 15px 0px 760px;
    max-width: 50px;
    width: 100%;
	
}
span.user-name{
font:18px/30px "Roboto-Regular";
text-transform:uppercase;
}
span.arrow-here{
	float: right;
    margin: 30px 50px 0px 0px;
}
span.arrow-here a{}
</style>
<body>
	<table border="1" cellspacing="0" cellpadding="0" style="width:79%;height:15%;margin:0px 0px 0px 275px" >
	<tr>
	<td colspan="2">
	    <span class="user-pic"></span>
		<span class="user-name">${sessionScope.activeUser.firstName} ${sessionScope.activeUser.lastName}</span><span class="arrow-here"><a href="#"><img src="images/icon-dwn-arrow.png"></a></span><br>
		<span class="user-id">${sessionScope.activeUser.email}</span>
		<div class="clear"></div></div><!--- .login-user-details ---->
	</tr>
	</table>
</body>
</html>