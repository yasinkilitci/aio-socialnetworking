
$(document).ready(function(){
	
	$("div.header-wrapper,div.menu-wrapper,div.content-wrapper,div.copyright-wrapper").hide();
	$("div.menu-wrapper").load("menu");
	
	$("div.header-wrapper").fadeIn(300, function(){
		$("div.menu-wrapper").show(1000, function(){
			$("div.content-wrapper").fadeIn(200, function(){
				$("div.copyright-wrapper").fadeIn(200);
			});
		});
	});
	
});