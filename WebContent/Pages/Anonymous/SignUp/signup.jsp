<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/Validation.css"></link>
     
<s:form action="signup" namespace="/Registration" id="frmSignup">
	<s:textfield label="Username" key="username"/>
	<s:password label="Password" key="password"/>
	<s:password label="Password(Repeat)" key="password2"/>
	<s:textfield label="First Name" key="firstname"/>
	<s:textfield label="Last Name" key="lastname"/>
	<s:textfield label="E-mail" key="email"/>
	<s:submit align="left" value="SignUp Now!" id="btnSubmit" />
</s:form>
<script src="${pageContext.request.contextPath}/Scripts/signup.js"></script>
