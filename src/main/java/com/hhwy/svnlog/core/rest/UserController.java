package com.hhwy.svnlog.core.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhwy.svnlog.core.RESTController;

@Controller
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController extends RESTController {

//	@RequestMapping(value = "hello", method = RequestMethod.GET)
//	public @ResponseBody String hello() throws Exception {
//		User u = new User();
//		// u.setAuthorizationLevel(AuthorizationGroup.General);
//		u.setDisplayName("ÕÅÈý");
//
//		getEbean().save(u);
//		// TODO
//		getEbean().createNamedSqlQuery("").findList().get(0).keys();
//
//		User m02 = getEbean().find(User.class, u.getId());
//		return m02.toString();
//	}

//	@RequestMapping(value = "tables", method = RequestMethod.GET)
//	public @ResponseBody String tables() throws Exception {
////		int count = jdbcTemplate.queryForObject("select count(*) from sqlite_master", int.class);
//		return count + "";
//	}
}
