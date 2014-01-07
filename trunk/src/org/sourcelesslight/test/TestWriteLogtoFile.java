package org.sourcelesslight.test;

import java.io.IOException;

import org.sourcelesslight.aspects.WriteLogToFile;
import org.sourcelesslight.model.User;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

public class TestWriteLogToFile {
	
	
	static void testWriteToFile(){
		
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		WriteLogToFile wtf = context.getBean("LoggingToFile",WriteLogToFile.class);
		User user = new User();
		user.setUsername("username_test");
		user.setPassword("password_test");
		user.setEmail("email_test");
		try {
			wtf.write(user);
			System.out.println("logging successful!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)
	{
		testWriteToFile();
		
	}
	
}