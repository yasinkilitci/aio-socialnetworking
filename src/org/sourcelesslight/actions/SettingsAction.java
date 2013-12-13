package org.sourcelesslight.actions;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.actions.interfaces.LoginRequired;
import org.sourcelesslight.model.Theme;
import org.sourcelesslight.services.PreferencesService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
 
public class SettingsAction extends ActionSupport implements LoginRequired,SessionAware,ServletResponseAware{
 
	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 1000L;
	
	private AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
	private PreferencesService preferencesService;
	private HttpServletResponse response;
	private Map<String,Object> session;
	private ArrayList<Theme> themes;
	
	public SettingsAction(){
 
		preferencesService = context.getBean("PreferencesService",PreferencesService.class);
		themes = preferencesService.getAllThemes();
	}
 
	public String execute() {
		return SUCCESS;
	}
 
	public String display() {
		return NONE;
	}
 
	public ArrayList<Theme> getThemes() {
		return themes;
	}

	public void setThemes(ArrayList<Theme> themes) {
		this.themes = themes;
	}
	
	public String populate()
	{
		preferencesService = context.getBean("PreferencesService",PreferencesService.class);
		themes = preferencesService.getAllThemes();
		return "populate";
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getServletResponse(){
		return this.response;
	}
	
}