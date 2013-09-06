<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
     
<%@page import="org.sourcelesslight.model.User"%>
<%@page import="org.sourcelesslight.services.AuthenticationService"%>
<%@page import="org.spring.helpers.ApplicationContextProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext" %>

<%
AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/Scripts/Libraries/jquery-1.10.2.js">
</script>
</head>
<body>
<span>Home Page</span>

<s:form action="Authentication/login">
	<s:textfield label="Username" key="username"/>
	<s:password label="Password" key="password"/>
	<s:submit value="Login Now" />
</s:form>

</body>
</html>