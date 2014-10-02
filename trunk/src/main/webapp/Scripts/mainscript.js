
$(document).ready(function(){
	
	$("div.menu-wrapper,div.content-wrapper,div.copyright-wrapper").hide();
	$("div.menu-wrapper").load("./Pages/Partial/menu");
	
		$("div.menu-wrapper").show(1000, function(){
			$("div.content-wrapper").fadeIn(200, function(){
				$("div.copyright-wrapper").fadeIn(200);
			});
		});
	
});