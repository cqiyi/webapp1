package com.hhwy.webapp1.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhwy.webapp1.model.AuthorizationGroup;
import com.hhwy.webapp1.model.Model01;
import com.hhwy.webapp1.model.User;

@Controller
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController extends ModelController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Model01 get() {
		Model01 m01 = new Model01();
		m01.setStr01("这是一个字符串");
		return m01;
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public @ResponseBody String hello() throws Exception {
		User u = new User();
//		u.setAuthorizationLevel(AuthorizationGroup.General);
		u.setDisplayName("张三");

		getEbean().save(u);

		User m02 = getEbean().find(User.class, u.getId());
		return m02.toString();
	}
}
