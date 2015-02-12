package com.hhwy.webapp1.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hhwy.webapp1.core.model.AccessToken;
import com.hhwy.webapp1.core.model.User;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class ViewController {
	private static final String JSP_CREATE_ADMIN_PAGE = "install/create-admin";
	
	@RequestMapping(value = JSP_CREATE_ADMIN_PAGE)
	public String showCreateAdminPage(Model model) {
		AccessToken token = new AccessToken();
		model.addAttribute("apikey", token.getApikey());
		model.addAttribute("secret", token.getSecret());
		
		wrapModel(JSP_CREATE_ADMIN_PAGE, model);
		return JSP_CREATE_ADMIN_PAGE;
	}
	
	private static final String JSP_LOGIN_PAGE = "login";
	
	@RequestMapping(value = JSP_LOGIN_PAGE)
	public String showLoginPage(Model model) {
		AccessToken token = new AccessToken();
		model.addAttribute("apikey", token.getApikey());
		model.addAttribute("secret", token.getSecret());

		User user = new User();
		user.setLoginName("administrator");
		user.setPassword("123");
		
		wrapModel(JSP_LOGIN_PAGE, model);
		return JSP_LOGIN_PAGE;
	}

	private static final String JSP_LOGOUT_PAGE = "logout";

	@RequestMapping(value = JSP_LOGOUT_PAGE)
	public String logoutPage(Model model) {
		wrapModel(JSP_LOGOUT_PAGE, model);
		return JSP_LOGOUT_PAGE;
	}

	private static final String JSP_INDEX_PAGE = "/";
	
	@RequestMapping(value = JSP_INDEX_PAGE)
	public String showIndexView(Model model) {
		wrapModel(JSP_INDEX_PAGE, model);
		return "index";
	}
	
	/*
	 * 在此包装Model，比如向页面设置全局参数
	 */
	private void wrapModel(String soure, Model model){
		//TODO 在此包装Model，比如向页面设置全局参数
	}
}
