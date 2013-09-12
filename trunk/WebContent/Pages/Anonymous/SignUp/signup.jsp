<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
     
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/Validation.css"></link>
     
<s:form action="Registration/Signup" id="frmSignup">
	<s:textfield label="Username" key="username"/>
	<s:password label="Password" key="password"/>
	<s:password label="Password(Repeat)" key="password2"/>
	<s:textfield label="First Name" key="firstname"/>
	<s:textfield label="Last Name" key="lastname"/>
	<s:textfield label="E-mail" key="email"/>
	<s:submit align="left" value="SignUp Now!" id="btnSubmit" />
</s:form>

<script>
$("#btnSubmit").button();

$("#frmSignup").validate({
    rules: {
        firstname: { required: true },
        lastname: { required: true },
        email: { required: true, email: true },
        username: { required: true, minlength:6, maxlength: 20 },
        password: { required: true, minlength: 6, maxlength: 20 },
        password2: { required: true, equalTo: "#frmSignup_password"}
    },
    messages:
    {
        firstname: "First Name is required!",
        lastname: "Last Name is required!",
        email: {required:"Email is required" , email:"Enter a valid email address!"},
        username: {	required: "Username is required!", 
            		minlength:"Username must be min {0} characters long!",
            		maxlength:"Username must be max {0} characters long!"},
   		password: {	required: "Password is required!", 
       				minlength:"Password must be min {0} characters long!",
       				maxlength:"Password must be max {0} characters long!"},
		password2: {	required: "Password(Repeat) is required!", 
  				minlength:"Password must be min {0} characters long!",
  				maxlength:"Password must be max {0} characters long!"},
    }
});

$("#frmSignup").submit(function(e){
    return false;
});

$("#btnSubmit").click(function () {
    var isvalid = $("#frmSignup").valid();
    if (isvalid)
        sendCreateFormViaAjax();
});

function sendCreateFormViaAjax() {
    
    var formData =
        {
            firstname: $("#frmSignup_firstname").val(),
            lastname: $("#frmSignup_lastname").val(),
            email: $("#frmSignup_email").val(),
            username: $("#frmSignup_username").val(),
            password: $("#frmSignup_password").val()
        };

        $.ajax({
            url: "Registration/signup",
            data: formData,
            type: "POST",
            datatype: "JSON",
            success: function (data, textStatus, request) {

            	$("body").append("<div id='errordialog' class='dialogbox ui-helper-hidden ui-state-highlight'>" +
                        "<p><span class='ui-icon ui-icon-info' style='float: left; margin: 0 7px 20px 0;'>" +
                        "</span>"+ request.responseText +"</p></div>");
				$("#errordialog").dialog({
                    title: "Welcome!",
                    height: 190,
                    width: 350,
                    modal: true,
                    resizable: false,
                    draggable: false,
                    show: {
                        effect: "blind",
                        duration: 350
                    },
                    hide: {
                        effect: "fade",
                        duration: 250
                    },
                    buttons: {
                        "OK": function () {
                            $(this).dialog("close");
                        }
                    },
                    open: function (event, ui) { $('.ui-widget-overlay').bind('click', function () { $("#errordialog").dialog('close'); }); },
                    close: function () {
                        $(this).dialog('destroy').remove();
                        window.location.href = "./";
                    }
                }).dialog('open');

            },
            error: function (request, textStatus, errorThrown) {
                if (errorThrown == "Forbidden") {

                	$("body").append("<div id='errordialog' class='dialogbox ui-helper-hidden'>" +
                            "<p><span class='ui-icon ui-icon-alert' style='float: left; margin: 0 7px 20px 0;'>" +
                            "</span><strong>Error: </strong>"+ request.responseText +"</p></div>");
					$("#errordialog").dialog({
                        title: "Access Denied",
                        height: 170,
                        width: 350,
                        modal: true,
                        resizable: false,
                        draggable: false,
                        show: {
                            effect: "blind",
                            duration: 350
                        },
                        hide: {
                            effect: "fade",
                            duration: 250
                        },
                        buttons: {
                            "OK": function () {
                                $(this).dialog("close");
                            }
                        },
                        open: function (event, ui) { $('.ui-widget-overlay').bind('click', function () { $("#errordialog").dialog('close'); }); },
                        close: function () {
                            $(this).dialog('destroy').remove();
                        }
                    }).dialog('open');

                }
                else
                   {
                	$("body").append("<div id='errordialog' class='dialogbox ui-helper-hidden'>" +
                            "<p><span class='ui-icon ui-icon-alert' style='float: left; margin: 0 7px 20px 0;'>" +
                            "</span>Error: "+errorThrown+" Error occured while sending request!</p></div>");
					$("#errordialog").dialog({
                        title: "Caution",
                        height: 170,
                        width: 350,
                        modal: true,
                        resizable: false,
                        draggable: false,
                        show: {
                            effect: "blind",
                            duration: 350
                        },
                        hide: {
                            effect: "fade",
                            duration: 250
                        },
                        buttons: {
                            "OK": function () {
                                $(this).dialog("close");
                            }
                        },
                        open: function (event, ui) { $('.ui-widget-overlay').bind('click', function () { $("#errordialog").dialog('close'); }); },
                        close: function () {
                            $(this).dialog('destroy').remove();
                        }
                    }).dialog('open');
							


                    }
            }
        });
    }

</script>