package com.hhwy.shorturl.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avaje.ebean.EbeanServer;
import com.hhwy.shorturl.core.Installed;
import com.hhwy.shorturl.core.model.AccessToken;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class ViewController {
	private static final String JSP_INDEX_PAGE = "/";

	@RequestMapping(value = JSP_INDEX_PAGE)
	public String showIndexView(Model model) {
		AccessToken token = new AccessToken();
		model.addAttribute("apikey", token.getApikey());
		model.addAttribute("secret", token.getSecret());
		getEbean().save(token);

		wrapModel(JSP_INDEX_PAGE, model);
		return "index";
	}

	/*
	 * �ڴ˰�װModel��������ҳ������ȫ�ֲ���
	 */
	private void wrapModel(String soure, Model model) {
		// TODO �ڴ˰�װModel��������ҳ������ȫ�ֲ���
	}

	private EbeanServer getEbean() {
		return Installed.getEbean();
	}
}
