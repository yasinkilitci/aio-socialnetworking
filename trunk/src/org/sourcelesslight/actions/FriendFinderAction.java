package org.sourcelesslight.actions;

import java.util.List;

import org.sourcelesslight.actions.interfaces.LoginRequired;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.UserService;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class FriendFinderAction extends ActionSupport implements LoginRequired {

	private static final long serialVersionUID = 7089836153796088129L;
	
	private List<User> friendList;
	private UserService userService;
	
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
