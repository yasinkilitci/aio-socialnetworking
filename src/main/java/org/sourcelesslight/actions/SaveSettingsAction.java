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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SaveSettingsAction extends ActionSupport implements ModelDriven<Theme>,LoginRequired,SessionAware,ServletResponseAware  {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = -5384546532697107457L;
	
	// Spring Dependencies
	private MessageSource messageSource;
	private PreferencesService preferencesService;
	private UserService userService;
	private Theme theme;
	// Struts2 Dependencies
	private HttpServletResponse response;
	private Map<String,Object> session;
	
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
		response.getWriter().write(messageSource.getMessage("0005",null,"Successfully Saved!", Locale.US));
		return null;
		}
		catch(HibernateException h)
		{
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toInt());
			try
			{
				response.getWriter().write(messageSource.getMessage("0004",null,"Error Saving Preferences!", Locale.US));
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

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public PreferencesService getPreferencesService() {
		return preferencesService;
	}

	public void setPreferencesService(PreferencesService preferencesService) {
		this.preferencesService = preferencesService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
