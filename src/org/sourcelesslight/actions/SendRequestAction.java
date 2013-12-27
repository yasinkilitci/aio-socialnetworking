package org.sourcelesslight.actions;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.sourcelesslight.actions.enums.HttpStatus;
import org.sourcelesslight.actions.interfaces.LoginRequired;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.FriendRequestService;
import org.sourcelesslight.services.UserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SendRequestAction extends ActionSupport implements SessionAware, ServletResponseAware, LoginRequired {

	private static final long serialVersionUID = 3158587621861280485L;
	
	private HttpServletResponse response;
	private MessageSource messageSource;
	private Map<String, Object> session;
	private UserService userService;
	private FriendRequestService friendRequestService;
	private String friendNick;
	
	public SendRequestAction()
	{
		
	}
	
	public String execute()
	{
		try
		{
			Object sessionId = session.get("id");
			if(sessionId!=null && !StringUtils.isEmpty(friendNick))
			{
				int userId = Integer.valueOf(sessionId.toString());
				User userSender = userService.getUserById(userId);
				User userReceiver = userService.getUserByUsername(friendNick);
				friendRequestService.sendRequest(userSender, userReceiver);
			}
			else
			{
				response.getWriter().write(messageSource.getMessage("0002",null,"Failed!", Locale.US));
			}
			
		}
		catch(HibernateException e)
		{
			response.setStatus(HttpStatus.CONFLICT.toInt());
			return null;
		}
		catch(IOException ioe)
		{
			response.setStatus(HttpStatus.CONFLICT.toInt());
			return null;
		}
		return SUCCESS;
	}



	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Map<String, Object> getSession()
	{
		return session;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
	public String getFriendNick() {
		return friendNick;
	}

	public void setFriendNick(String friendNick) {
		this.friendNick = friendNick;
	}

	public FriendRequestService getFriendRequestService() {
		return friendRequestService;
	}

	public void setFriendRequestService(FriendRequestService friendRequestService) {
		this.friendRequestService = friendRequestService;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getServletResponse()
	{
		return response;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
	
}
