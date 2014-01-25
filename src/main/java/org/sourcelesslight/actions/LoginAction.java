package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.actions.enums.HttpStatus;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.AuthenticationService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware,ServletResponseAware,CookiesAware {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 1000L;
	
	//Dependencies
	// Get an actual user record from database with current username and password parameters
	private AuthenticationService authenticationService;
	private MessageSource messageSource;
	//Dependencies
	
	// Parameters
	private String username;
	private String password;
	private boolean rememberMe;
	// Parameters
	
	// Retrieve request,response,session, cookies objects, any changes made to these will be reflected to actual ones.
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> session;
	private Map<String, String> cookies;
	
	public String execute()
	{
		try
		{
			String method = request.getMethod();
			if(method == "POST")
			{
				
				if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
				{
					response.getWriter().write(messageSource.getMessage("0001",null,null,Locale.US));
					response.setStatus(HttpStatus.FORBIDDEN.toInt());
					return null;
				}
				
				// is client behind something?
				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
					ipAddress = request.getRemoteHost();
				}
				User user = authenticationService.performLogin(username, password,ipAddress);
					if(user!=null)
					{
						/* Successful Login */
						session.put("id", user.getUserId());
						
						//set cookies if rememberMe is "true"
						if(rememberMe)
						{
							Cookie cookie_id = new Cookie("MPUSERID",String.valueOf(user.getUserId()));
							cookie_id.setMaxAge(60*60*24*365);
							//cookie_id.setDomain("magnepal.com");
							// When using locally set this to contextpath like "/magnepal/"
							// when using on server set path to "/"
							cookie_id.setPath(request.getContextPath()+"/");
							response.addCookie(cookie_id);
						}
						
						response.setStatus(HttpStatus.SUCCESSFUL.toInt());
						return SUCCESS;
					}
					else
					{
						/* Failed Login */
						response.getWriter().write(messageSource.getMessage("0001",null,null,Locale.US));
						response.setStatus(HttpStatus.FORBIDDEN.toInt());
						return null;
					}
			}
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Map<String, Object> getSession() {
		return this.session;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getServletResponse()
	{
		return this.response;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setCookiesMap(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public Map<String, String> getCookiesMap() {
		return cookies;
	}

	
	
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}



	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}



	public MessageSource getMessageSource() {
		return messageSource;
	}



	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
	
}
