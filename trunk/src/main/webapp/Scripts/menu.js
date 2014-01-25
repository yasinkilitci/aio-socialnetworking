$(document).ready(function(){
	
	//$("div.submenu-wrapper").load("./Pages/Partial/submenu.action");
	
	$('li.menu-list').button();
	
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
	
	$("#lnkFind").click(function(){

		$("div.content-wrapper").load("./FriendFinder/list");
		
	});
	
	
	
	
	
});


