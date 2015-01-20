package com.hhwy.webapp1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.hhwy.webapp1.core.Utility;

@Entity
@Table(name = "t_user")
public class User {
	@Id
	private String id = Utility.getRandomUUID();

	/*
	 * ��¼�û���
	 */
	private String loginName;

	/*
	 * ��ʾ��/����
	 */
	private String displayName;

	/*
	 * ���룬���ܺ��
	 */
	private String password;

	/*
	 * ���������
	 */
	private String salt;

	/*
	 * ����ʱ��
	 */
	private Date lockExpirationTime;

	/*
	 * ��֤�û���
	 */
	private AuthorizationGroup authorizationLevel;

	@Version
	private Integer version;

	/*
	 * ����¼ʱ��
	 */
	private Date lastLogined;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Date getLockExpirationTime() {
		return lockExpirationTime;
	}

	public void setLockExpirationTime(Date lockExpirationTime) {
		this.lockExpirationTime = lockExpirationTime;
	}

	public AuthorizationGroup getAuthorizationLevel() {
		return authorizationLevel;
	}

	public void setAuthorizationLevel(AuthorizationGroup authorizationLevel) {
		this.authorizationLevel = authorizationLevel;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getLastLogined() {
		return lastLogined;
	}

	public void setLastLogined(Date lastLogined) {
		this.lastLogined = lastLogined;
	}

	public boolean isMustChangePassword() {
		return mustChangePassword;
	}

	public void setMustChangePassword(boolean mustChangePassword) {
		this.mustChangePassword = mustChangePassword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * �´ε�¼�����޸�����
	 */
	private boolean mustChangePassword;

	/*
	 * ����
	 */
	private String description;
}
