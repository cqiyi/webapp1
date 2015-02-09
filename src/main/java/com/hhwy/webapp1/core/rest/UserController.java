package com.hhwy.webapp1.core.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stringtemplate.v4.ST;

import com.hhwy.webapp1.core.ModelController;
import com.hhwy.webapp1.core.SessionHelper;
import com.hhwy.webapp1.core.Utility;
import com.hhwy.webapp1.core.model.User;
import com.hhwy.webapp1.test1.model.Model01;

@Controller
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController extends ModelController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Model01 get() {
		Model01 m01 = new Model01();
		return m01;
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public @ResponseBody String hello() throws Exception {
		User u = new User();
		// u.setAuthorizationLevel(AuthorizationGroup.General);
		u.setDisplayName("张三");

		getEbean().save(u);
		// TODO
		getEbean().createNamedSqlQuery("").findList().get(0).keys();

		User m02 = getEbean().find(User.class, u.getId());
		return m02.toString();
	}

	@RequestMapping(value = "tables", method = RequestMethod.GET)
	public @ResponseBody String tables() throws Exception {
		int count = jdbcTemplate.queryForObject(
				"select count(*) from sqlite_master", int.class);
		return count + "";
	}

	private static final String JSON_LOGIN = "{\"logined\":\"<logined>\", \"message\":\"<message>\"}";

	/*
	 * 登录验证
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String apikey,
			@RequestParam String login, @RequestParam String password,
			@RequestParam(required = false) String captcha, Model model) {
		//假设登录失败
		boolean logined = false;
		String message = "登录失败";
		
		ST json = new ST(JSON_LOGIN);
		try {
			List<User> users = getEbean().find(User.class).where()
					.eq("login_name", login).findList();
			if (users.size() != 1) {
				message = message + "，用户不存在";
			} else {
				SessionHelper.login(users.get(0), password);
				logined = true;
			}
		} catch (Exception ex) {
			Utility.wrapRuntimeException(ex);
			message = message + "，用户名或密码错误";
		}
		json.add("logined", logined);
		json.add("message", message);
		return json.render();
	}
}
