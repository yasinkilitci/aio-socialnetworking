$(document).ready(function(){
	
	
	$("#lnkHome").click(function(){

		window.location.href = "./";
		
	});
	
	$("#lnkSignUp").click(function(){

		$("div.content-wrapper").load("./Pages/Anonymous/SignUp/signup.jsp");
		
	});
	
	$("#lnkLogIn").click(function(){

		$("div.content-wrapper").load("./Pages/Anonymous/Authentication/login.jsp");
		
	});
	
	$("#lnkSettings").click(function(){

		$("div.content-wrapper").load("./ControlPanel/settings");
		
	});
	
	
	
});


