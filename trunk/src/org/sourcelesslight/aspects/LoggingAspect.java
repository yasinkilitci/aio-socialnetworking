package org.sourcelesslight.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

	/* run this method each time after the execution of public anyreturntype from this full path: 
	 * org.sourcelesslight.services.AuthenticationService.performLogin(any parameter)
	 * Joinpoint: helps us catch the parameters of the method specified
	 */
	@After("execution(public * org.sourcelesslight.services.AuthenticationService.performLogin(..))")
	public void logging_LoginAdvice(JoinPoint joinpoint){
		Object args[] = joinpoint.getArgs();
		String username = (String)args[0];
		String password = (String)args[1];
		System.out.println("A Client tried to log in with the username: "+username+" and the password:" + password);
	}
}
