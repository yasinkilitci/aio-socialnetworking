
    <%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/Validation.css"></link>

<s:form action="Authentication/login">
	<s:textfield label="Username" key="username"/>
	<s:password label="Password" key="password"/>
	<s:submit align="left" value="Login Now" />
</s:form>

<script>
$("#login_0").button();

$("#login").validate({
    rules: {
        username: { required: true },
        password: { required: true }
    },
    messages:
    {
    	username: "'Username' field is required!",
    	password: "'Password' field is required!"
    }
});

$("#login").submit(function(e){
    return false;
});

$("#login_0").click(function () {
    var isvalid = $("#login").valid();
    if (isvalid)
        sendCreateFormViaAjax();
});

function sendCreateFormViaAjax() {
    
    var formData =
        {
            username: $("#login_username").val(),
            password: $("#login_password").val()
        };

        $.ajax({
            url: "Authentication/login",
            data: formData,
            type: "POST",
            datatype: "JSON",
            success: function (data) {
				window.location.href = "./";
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