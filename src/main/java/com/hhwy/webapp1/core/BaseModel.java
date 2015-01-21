package com.hhwy.webapp1.core;

import java.sql.Timestamp;

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
	 * 数据版本号
	 */
	@Version
	protected Integer version;

	/*
	 * 创建时间
	 */
	@Column(updatable = false)
	protected Timestamp created;

	/*
	 * 更新时间
	 */
	protected Timestamp updated;

	/*
	 * 扩展属性，以json格式存储
	 */
	@Column(length = LONG)
	protected String extendProperties;

	/*
	 * 描述
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

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
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
