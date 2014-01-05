package org.sourcelesslight.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.UserService;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class MenuProviderAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 4516184325584748750L;
	
	//Spring Dependencies
	private User user;
	private UserService userService;
	//Session is injected by Struts2
	private Map<String, Object> session; 
	
	
	public String execute()
	{
		Object sessionId = session.get("id");
		if(sessionId!=null)
		{
			user = userService.getUserById(Integer.parseInt(sessionId.toString()));
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
}
