package org.sourcelesslight.test;

import org.sourcelesslight.mailing.Postman;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.UserService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.sun.mail.util.MailConnectException;

public class TestMailing {

	
	static void testMailing()
	{
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		UserService userService = context.getBean("UserService",UserService.class);
		User user = userService.getUserByUsername("akiner41");
		Postman postman = context.getBean("Postman",Postman.class);
		try {
			postman.sendConfirmationMail(user);
		} catch (MailConnectException e) {
			
			System.out.println("cannot connect to mail server");
		}
		
	}
	
	
	public static void main(String[] args)
	{
		testMailing();
		
	}
}
