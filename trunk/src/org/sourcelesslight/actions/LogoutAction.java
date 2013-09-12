package org.sourcelesslight.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware, SessionAware {

	// Retrieve request and session objects, any changes made to these will be reflected to actual ones.
	private HttpServletRequest request;
	private Map<String, Object> session;
	
	public String execute()
	{
		Map<String, Object> parameters = this.getSession();
		parameters.remove("id");
		System.out.println("SUCCESSFULLY LOGGED OUT!");
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

}
