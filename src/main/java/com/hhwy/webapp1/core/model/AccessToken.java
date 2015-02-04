package com.hhwy.webapp1.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.webapp1.core.BaseModel;
import com.hhwy.webapp1.core.Utility;

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
		this.secret = Utility.getRandomUUID()
				+ Utility.getStringMD5(this.apikey);
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
