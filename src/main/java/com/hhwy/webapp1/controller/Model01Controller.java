package com.hhwy.webapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhwy.webapp1.core.ModelController;

@Controller
@RequestMapping(value = "/api/model01", produces = "application/json;charset=UTF-8")
public class Model01Controller extends ModelController {
//
//	@RequestMapping(method = RequestMethod.GET)
//	public @ResponseBody Model01 get() {
//		Model01 m01 = new Model01();
//		m01.setStr01("这是一个字符串");
//		return m01;
//	}
//
//	@RequestMapping(value = "hello", method = RequestMethod.GET)
//	public @ResponseBody String hello() throws Exception {
//		Model01 m01 = new Model01();
//		m01.setStr01("这是一个字符串");
//
//		User u = new User();
////		u.setAuthorizationLevel(AuthorizationGroup.General);
//		u.setDisplayName("张三");
//		
//		m01.setCurrentUser(u);
//
//		getEbean().save(m01);
//
//		m01.setDate01(new Date());
//		getEbean().save(m01);
//
//		Model01 m02 = getEbean().find(Model01.class, m01.getId());
//		return m02.toString();
//	}
}
