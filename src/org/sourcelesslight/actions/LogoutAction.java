package org.sourcelesslight.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {

	//This prevents serializing the class to file and deserialize as a different version of class.
	private static final long serialVersionUID = 4462166867333011075L;
	
	private Map<String, Object> session;
	
	public String execute()
	{
		getSession().remove("id");
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

}
