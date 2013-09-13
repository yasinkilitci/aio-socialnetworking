package org.sourcelesslight.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.sourcelesslight.actions.LoginAction;
import org.sourcelesslight.actions.LoginRequired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	 	
	
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
	 
		 	// sb: if this request does require login and the current action is
	        // not the login action, then redirect the user
	        if (!(action instanceof LoginAction)) {
	            return "loginRedirect";
	        }
			 
			return  invocation.invoke();
		}

		//called during interceptor destruction
		public void destroy() {
			System.out.println("LoginInterceptor destroy() is called...");
		}
	 
		//called during interceptor initialization
		public void init() {
			System.out.println("LoginInterceptor init() is called...");
		}

		
}
