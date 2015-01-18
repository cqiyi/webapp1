package com.hhwy.webapp1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhwy.webapp1.model.Model01;


@Controller
@RequestMapping(value = "/api/model01", produces = "application/json;charset=UTF-8")
public class Model01Controller {


	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Model01 get() {
		Model01 m01 = new Model01();
		m01.setStr01("这是一个字符串");
		return m01;
	}
}
