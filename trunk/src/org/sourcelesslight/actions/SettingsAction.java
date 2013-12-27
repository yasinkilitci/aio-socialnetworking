package org.sourcelesslight.actions;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.actions.interfaces.LoginRequired;
import org.sourcelesslight.model.Theme;
import org.sourcelesslight.services.PreferencesService;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
 
@Controller
public class SettingsAction extends ActionSupport implements LoginRequired,SessionAware,ServletResponseAware{
 
	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 1000L;
	private PreferencesService preferencesService;
	private HttpServletResponse response;
	private Map<String,Object> session;
	private ArrayList<Theme> themes;
	
	public SettingsAction(){
 
	}
 
	public String execute() {
		themes = preferencesService.getAllThemes();
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

	public PreferencesService getPreferencesService() {
		return preferencesService;
	}

	public void setPreferencesService(PreferencesService preferencesService) {
		this.preferencesService = preferencesService;
	}
	
	
	
}