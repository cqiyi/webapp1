package com.hhwy.webapp1.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.webapp1.core.BaseModel;

/*
 * ϵͳ��¼�û�
 */
@Entity
@Table(name = "t_user")
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
	@Column(length = MIDDLE)
	private String password;

	/*
	 * ���������
	 */
	@Column(length = SHORT)
	private String salt;

	/*
	 * ����ʱ��
	 */
	private Timestamp lockExpirationTime;

	/*
	 * ��֤�û���
	 */
	private AuthorizationGroup authorizationLevel;

	/*
	 * ����¼ʱ��
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
	 * �´ε�¼�����޸�����
	 */
	private boolean mustChangePassword;

}
