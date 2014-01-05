
<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Styles/Validation.css"></link>

<s:form id="frmLogin" action="login" namespace="/Authentication">
	<s:textfield id="txtUsername" label="Username" key="username" />
	<s:password id="txtPassword" label="Password" key="password" />
	<s:checkbox id="optRememberMe" label="Remember Me" key="rememberMe" />
	<s:submit id="btnLogin" align="left" value="Login Now" />
</s:form>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/Scripts/login.js"></script>