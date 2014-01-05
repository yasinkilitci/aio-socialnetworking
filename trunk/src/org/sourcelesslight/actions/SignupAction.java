package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.sourcelesslight.actions.enums.HttpStatus;
import org.sourcelesslight.hashing.SHA256Hasher;
import org.sourcelesslight.mailing.Postman;
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
import com.sun.mail.util.MailConnectException;

@Controller
public class SignupAction extends ActionSupport implements ModelDriven<User>,ServletRequestAware, ServletResponseAware {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 1000L;
	
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	//Injected Dependencies ( we did not use "new" keyword )
	private MessageSource messageSource;
	private PreferencesService preferencesService;
	private UserService userService;
	private SHA256Hasher hasher;
	private User user;
	private Preferences preferences;
	private ConfirmationCode confirmationCode;
	private Postman postman;
	
	public String execute() throws IOException
	{
		try
		{
			
				if	(		
					user.getFirstname().isEmpty()||
					user.getUsername().isEmpty()||
					user.getPassword().isEmpty()
					)
			{
				response.getWriter().write(messageSource.getMessage("0002",null,Locale.US));
				response.setStatus(HttpStatus.FORBIDDEN.toInt());
				return null;
			}
			
			Theme theme = preferencesService.getThemeById(1);
			Date date = new Date();//now
			confirmationCode.setLastActionTime(date);
			String code = hasher.encrypt(user.getPassword());
			confirmationCode.setCode(code);
			preferences.setTheme(theme);
			user.setRegDate(date);
			user.setAuthLevel(AuthType.USER);
			user.setConfirmationCode(confirmationCode);
			user.setPreferences(preferences);
			//try to send the mail
			postman.sendConfirmationMail(user);
			
			// if mail send is successful, set account state as "pending_confirm"
			user.setAccState(AccountState.PENDING_CONFIRMATION);
			userService.savePreferencesWithUser(user);
			response.setStatus(HttpStatus.SUCCESSFUL.toInt());
			response.getWriter().write(messageSource.getMessage("0003",null,Locale.US));
		}
		catch(MailConnectException m)
		{
			// if mail dispatch is failed, set account state as "CONFIRMATION_NOT_SEND"
			user.setAccState(AccountState.CONFIRMATION_NOT_SEND);
			userService.savePreferencesWithUser(user);
			response.getWriter().write(messageSource.getMessage("0006",null,Locale.US));
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

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	public ConfirmationCode getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(ConfirmationCode confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public Postman getPostman() {
		return postman;
	}

	public void setPostman(Postman postman) {
		this.postman = postman;
	}
	
	
}
