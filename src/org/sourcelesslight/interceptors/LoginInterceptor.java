package org.sourcelesslight.interceptors;

import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;
import org.sourcelesslight.actions.LoginAction;
import org.sourcelesslight.actions.interfaces.LoginRequired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 INTERCEPTOR TERMINOLOGY (STRUTS2)
	ActionInvocation(Class) 	:	   represents the execution state of an Action. 
	It holds the Interceptors and the Action instance. By repeated re-entrant execution 
	of the invoke() method, initially by the ActionProxy, then by the Interceptors, the 
	Interceptors are all executed, and then the Action and the Result.
  */

public class LoginInterceptor extends AbstractInterceptor {
	
		//This prevents serializing the class to file and deserialize as a different version of class.
		private static final long serialVersionUID = 1000L;
		
		@Override
		public String intercept(ActionInvocation invocation) throws Exception {
			
			// get the session information
		     Map<String, Object> session = ActionContext.getContext().getSession();
		     
			// if the user is already signed-in, then let the request through.
			if(session.get("id")!=null)
			{
				return invocation.invoke();
			}
		
			// if the action doesn't require sign-in, then let it through.
			Object action = invocation.getAction();
			 if (!(action instanceof LoginRequired)) {
		            return invocation.invoke();
		        }
	 
			 
			 
			 // if the action is loginAction let it through
	        if (action instanceof LoginAction)
	            return invocation.invoke();
	        else 
	        {
	        	Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			    if(cookies!=null) 
	        	for(int i=0;i<cookies.length;i++)
			    	 if (cookies[i].getName()=="cookie_id")
					    {
					     session.put("id", cookies[i].getValue());
					     return invocation.invoke();
					    }
			  // checkthecookies "id" cookie not found redirect the user to homepage
	        	return "loginRedirect";
	        }
		}

		//called during interceptor destruction
		public void destroy() {
			//System.out.println("LoginInterceptor destroy() is called...");
		}
	 
		//called during interceptor initialization
		public void init() {
			//System.out.println("LoginInterceptor init() is called...");
		}

}
