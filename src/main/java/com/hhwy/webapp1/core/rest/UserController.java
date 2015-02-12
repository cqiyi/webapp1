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
		u.setDisplayName("����");

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
	 * ��¼��֤
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String apikey, @RequestParam String login,
			@RequestParam String password, @RequestParam(required = false) String captcha, Model model) {
		// �����¼ʧ��
		boolean logined = false;
		String message = "��¼ʧ��";

		ST json = new ST(JSON_LOGIN);
		try {
			List<User> users = getEbean().find(User.class).where().eq("login_name", login).findList();
			if (users.size() != 1) {
				message = message + "���û�������";
			} else {
				SessionHelper.login(users.get(0), password);
				logined = true;
			}
		} catch (Exception ex) {
			Utility.wrapRuntimeException(ex);
			message = message + "���û������������";
		}
		json.add("logined", logined);
		json.add("message", message);
		return json.render();
	}

	/*
	 * ��ϵͳ ��ʼ��ʱ������ϵͳ����Ա���˷�����������ϵͳ��һ�ΰ�װʱִ�У��Ժ�������� �ͻ��˴����������룬���Ѿ���Sha256���ܹ���
	 */
	@RequestMapping(value = "init", method = RequestMethod.POST)
	public @ResponseBody String init(@RequestParam String apikey, User user) {

		if (Installed.getLocked()) {
			// ���ͻ�����ʾϵͳ�쳣
			throw new RESTException(1, "System abnormalities.");
		}
		service.hashPassword(user);
		getEbean().save(user);

		// TODO ���û�Ϊϵͳ����Ա���ڴ����ù���ԱȨ��

		return "{message:\"�����ɹ�.\"}";
	}
}
