package com.hhwy.svnlog.core.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.svnlog.core.BaseModel;

/*
 * 系统登录用户
 */
@Entity
@Table(name = "t_core_user")
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
	@Column(length = LITTLE)
	private String password;

	/*
	 * 解锁时间
	 */
	private Timestamp lockExpirationTime;

	/*
	 * 最后登录时间
	 */
	private Timestamp lastLogined;

	/*
	 * 下次登录必须修改密码
	 */
	private boolean mustChangePassword;

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

	public Timestamp getLockExpirationTime() {
		return lockExpirationTime;
	}

	public void setLockExpirationTime(Timestamp lockExpirationTime) {
		this.lockExpirationTime = lockExpirationTime;
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

}
