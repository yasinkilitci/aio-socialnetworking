
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/Validation.css"></link>

<h1>Settings</h1>
 
<s:form action="resultAction" namespace="/">
 
<h4>
	<s:select label="Theme" 
		headerKey="" headerValue="Select a Theme"
		list="themes"
		listKey="themeId"
		listValue="themeName"
		name="themeBox" 
		 />
</h4> 
 
<s:submit value="Save Settings" align="left" name="submit" />
 
</s:form>
 
 
 
 <script>

 $("#resultAction_submit").button();

 $("#resultAction").validate({
	    rules: {
	    	themeBox: { required: true }
	    },
	    messages:
	    {
	    	themeBox: "Please select a theme!"
	    }
	});

 $("#resultAction").submit(function(e){
	    return false;
	});

 $("#resultAction_submit").click(function () {
	    var isvalid = $("#resultAction").valid();
	    if (isvalid)
	        alert("form is valid");
	});
 
</script>