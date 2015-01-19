package com.hhwy.webapp1.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.springsupport.factory.EbeanServerFactoryBean;

public abstract class BaseController {

	@Autowired
	private EbeanServerFactoryBean ebean;

	public EbeanServer getEbean() {
		try {
			return ebean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			RuntimeException ex = new RuntimeException(e);
			throw ex;
		}
	}
}
