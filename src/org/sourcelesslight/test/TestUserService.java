package org.sourcelesslight.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.spring.helpers.ApplicationContextProvider;
import org.sourcelesslight.services.UserService;
import java.util.List;
import org.sourcelesslight.model.User;

public class TestUserService {
	
	static void testUserService()
	{
		AbstractApplicationContext context = ApplicationContextProvider.getApplicationContext();
		UserService userService = context.getBean("UserService",UserService.class);
		//userService.refreshIndexes();
		List<User> users = userService.searchUser("ilke");
		
		System.out.println("Query executed!");
		for (User user : users)
		{
			System.out.println(user.getUsername() + " Found!");
		}
	}
	
	
	public static void main(String[] args)
	{
		testUserService();
		
	}
	
}
