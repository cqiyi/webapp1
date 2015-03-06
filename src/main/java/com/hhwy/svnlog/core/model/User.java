package com.hhwy.svnlog.core.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.svnlog.core.BaseModel;

/*
 * ϵͳ��¼�û�
 */
@Entity
@Table(name = "t_core_user")
public class User extends BaseModel {

	/*
	 * ��¼�û���
	 */
	@Column(length = SHORT, unique = true, nullable = false)
	private String loginName;

	/*
	 * ��ʾ��/����
	 */
	@Column(length = SHORT)
	private String displayName;

	/*
	 * ���룬���ܺ��
	 */
	@Column(length = LITTLE)
	private String password;

	/*
	 * ����ʱ��
	 */
	private Timestamp lockExpirationTime;

	/*
	 * ����¼ʱ��
	 */
	private Timestamp lastLogined;

	/*
	 * �´ε�¼�����޸�����
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
