package com.hhwy.svnlog.core.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.alibaba.fastjson.JSON;
import com.hhwy.svnlog.core.RESTController;
import com.hhwy.svnlog.core.Utility;

@Controller
@RequestMapping(value = "/sql", produces = "application/json;charset=UTF-8")
public class JdbcController extends RESTController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody String get(@PathVariable String id, WebRequest request) {
		Iterator<String> names = request.getParameterNames();
		while(names.hasNext()){
			String name = names.next();
			Utility.println(name + "=" + request.getParameter(name));
		}
		return JSON.toJSONString(queryForList(id));
	}

	@RequestMapping(value = "/{id}", params = "first", method = RequestMethod.GET)
	public @ResponseBody String first(@PathVariable String id) {
		List<Map<String, Object>> list = queryForList(id);
		return JSON.toJSONString(list.get(0));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public @ResponseBody String post(@PathVariable String id) {
		return null;
	}

	private List<Map<String, Object>> queryForList(String id) {
		String sql = this.getMessage(id);
		Utility.println(sql);
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		return list;
	}
}
