
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/Validation.css"></link>

<h1>Settings</h1>
 
<s:form id="formSettings" action="savePreferences" namespace="/ControlPanel">
 
<h4>
	<s:select label="Theme" 
		headerKey="" headerValue="Select a Theme"
		list="themes"
		listKey="themeId"
		listValue="themeName"
		name="themeBox"
		id="cmbTheme"
		 />
</h4> 
 
<s:submit value="Save Settings" align="left" name="submit" />
 
</s:form>
 
 
 
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/Scripts/settings.js"></script>