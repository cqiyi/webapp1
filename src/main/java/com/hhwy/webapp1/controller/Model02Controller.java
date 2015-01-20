package com.hhwy.webapp1.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhwy.webapp1.model.Model01;
import com.hhwy.webapp1.model.Model02;
import com.hhwy.webapp1.model.Model03;
import com.hhwy.webapp1.model.User;

@Controller
@RequestMapping(value = "/api/model02", produces = "application/json;charset=UTF-8")
public class Model02Controller extends ModelController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Model01 get() {
		Model01 m01 = new Model01();
		m01.setStr01("这是一个字符串");
		return m01;
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public @ResponseBody String hello() throws Exception {
		User u = new User();
		// u.setAuthorizationLevel(AuthorizationGroup.General);
		u.setDisplayName("李四");
		getEbean().save(u);

		Model01 m01 = new Model01();
		m01.setDate03(new Date());
		m01.setStr01("这是一个字符串，来自于Model03");
		m01.setCurrentUser(u);

		Model03 m03 = new Model03();
		m03.setDate01(new Date());
		m03.setModel(m01);

		Model02 m02 = new Model02();
		m02.setModel(m03);

		getEbean().save(m02);

		m01.setDate01(new Date());
		getEbean().save(m01);
		getEbean().save(m03);

		Model02 _m02 = getEbean().find(Model02.class, m02.getId());
		_m02.getModel().getModel().getCurrentUser();
		return getJsonContext().toJsonString(_m02);
	}
}
