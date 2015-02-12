package com.hhwy.webapp1.core.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stringtemplate.v4.ST;

import com.hhwy.webapp1.core.Installed;
import com.hhwy.webapp1.core.RESTController;
import com.hhwy.webapp1.core.RESTException;
import com.hhwy.webapp1.core.SessionHelper;
import com.hhwy.webapp1.core.Utility;
import com.hhwy.webapp1.core.model.User;
import com.hhwy.webapp1.core.service.UserService;
import com.hhwy.webapp1.test1.model.Model01;

@Controller
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController extends RESTController {

	@Autowired
	private UserService service;

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
		int count = jdbcTemplate.queryForObject("select count(*) from sqlite_master", int.class);
		return count + "";
	}

	private static final String JSON_LOGIN = "{\"logined\":\"<logined>\", \"message\":\"<message>\"}";

	/*
	 * 登录验证
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String apikey, @RequestParam String login,
			@RequestParam String password, @RequestParam(required = false) String captcha, Model model) {
		// 假设登录失败
		boolean logined = false;
		String message = "登录失败";

		ST json = new ST(JSON_LOGIN);
		try {
			List<User> users = getEbean().find(User.class).where().eq("login_name", login).findList();
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

	/*
	 * 在系统 初始化时，创建系统管理员，此方法仅允许在系统第一次安装时执行，以后不允许调用 客户端传上来的密码，是已经用Sha256加密过的
	 */
	@RequestMapping(value = "init", method = RequestMethod.POST)
	public @ResponseBody String init(@RequestParam String apikey, User user) {

		if (Installed.getLocked()) {
			// 给客户端提示系统异常
			throw new RESTException(1, "System abnormalities.");
		}
		service.hashPassword(user);
		getEbean().save(user);

		// TODO 此用户为系统管理员，在此设置管理员权限

		return "{message:\"创建成功.\"}";
	}
}
