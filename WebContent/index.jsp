<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
 
<%@page import="org.spring.helpers.ApplicationContextProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>

<%
AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
%>   


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/Libraries/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/Libraries/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/Libraries/jquery-ui.js"></script>
</head>
<body>

<div class="main-wrapper">
	
	<div class="header-wrapper">
		<span>Header</span>
	</div>
	
	<div class="menu-wrapper">
		<jsp:include page="./Pages/Partial/menu.jsp"/>
	</div>
	
	<div class="content-wrapper">
	<% if(request.getSession().getAttribute("id")==null){ %>
		<jsp:include page="./Pages/Anonymous/Authentication/login.jsp"/>
		<%} %>
	</div>
	
	<div class="copyright-wrapper"><span>Copyright</span></div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/mainscript.js"/></script>

</body>
</html>