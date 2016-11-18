<html>
<body>
	<table border="1" cellspacing="0" cellpadding="0" style="width:20%;height:99% ;margin:-75px 0px 0px 0px">
	<tr>
	<td colspan="2">
	      	<div class="purple-logo-menu-left">
				<div class="site-logo">
					<a href="index.html">
						<img src="images/logo-main.jpg">
					</a>
				</div>
			</div>
		   <header>
		   <div id="navbar">
			
		     <!-- <a href="#" class="menubtn">menu</a>-->
			   
		      <!-- use captain icon for toggle menu -->
		      <div id="hamburgermenu">
		        <ul>
		          <li><a href="<%= getServletConfig().getServletContext().getContextPath() %>/SurveySearch?surveySearch=true">POSTS</a></li>
		          <li><a href="<%= getServletConfig().getServletContext().getContextPath() %>/UserSearch">USERS</a></li>
		         
		        </ul>
		      </div>
		    </div>
		    <div class="overlay"></div>
		
		  </header>
		  </td>
	</tr>
	</table>

  </body>
</html>