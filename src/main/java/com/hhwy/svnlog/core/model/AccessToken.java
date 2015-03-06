package com.hhwy.svnlog.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.svnlog.core.BaseModel;
import com.hhwy.svnlog.core.Utility;

/*
 * 
 */
@Entity
@Table(name = "t_core_access_token")
public class AccessToken extends BaseModel {
	private String apikey;
	private String secret;

	public AccessToken() {
		this.apikey = Utility.getRandomUUID();
		this.secret = Utility.getRandomUUID() + Utility.md5Hash(this.apikey);
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
