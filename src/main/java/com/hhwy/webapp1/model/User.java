package com.hhwy.webapp1.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.webapp1.core.BaseModel;

/*
 * 系统登录用户
 */
@Entity
@Table(name = "t_user")
public class User extends BaseModel {

	/*
	 * 登录用户名
	 */
	@Column(length = SHORT, unique = true, nullable = false)
	private String loginName;

	/*
	 * 显示名/姓名
	 */
	@Column(length = SHORT)
	private String displayName;

	/*
	 * 密码，加密后的
	 */
	@Column(length = MIDDLE)
	private String password;

	/*
	 * 密码混淆码
	 */
	@Column(length = SHORT)
	private String salt;

	/*
	 * 解锁时间
	 */
	private Timestamp lockExpirationTime;

	/*
	 * 认证用户组
	 */
	private AuthorizationGroup authorizationLevel;

	/*
	 * 最后登录时间
	 */
	private Timestamp lastLogined;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Timestamp getLockExpirationTime() {
		return lockExpirationTime;
	}

	public void setLockExpirationTime(Timestamp lockExpirationTime) {
		this.lockExpirationTime = lockExpirationTime;
	}

	public AuthorizationGroup getAuthorizationLevel() {
		return authorizationLevel;
	}

	public void setAuthorizationLevel(AuthorizationGroup authorizationLevel) {
		this.authorizationLevel = authorizationLevel;
	}

	public Timestamp getLastLogined() {
		return lastLogined;
	}

	public void setLastLogined(Timestamp lastLogined) {
		this.lastLogined = lastLogined;
	}

	public boolean isMustChangePassword() {
		return mustChangePassword;
	}

	public void setMustChangePassword(boolean mustChangePassword) {
		this.mustChangePassword = mustChangePassword;
	}

	/*
	 * 下次登录必须修改密码
	 */
	private boolean mustChangePassword;

}
