// Login.js Form Actions and JqueryUI commands for Login.jsp


$('input:text, input:password, input[type=email]')
.button().addClass('standard-textfield');

$('input[type=submit], input[type=button]')
.button();

$("#frmLogin").validate({
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

$("#frmLogin").submit(function(e){
    return false;
});

$("#btnLogin").click(function () {
    var isvalid = $("#frmLogin").valid();
    if (isvalid)
        sendCreateFormViaAjax();
});

function sendCreateFormViaAjax() {
    
	$("#btnLogin").attr("disabled", true);
	var formData =
        {
            username: $("#txtUsername").val(),
            password: $("#txtPassword").val(),
            rememberMe : $('#optRememberMe').is(':checked')
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
                            $("#btnLogin").removeAttr("disabled");
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
                            $("#btnLogin").removeAttr("disabled");
                        }
                    }).dialog('open');


		            }
            }
        });
    }