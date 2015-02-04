package com.hhwy.webapp1.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.springsupport.factory.EbeanServerFactoryBean;
import com.avaje.ebean.text.json.JsonContext;

public class ModelController {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	private EbeanServerFactoryBean ebeanServerFactory;

	public EbeanServer getEbean() {
		try {
			return ebeanServerFactory.getObject();
		} catch (Exception e) {
			throw Utility.wrapRuntimeException(e);
		}
	}

	private JsonContext jc;

	public JsonContext getJsonContext() {
		if (jc == null) {
			jc = getEbean().createJsonContext();
		}
		return jc;
	}
}
