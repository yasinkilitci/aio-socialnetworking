package org.sourcelesslight.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.UserService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class MenuProviderAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 4516184325584748750L;
	private User user = new User();
	private Map<String, Object> session; 
	
	public String execute()
	{
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		UserService us;
		Object sessionId = session.get("id");
		if(sessionId!=null)
		{
			us = context.getBean("UserService",UserService.class);
			user = us.getUserById(Integer.parseInt(sessionId.toString()));
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
