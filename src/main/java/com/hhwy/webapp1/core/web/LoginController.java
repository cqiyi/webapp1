package com.hhwy.webapp1.core.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hhwy.webapp1.core.model.AccessToken;
import com.hhwy.webapp1.core.model.User;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	private static final String JSP_LOGIN_PAGE = "login";
	private static final String JSP_LOGOUT_PAGE = "logout";

	@RequestMapping(value = JSP_LOGIN_PAGE, method = RequestMethod.GET)
	public String showLoginPage(Model model) {
		AccessToken token = new AccessToken();

		model.addAttribute("apikey", token.getApikey());
		model.addAttribute("secret", token.getSecret());
		
		User user = new User();
		user.setLoginName("administrator");
		user.setPassword("123");
		
		return JSP_LOGIN_PAGE;
	}

	@RequestMapping(value = JSP_LOGOUT_PAGE)
	public String logoutPage() {
		return JSP_LOGOUT_PAGE;
	}

	@RequestMapping(value = "/")
	public String showIndexView(Model model) {
		return "index";
	}
}
