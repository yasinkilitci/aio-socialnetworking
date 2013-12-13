package org.sourcelesslight.actions;

import java.util.List;
import java.util.Map;

import org.sourcelesslight.actions.interfaces.LoginRequired;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.UserService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class FriendFinderAction extends ActionSupport implements LoginRequired {

	private static final long serialVersionUID = 7089836153796088129L;
	
	private List<User> friendList;
	private AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
	private UserService userService;
	
	public FriendFinderAction()
	{
		userService = context.getBean("UserService",UserService.class);
	}
	
	public String execute()
	{
		friendList = userService.getAllUsers();
		return SUCCESS;
	}

	public List<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}
	
	

}
