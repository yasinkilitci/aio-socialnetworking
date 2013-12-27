package org.sourcelesslight.aspects;

import java.util.Locale;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;

@Aspect
public class LoggingAspect {
	
	//Injected by Spring
	private MessageSource messageSource;

	/* run this method each time after the execution of public anyreturntype from this full path: 
	 * org.sourcelesslight.services.AuthenticationService.performLogin(any parameter)
	 * Joinpoint: helps us catch the parameters of the method specified
	 */
	@After("execution(public * org.sourcelesslight.services.AuthenticationService.performLogin(..))")
	public void logging_LoginAdvice(JoinPoint joinpoint){
		Object args[] = joinpoint.getArgs();
		String username = (String)args[0];
		String password = (String)args[1];
		System.err.println(messageSource.getMessage("L001",new Object[]{username,password},"Default",Locale.US));
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
}
