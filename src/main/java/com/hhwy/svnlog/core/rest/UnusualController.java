package com.hhwy.svnlog.core.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhwy.svnlog.core.RESTUnusual;

@Controller
@RequestMapping(value = "/unusual", produces = "application/json;charset=UTF-8")
public class UnusualController {

	@RequestMapping("/authentication-fails")
	public @ResponseBody String authenticationails() {
		return RESTUnusual.AUTHENTICATION_FAILS.toString();
	}
}
