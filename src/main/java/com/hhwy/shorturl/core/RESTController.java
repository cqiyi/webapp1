package com.hhwy.shorturl.core;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.text.json.JsonContext;

public class RESTController {

	protected JdbcTemplate getJdbcTemplate(){
		return Installed.getJdbcTemplate();
	}
	protected EbeanServer getEbean() {
		return Installed.getEbean();
	}

	private JsonContext jc;

	protected JsonContext getJsonContext() {
		if (jc == null) {
			jc = getEbean().createJsonContext();
		}
		return jc;
	}

	/*
	 * ��REST�ӿڵĿͻ������е��쳣���ظ�ʽ
	 */
	@ExceptionHandler(value = { Exception.class })
	public String showException(Exception ex) {
		return ex instanceof RESTException ? ex.toString() : RESTException.UNKWON.toString();
	}
}
