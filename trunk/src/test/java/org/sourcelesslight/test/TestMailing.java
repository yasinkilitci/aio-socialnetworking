package org.sourcelesslight.test;

import javax.mail.MessagingException;

import org.springframework.context.support.AbstractApplicationContext;
import org.sourcelesslight.mailing.Postman;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.UserService;
import org.spring.helpers.ApplicationContextProvider;


public class TestMailing {

	
	static void testMailing()
	{
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		UserService userService = context.getBean("UserService",UserService.class);
		User user = userService.getUserByUsername("ilkercruiser");
		Postman postman = context.getBean("Postman",Postman.class);
		try {
			postman.sendConfirmationMail(user);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("SENDGRID HOST:" + System.getProperty("SENDGRID_SMTP_HOST"));
		System.out.println("SENDGRID USERNAME:" + System.getProperty("SENDGRID_USERNAME"));
		System.out.println("SENDGRID PASSWORD:" + System.getProperty("SENDGRID_PASSWORD"));
	}
	
	
	public static void main(String[] args)
	{
		testMailing();
		
	}
}
