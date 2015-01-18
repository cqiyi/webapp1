package com.hhwy.webapp1.core;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.springsupport.factory.EbeanServerFactoryBean;

public final class EbeanFactory {

	@Autowired
	private EbeanServerFactoryBean configEbeanServerFactory;

	private static Map<String, EbeanFactory> ebeans = new HashMap<String, EbeanFactory>();

	public static EbeanServer get(String dataSourceId) {
		EbeanFactory ef = ebeans.get(dataSourceId);
		if (ef == null) {
			ef = new EbeanFactory();
			ef.id = dataSourceId;
			ef.init();
			ebeans.put(dataSourceId, ef);
		}
		return ef.getEbean();
	}

	public static EbeanServer get() {
		EbeanFactory factory = new EbeanFactory();
		try {
			return factory.configEbeanServerFactory.getObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RuntimeException ex = new RuntimeException(e);
			throw ex;

		}
	}

	private EbeanFactory() {
	}

	private String id;
	private DataSource dataSource;
	private EbeanServer server;

	private EbeanServer getEbean() {
		return server;
	}

	private void init() {
		ServerConfig config = new ServerConfig();
		config.setDataSource(dataSource);
		server = EbeanServerFactory.create(config);
	}
}
