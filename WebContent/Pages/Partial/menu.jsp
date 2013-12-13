<%@page import="org.sourcelesslight.model.User"%>
<%@page import="org.sourcelesslight.services.AuthenticationService"%>
<%@page import="org.sourcelesslight.services.UserService"%>
<%@page import="org.spring.helpers.ApplicationContextProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
<%@page import="org.sourcelesslight.model.enums.AuthType"%>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  
  	<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/menu.js"/></script>
  	
<%
UserService us;
User user = null;
AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
Object sessionId = request.getSession().getAttribute("id");
%>
	<% if(sessionId==null){ %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/themes/ui-lightness/jquery-ui.css"></link>
	<script>
$(function(){
	$("body").css("background","rgba(0,128,255,1)");
	$("body.li").draggable();
	$("div.menu-wrapper,div.content-wrapper,div.copyright-wrapper").css("background","rgba(255,150,0,1)");
	$(".content-wrapper").load("./Pages/Anonymous/Authentication/login.jsp");
});
</script>
	<% } else {
	int id = Integer.valueOf(sessionId.toString());
	us = context.getBean("UserService",UserService.class);
	
	try{
	user = us.getUserById(id);
	AuthType authType = user.getAuthLevel();
	if(authType == AuthType.ADMIN)
	{
		%>
		
	<script>
		$(function(){
			$("div.menu-wrapper,div.content-wrapper,div.copyright-wrapper").css("background","rgba(255,150,0,0.6)");
		});
		</script>
	<%
	}
	else if(authType == AuthType.USER)
	{
		%>
	<script>
		$(function(){
			$("div.menu-wrapper,div.content-wrapper,div.copyright-wrapper").css("background","rgba(255,150,0,0.6)");
		});
		</script>
	<%
	}
	}
	catch(Exception h)
	{
		h.printStackTrace();
	}
}
%>
<div id="menu-css">
	<ul>
		<li><a href="#" id="lnkHome">Home</a></li>
		
	<%
if(user!=null)
{
	AuthType authType = user.getAuthLevel();
	String theme = user.getPreferences().getTheme().getThemeName();
	%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/themes/<%=theme %>/jquery-ui.css"></link>
	<%
	
	if(authType == AuthType.USER)
	{%>
	<li><a href="#" id="lnkDiscover">Discover</a></li>
	<li><a href="#" id="lnkFind">Find</a></li>
	<li><a href="#" id="lnkMyProfile">My Profile</a></li>
	<%
	}else if(authType == AuthType.ADMIN)
	{%>
	<li><a href="#" id="lnkDiscover">Manage Users</a></li>
	<li><a href="#" id="lnkTools">Administrative Tools</a></li> 
	<%}
	
	if(authType == AuthType.USER || authType == AuthType.ADMIN)
	{%>
	<li><a href="#" id="lnkSettings">Settings</a></li>
	<li><a href="<s:url action="Authentication/logout"/>">Log Out</a></li>
	<%
	}
	
	
}else{
%>
	<li><a href="#" id="lnkAbout">About</a></li>
	<li><a href="#" id="lnkDonate">Donate</a></li>
	<li><a href="#" id="lnkLogIn">Log In</a></li>
	<li><a href="#" id="lnkSignUp">Sign Up Now!</a></li>
<% } %>

</ul>
</div>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/maintheme.css"></link>