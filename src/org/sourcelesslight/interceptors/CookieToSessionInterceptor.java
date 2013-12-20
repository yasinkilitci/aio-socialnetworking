package org.sourcelesslight.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CookieToSessionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -2593432061357423686L;

	// This interceptor checks for a cookie named "MPUSERID" and sets a session value
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		// get the session information
	     HttpSession session = ServletActionContext.getRequest().getSession();
	     // check the cookies, if a cookie named mpuserid exists set this as sessionID
     	HttpServletRequest request = ServletActionContext.getRequest();
     	Cookie[] cookies = request.getCookies();
     	
     	if(cookies!=null) 
	    	for(int i=0;i<cookies.length;i++)
			{
				if(StringUtils.equals("MPUSERID", cookies[i].getName()))
				{
					session.setAttribute("id", cookies[i].getValue());
					return invocation.invoke();
				}
			}
     	
		return invocation.invoke();
	}

}
