
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
		id="cmbTheme"
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
	    	sendSettingsViaAjax();
	});

 function sendSettingsViaAjax() {
	    
	    var formData =
	        {
	            themeId: $("#cmbTheme").val(),
	            themeName: $("#cmbTheme option:selected").text()
	        };

	        $.ajax({
	            url: "ControlPanel/savePreferences",
	            data: formData,
	            type: "POST",
	            datatype: "JSON",
	            success: function (data, textStatus, request) {

	            	$("body").append("<div id='errordialog' class='dialogbox ui-helper-hidden ui-state-highlight'>" +
	                        "<p><span class='ui-icon ui-icon-info' style='float: left; margin: 0 7px 20px 0;'>" +
	                        "</span>"+ request.responseText +"</p></div>");
					$("#errordialog").dialog({
	                    title: "Successful!",
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
	                    	window.location.href = "./";
	                        $(this).dialog('destroy').remove();
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