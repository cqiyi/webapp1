package com.hhwy.svnlog.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApplicationInitListener implements ServletContextListener {

	public static Log logger = LogFactory.getLog(ApplicationInitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

		System.out.println("contextDestroyed ...");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// Message.getContext().loadProperties(arg0.getServletContext());
		// ApplicationBean.initVersionMD5();
		Installed.execute();
	}

}
