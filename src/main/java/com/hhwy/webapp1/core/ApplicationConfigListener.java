package com.hhwy.webapp1.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ApplicationConfigListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("contextDestroyed ...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("contextInitialized ...");
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		System.out.println(context);
	}

}

