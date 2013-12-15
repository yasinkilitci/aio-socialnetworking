package org.sourcelesslight.aspects;

import java.util.Locale;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

@Aspect
public class LoggingAspect {
	
	private AbstractApplicationContext context;

	/* run this method each time after the execution of public anyreturntype from this full path: 
	 * org.sourcelesslight.services.AuthenticationService.performLogin(any parameter)
	 * Joinpoint: helps us catch the parameters of the method specified
	 */
	@After("execution(public * org.sourcelesslight.services.AuthenticationService.performLogin(..))")
	public void logging_LoginAdvice(JoinPoint joinpoint){
		Object args[] = joinpoint.getArgs();
		String username = (String)args[0];
		String password = (String)args[1];
		context = ApplicationContextProvider.getApplicationContext();
		System.err.println(context.getMessage("L001",new Object[]{username,password},"Default",Locale.US));
	}
}
