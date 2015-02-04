package com.hhwy.webapp1.core.service;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.hhwy.webapp1.core.BaseService;
import com.hhwy.webapp1.core.model.User;

public class UserService extends BaseService {

	/*
	 * ��������б���
	 */
	public void hashedSha256Password(User user) {
		String hashedPassword = new Sha256Hash(user.getPassword(),
				user.getSalt(), 1).toString();
		user.setPassword(hashedPassword);
	}
	
	/*
	 * ��¼�����֤
	 */
	public boolean Authentication(String loginName, String password){
		//TODO ��¼�����֤��δʵ��
		return true;
	}
}
