package com.hhwy.webapp1.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.springsupport.factory.EbeanServerFactoryBean;
import com.avaje.ebean.text.json.JsonContext;

@Controller
public class RESTController {

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

	/*
	 * 向REST接口的客户端所有的异常返回格式
	 */
	@ExceptionHandler(value = { Exception.class })
	public String showException(Exception ex) {
		return ex instanceof RESTException ? ex.toString() : RESTException.UNKWON.toString();
	}
}
