package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.AuthenticationService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware,ServletResponseAware {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 1000L;
	
	// Parameters
	private String username;
	private String password;
	// Parameters
	
	// Get an actual user record from database with current username and password parameters
	private AuthenticationService authService;
	
	// Retrieve request and session objects, any changes made to these will be reflected to actual ones.
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> session;
	
	public String execute()
	{
		String method = request.getMethod();
		if(method == "POST")
		{
			if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			{
				return "failure";
			}
			
			AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
			authService = context.getBean("AuthenticationService",AuthenticationService.class);
			User user = authService.performLogin(username, password);
				if(user!=null)
				{
					/* Successful Login */
					Map<String, Object> parameters = this.getSession();
					parameters.put("id", user.getUserId());
					HttpServletResponse lresponse = this.getServletResponse();
					lresponse.setStatus(200);
					return SUCCESS;
				}
				else
				{
					/* Failed Login */
					HttpServletResponse lresponse = this.getServletResponse();
					try 
					{
						lresponse.getWriter().write(context.getMessage("0001",null,null,Locale.US));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					
					lresponse.setStatus(403);
					return null;
				}
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getServletResponse()
	{
		return this.response;
	}
}
