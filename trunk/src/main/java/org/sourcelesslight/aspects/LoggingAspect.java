package org.sourcelesslight.aspects;

import java.util.Locale;




import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.sourcelesslight.model.User;
import org.springframework.context.MessageSource;

@Aspect
public class LoggingAspect {
	
	//Injected by Spring
	private MessageSource messageSource;
	private Logger logger;
	
	/* run this method each time after the execution of public anyreturntype from this full path: 
	 * org.sourcelesslight.services.AuthenticationService.performLogin(any parameter)
	 * Joinpoint: helps us catch the parameters of the method specified
	 */
//	@AfterReturning(value = "@target(org.sourcelesslight.services.AuthenticationService) && !execution(* performLogin*(..))", returning = "returnValue")
	@After("execution(public * org.sourcelesslight.services.AuthenticationService.performLogin(..))")
	public void logging_LoginAdvice(JoinPoint joinpoint){
				Object args[] = joinpoint.getArgs();
		 		String username = (String)args[0];
		 		String password = (String)args[1];
		 		String ip = (String)args[2];
		 		logger.warn(messageSource.getMessage("L001",new Object[]{username,password,ip},Locale.US));
			}
	
	@After("execution(public * org.sourcelesslight.services.UserService.savePreferencesWithUser(..))")
	public void Logging_SignupAdvice(JoinPoint joinpoint ){
		Object args[] = joinpoint.getArgs();
		User user = (User)args[0];
		//http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html
		// warn / fatal / trace 
		logger.fatal(messageSource.getMessage("L002",new Object[]{user.getUsername(),user.getPassword()},Locale.US));

	}
	
//	@After("execution(public *  org.sourcelesslight.actions.LogoutAction.session.remove(..))")
//	public void Logging_LogoutAdvice(JoinPoint joinpoint){
//		
//		Object args[]=joinpoint.getArgs();
//		User user = (User)args[0];
//		logger.info(messageSource.getMessage("L003",new Object[]{user.getUsername(),user.getPassword()},Locale.US));
//
//	}
	

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	
}
