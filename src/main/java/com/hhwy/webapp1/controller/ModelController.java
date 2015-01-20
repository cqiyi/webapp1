package com.hhwy.webapp1.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.springsupport.factory.EbeanServerFactoryBean;
import com.avaje.ebean.text.json.JsonContext;

public abstract class ModelController {

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

	private JsonContext jsonContext;

	public JsonContext getJsonContext() {
		if (jsonContext == null) {
			jsonContext = getEbean().createJsonContext();
		}
		return jsonContext;
	}
}
