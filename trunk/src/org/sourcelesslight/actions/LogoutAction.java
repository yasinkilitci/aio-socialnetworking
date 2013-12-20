package org.sourcelesslight.actions;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 4462166867333011075L;
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public String execute()
	{
		session.remove("id");
		
		for(Cookie c : request.getCookies()) {
		      if (c.getName().equals("MPUSERID"))
		      {
		    	  c.setMaxAge(0);
		    	  c.setPath("/SocialNetworking/");
		    	  response.addCookie(c);
		    	  break;
		      }
		    }
		
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Map<String, Object> getSession()
	{
		return this.session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
}
