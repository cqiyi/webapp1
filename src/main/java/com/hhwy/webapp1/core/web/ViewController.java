package com.hhwy.webapp1.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	@RequestMapping(value = "/{path}.html", method = RequestMethod.GET)
	public String showJspView(@PathVariable String path, Model model) {
		// model.addAttribute("app_name", "恒华移动应用商店");
		return path;
	}
	
}
