package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.sourcelesslight.model.Preferences;
import org.sourcelesslight.model.Theme;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.PreferencesService;
import org.sourcelesslight.services.UserService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaveSettingsAction extends ActionSupport implements ModelDriven<Theme>,LoginRequired,SessionAware,ServletResponseAware  {

	private AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
	private PreferencesService preferencesService;
	private UserService userService;
	private HttpServletResponse response;
	private Map<String,Object> session;
	private Theme theme = new Theme();
	
	public SaveSettingsAction(){
		preferencesService = context.getBean("PreferencesService",PreferencesService.class);
		userService = context.getBean("UserService",UserService.class);
	}
	
	
	public String execute()
	{
		try{
		Map<String, Object> parameters = this.getSession();
		int id = Integer.valueOf(parameters.get("id").toString());
		User user = userService.getUserById(id);
		Preferences pr = new Preferences();
		pr = user.getPreferences();
		pr.setTheme(this.getModel());
		preferencesService.savePreferencesForUser(user, pr);
		HttpServletResponse lresponse = this.getServletResponse();
		lresponse.setStatus(200);
		try {
			lresponse.getWriter().write(context.getMessage("0005",null,"Successfully Saved!", Locale.US));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
		}
		catch(HibernateException h)
		{
			HttpServletResponse lresponse = this.getServletResponse();
			lresponse.setStatus(502);
			try {
				lresponse.getWriter().write(context.getMessage("0004",null,"Error Saving Preferences!", Locale.US));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@Override
	public Theme getModel() {
		return theme;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
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
