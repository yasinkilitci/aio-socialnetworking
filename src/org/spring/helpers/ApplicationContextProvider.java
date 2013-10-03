package org.spring.helpers;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProvider {

	private static final AbstractApplicationContext context = buildApplicationContext();
	
	private static AbstractApplicationContext buildApplicationContext()
	{
		return new ClassPathXmlApplicationContext("/main/resources/spring.postgres.cfg.xml");
	}
	
	public static AbstractApplicationContext getApplicationContext()
	{
		return context;
	}
}
