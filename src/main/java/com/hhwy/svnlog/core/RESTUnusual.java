package com.hhwy.svnlog.core;

import com.alibaba.fastjson.JSON;

public class RESTUnusual {

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

	public final static RESTUnusual UNKWON = new RESTUnusual(999, "δ֪���쳣");

	public final static RESTUnusual AUTHENTICATION_FAILS = new RESTUnusual(99, "��ȫ��֤ʧ��");

	public final static RESTUnusual SHORT_URL_ALIAS_EXISTED = new RESTUnusual(9, "����ַ�Ѵ���");
	
	public final static RESTUnusual SHORT_URL_BLANK = new RESTUnusual(1, "��ַΪ��");

}
