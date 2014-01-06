
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/Scripts/menu.js" /></script>

<div id="menu-css">
	<div class="menu-logo"></div>
	<div class="menu-buttons">
		<ul>
			<li><a href="#" id="lnkHome">Home</a></li>
			<!-- Things to do for ordinary user-->
			<s:if
				test="user.authLevel==@org.sourcelesslight.model.enums.AuthType@USER">
				<script>
					$(function() {
						$('div.menu-wrapper').css('background','rgba(120,0,0,1)');
						$("div.content-wrapper,div.copyright-wrapper")
						.css("background", "rgba(255,150,0,0.6)");
					});
				</script>
				<li><a href="#" id="lnkDiscover">Discover</a></li>
				<li><a href="#" id="lnkFind">Find</a></li>
				<li><a href="#" id="lnkMyProfile">My Profile</a></li>

			</s:if>
			<!-- Things to do for Administrator-->
			<s:elseif
				test="user.authLevel==@org.sourcelesslight.model.enums.AuthType@ADMIN">
				<script>
					$(function() {
						$("div.menu-wrapper,div.content-wrapper,div.copyright-wrapper")
							.css("background", "rgba(255,150,0,0.6)");
					});
				</script>
				<li><a href="#" id="lnkDiscover">Manage Users</a></li>
				<li><a href="#" id="lnkTools">Administrative Tools</a></li>
			</s:elseif>
			<!-- Things to do for Moderator-->
			<s:elseif
				test="user.authLevel==@org.sourcelesslight.model.enums.AuthType@MODERATOR">

			</s:elseif>
			<!-- Things to do for "visitors" -->
			<s:else>
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/Styles/themes/ui-lightness/jquery-ui.css"></link>
				<script>
					$(function() {
						$("body").css("background", "rgba(0,128,255,1)");
						$("div.menu-wrapper,div.content-wrapper,div.copyright-wrapper")
						.css("background", "rgba(255,150,0,1)");
						$("div.content-wrapper").load("./Pages/Anonymous/Authentication/login.jsp");
					});
				</script>
				<li><a href="#" id="lnkAbout">About</a></li>
				<li><a href="#" id="lnkDonate">Donate</a></li>
				<li><a href="#" id="lnkLogIn">Log In</a></li>
				<li><a href="#" id="lnkSignUp">Sign Up Now!</a></li>
			</s:else>
			<!-- If any user logged in, settings and logout buttons will be shown -->
			<s:if test="user.userId > 0">
				<li><a href="#" id="lnkSettings">Settings</a></li>
				<li><a href="<s:url action="Authentication/logout"/>">Log
						Out</a></li>
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}<s:text name="format.theme">
    		<s:param name="value" value="user.preferences.theme.themeName" />
		</s:text>
  		"></link>
			</s:if>

		</ul>
	</div>
</div>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Styles/maintheme.css"></link>