package org.sourcelesslight.aspects;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.sourcelesslight.model.User;

@Aspect
public class LoggingAspect {
	
	//Injected by Spring
	//private MessageSource messageSource;
	private WriteLogToFile writeLogToFile;

	/* run this method each time after the execution of public anyreturntype from this full path: 
	 * org.sourcelesslight.services.AuthenticationService.performLogin(any parameter)
	 * Joinpoint: helps us catch the parameters of the method specified
	 */
	@After("execution(public * org.sourcelesslight.services.AuthenticationService.performLogin(..))")
	public void logging_LoginAdvice(JoinPoint joinpoint){
		Object args[] = joinpoint.getArgs();
		String username = (String)args[0];
		String password = (String)args[1];
		String ip = (String)args[2];
		System.err.println("IP Address is:"+ip);
		//System.err.println(messageSource.getMessage("L001",new Object[]{username,password},Locale.US));
	}
	
	@After("execution(public * org.sourcelesslight.services.UserService.savePreferencesWithUser(..))")
	public void Logging_SignupAdvice(JoinPoint joinpoint){
		Object args[] = joinpoint.getArgs();
		User user = (User)args[0];
		//System.err.println(messageSource.getMessage("L002",new Object[]{user.getUsername(),user.getPassword()},Locale.US));
		try {
			writeLogToFile.write(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setWriteLogToFile(WriteLogToFile writeLogToFile) {
		this.writeLogToFile = writeLogToFile;
	}

//	public MessageSource getMessageSource() {
//		return messageSource;
//	}
//
//	public void setMessageSource(MessageSource messageSource) {
//		this.messageSource = messageSource;
//	}


	
	
}
