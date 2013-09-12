package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.sourcelesslight.model.Preferences;
import org.sourcelesslight.model.Theme;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.PreferencesService;
import org.sourcelesslight.services.UserService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SignupAction extends ActionSupport implements ModelDriven<User>,ServletRequestAware, ServletResponseAware {

	private HttpServletResponse response;
	private HttpServletRequest request;
	private PreferencesService preferencesService;
	private UserService userService;
	private User user = new User();
	
	public String execute()
	{
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		
		if(StringUtils.isEmpty(user.getFirstname()))
		{
			
			HttpServletResponse lresponse = getServletResponse();
			
			try 
			{
				lresponse.getWriter().write(context.getMessage("0002",null,"Signup Error",Locale.US));
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			lresponse.setStatus(403);
			return null;
		}
		
		userService = context.getBean("UserService",UserService.class);
		preferencesService = context.getBean("PreferencesService",PreferencesService.class);
		
		Theme theme = preferencesService.getThemeById(1);
		Preferences preferences = new Preferences();
		preferences.setTheme(theme);
		
		user.setRegDate(new Date());
		user.setAuthLevel(2);
		
		preferencesService.savePreferencesForUser(user, preferences);
		
		HttpServletResponse lresponse = getServletResponse();
		lresponse.setStatus(200);
		
		try 
		{
			lresponse.getWriter().write(context.getMessage("0003",null,"Congratulations!",Locale.US));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getModel() {
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setServletRequest(HttpServletRequest request ) {
		this.request = request;
	}
	
	public HttpServletRequest getServletRequest()
	{
		return this.request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response; 
	}
	
	public HttpServletResponse getServletResponse(){
		return this.response;
	}
	
	
}
