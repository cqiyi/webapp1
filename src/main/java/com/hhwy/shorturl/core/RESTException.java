package com.hhwy.shorturl.core;

import com.alibaba.fastjson.JSON;
import com.hhwy.shorturl.core.Utility;

public class RESTException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605081281034364064L;

	private int code;
	private String message;
	
	protected RESTException(){
	}
	
	protected RESTException(int code, String message){
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

	public final static RESTException UNKWON = new RESTException(999, "未知的异常");
	
	public final static RESTException AUTHENTICATION_FAILS = new RESTException(999, "安全认证失败");

}
