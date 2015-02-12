package com.hhwy.webapp1.core.service;

import org.springframework.stereotype.Service;

import com.hhwy.webapp1.core.BaseService;
import com.hhwy.webapp1.core.Utility;
import com.hhwy.webapp1.core.model.User;

@Service
public class UserService extends BaseService {

	/*
	 * ¶ÔÃÜÂë½øĞĞ±àÂë
	 */
	public void hashPassword(User user) {
		String hashedPassword = Utility.sha256Hash(user.getPassword() + user.getLoginName());
		user.setPassword(hashedPassword);
	}
}
