package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.sourcelesslight.actions.enums.HttpStatus;
import org.sourcelesslight.hashing.SHA256Hasher;
import org.sourcelesslight.model.ConfirmationCode;
import org.sourcelesslight.model.Preferences;
import org.sourcelesslight.model.Theme;
import org.sourcelesslight.model.User;
import org.sourcelesslight.model.enums.AccountState;
import org.sourcelesslight.model.enums.AuthType;
import org.sourcelesslight.services.PreferencesService;
import org.sourcelesslight.services.UserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SignupAction extends ActionSupport implements ModelDriven<User>,ServletRequestAware, ServletResponseAware {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 1000L;
	private MessageSource messageSource;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private PreferencesService preferencesService;
	private UserService userService;
	private SHA256Hasher hasher;
	private User user = new User();
	
	public String execute()
	{
		try
		{
			
			if(		StringUtils.isEmpty(user.getFirstname())||
					StringUtils.isEmpty(user.getUsername())||
					StringUtils.isEmpty(user.getPassword())
					)
			{
				response.getWriter().write(messageSource.getMessage("0002",null,null,Locale.US));
				response.setStatus(HttpStatus.FORBIDDEN.toInt());
				return null;
			}
			
			Theme theme = preferencesService.getThemeById(1);
			ConfirmationCode code = new ConfirmationCode();
			Preferences preferences = new Preferences();
			
			code.setLastActionTime(new Date());
			preferences.setTheme(theme);
			user.setRegDate(new Date());
			user.setAuthLevel(AuthType.USER);
			user.setAccState(AccountState.PENDING_CONFIRMATION);
			userService.savePreferencesWithUser(user, preferences,code);
			
			response.setStatus(HttpStatus.SUCCESSFUL.toInt());
			response.getWriter().write(messageSource.getMessage("0003",null,null,Locale.US));
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

	public SHA256Hasher getHasher() {
		return hasher;
	}

	public void setHasher(SHA256Hasher hasher) {
		this.hasher = hasher;
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

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
	
}
