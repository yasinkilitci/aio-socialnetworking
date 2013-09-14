package org.sourcelesslight.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware, SessionAware {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 1000L;
	
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
	}

}
