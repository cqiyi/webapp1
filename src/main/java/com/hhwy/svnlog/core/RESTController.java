package com.hhwy.svnlog.core;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.text.json.JsonContext;

public class RESTController {

	protected JdbcTemplate getJdbcTemplate() {
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

	protected ApplicationContext getCurrentContext() {
		return Installed.getCurrentContext();
	}

	protected String getMessage(String code, Object[] args) {
		return Installed.getCurrentContext().getMessage(code, args, Locale.CHINA);
	}
	protected String getMessage(String code) {
		return Installed.getCurrentContext().getMessage(code, null, Locale.CHINA);
	}

	/*
	 * 向REST接口的客户端所有的异常返回格式
	 */
	@ExceptionHandler(value = { Exception.class })
	public @ResponseBody String showException(Exception ex) {
		ex.printStackTrace();
		return RESTUnusual.UNKWON.toString();
	}
}
