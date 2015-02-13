package com.hhwy.shorturl.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class BaseModel {
	protected static final int LONG = 1000;
	protected static final int MIDDLE = 500;
	protected static final int LITTLE = 200;
	protected static final int SHORT = 50;

	@Id
	@Column(length = SHORT, updatable = false)
	protected String id = Utility.getRandomUUID();

	/*
	 * ���ݰ汾��
	 */
	@Version
	protected Integer version;

	/*
	 * ����ʱ��
	 */
	@Column(updatable = false)
	protected Date created = Utility.getNow();

	/*
	 * ����ʱ��
	 */
	protected Date updated = Utility.getNow();

	/*
	 * ��չ���ԣ���json��ʽ�洢
	 */
	@Column(length = LONG)
	protected String extendProperties;

	/*
	 * ����
	 */
	@Column(length = LONG)
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getExtendProperties() {
		return extendProperties;
	}

	public void setExtendProperties(String extendProperties) {
		this.extendProperties = extendProperties;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
