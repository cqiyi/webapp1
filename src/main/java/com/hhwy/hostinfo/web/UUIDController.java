package com.hhwy.hostinfo.web;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/uuid")
public class UUIDController {
	@RequestMapping(method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody String uuid() {
		return UUID.randomUUID().toString().toLowerCase();
	}
}
