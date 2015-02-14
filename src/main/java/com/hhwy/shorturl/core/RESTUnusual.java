package com.hhwy.shorturl.core;

import com.alibaba.fastjson.JSON;
import com.hhwy.shorturl.core.Utility;

public class RESTUnusual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605081281034364064L;

	private int code;
	private String message;

	protected RESTUnusual() {
	}

	protected RESTUnusual(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, Utility.defaultJsonFeatures);
	}

	public final static RESTUnusual UNKWON = new RESTUnusual(999, "未知的异常");

	public final static RESTUnusual AUTHENTICATION_FAILS = new RESTUnusual(99, "安全认证失败");

	public final static RESTUnusual SHORT_URL_ALIAS_EXISTED = new RESTUnusual(9, "短网址已存在");

}
