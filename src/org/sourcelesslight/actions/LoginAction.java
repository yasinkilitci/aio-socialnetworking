package org.sourcelesslight.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.AuthenticationService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware {

	// Parameters
	private String username;
	private String password;
	// Parameters
	
	private AuthenticationService authService; 
	private HttpServletRequest request;
	
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
					System.out.println("SUCCESSFULLY LOGGED IN!");
					return SUCCESS;
				}
				else
				{
					System.err.println("LOGIN FAILED!");
					return "failure";
				}
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
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
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
}
