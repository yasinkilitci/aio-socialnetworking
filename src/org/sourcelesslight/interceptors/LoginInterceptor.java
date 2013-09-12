package org.sourcelesslight.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

		@Override
		public String intercept(ActionInvocation invocation) throws Exception {
			System.out.println("CustomInterceptor, before invocation.invoke()...");
			 
			String result = invocation.invoke();
	 
			System.out.println("CustomInterceptor, after invocation.invoke()...");
	 
			return result;
		}

		//called during interceptor destruction
		public void destroy() {
			System.out.println("CustomInterceptor destroy() is called...");
		}
	 
		//called during interceptor initialization
		public void init() {
			System.out.println("CustomInterceptor init() is called...");
		}
	 
}
