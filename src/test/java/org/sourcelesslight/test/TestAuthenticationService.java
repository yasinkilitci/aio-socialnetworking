package org.sourcelesslight.test;

import org.sourcelesslight.model.User;
import org.sourcelesslight.services.AuthenticationService;
import org.spring.helpers.ApplicationContextProvider;
import org.springframework.context.support.AbstractApplicationContext;

public class TestAuthenticationService {

	static void testAuthenticationService()
	{
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		AuthenticationService authService = context.getBean("AuthenticationService",AuthenticationService.class);
		User user = authService.performLogin("ilkercruiser", "123456", "127.0.0.1");
		if(user!=null)
		{
			System.out.println("Login successful!");
			System.out.println(user.getConfirmationCode().getCode());
			System.out.println(user.getPreferences().getTheme().getThemeName());
		}
		
	}
	
	public static void main(String[] args) {
		
		testAuthenticationService();
	}

}
