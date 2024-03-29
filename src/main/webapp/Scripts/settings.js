// Javascript file for Settings.jsp

$('input[type=submit], input[type=button]').button();

 $('select').addClass('ui-selectonemenu ui-widget ui-state-default ui-corner-all ui-helper-clearfix');

 $("#formSettings").validate({
	    rules: {
	    	themeBox: { required: true }
	    },
	    messages:
	    {
	    	themeBox: "Please select a theme!"
	    }
	});

 $("#formSettings").submit(function(e){
	    return false;
	});

 $("#formSettings_submit").click(function () {
	    var isvalid = $("#formSettings").valid();
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
 