package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.sourcelesslight.actions.enums.HttpStatus;
import org.sourcelesslight.actions.interfaces.LoginRequired;
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

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = -5384546532697107457L;
	
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
		try
		{
		
		int id = Integer.valueOf(getSession().get("id").toString());
		User user = userService.getUserById(id);
		Preferences pr = user.getPreferences();
		pr.setTheme(this.getModel());
		preferencesService.updatePreferences(pr);
		response.setStatus(HttpStatus.SUCCESSFUL.toInt());
		response.getWriter().write(context.getMessage("0005",null,"Successfully Saved!", Locale.US));
		return null;
		}
		catch(HibernateException h)
		{
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toInt());
			try
			{
				response.getWriter().write(context.getMessage("0004",null,"Error Saving Preferences!", Locale.US));
			}
			catch(IOException ioe)
			{
				return null;
			}
			
		}
		catch (IOException e) 
		{
			
		}
		return null;
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
