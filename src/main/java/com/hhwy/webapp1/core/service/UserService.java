package com.hhwy.webapp1.core.service;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.hhwy.webapp1.core.BaseService;
import com.hhwy.webapp1.core.model.User;

public class UserService extends BaseService {

	/*
	 * 对密码进行编码
	 */
	public void hashedSha256Password(User user) {
		String hashedPassword = new Sha256Hash(user.getPassword(),
				user.getSalt(), 1).toString();
		user.setPassword(hashedPassword);
	}
	
	/*
	 * 登录身份验证
	 */
	public boolean Authentication(String loginName, String password){
		//TODO 登录身份验证，未实现
		return true;
	}
}
