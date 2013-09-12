package org.sourcelesslight.actions;

import java.util.ArrayList;
import java.util.List;

import org.sourcelesslight.model.Theme;
import org.sourcelesslight.services.AuthenticationService;
import org.sourcelesslight.services.PreferencesService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;
 
import com.opensymphony.xwork2.ActionSupport;
 
public class SettingsAction extends ActionSupport{
 
	private ArrayList<Theme> themes;
	private PreferencesService preferencesService;

	public SettingsAction(){
 
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
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
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		preferencesService = context.getBean("PreferencesService",PreferencesService.class);
		themes = preferencesService.getAllThemes();
		
		return "populate";
	}
	
	
}