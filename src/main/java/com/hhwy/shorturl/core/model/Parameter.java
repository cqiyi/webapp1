package com.hhwy.shorturl.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.shorturl.core.BaseModel;

/*
 * ϵͳ����
 */
@Entity
@Table(name = "t_core_parameter")
public class Parameter extends BaseModel {

	@Column(length = LITTLE)
	private String paramName;

	@Column(length = MIDDLE)
	private String paramValue;

	@Column(length = MIDDLE)
	private String defaultValue;

	/*
	 * ϵͳ���Ĳ�����ֻ��Maintenance������û������޸�
	 */
	private Boolean isCore;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Boolean getIsCore() {
		return isCore;
	}

	public void setIsCore(Boolean isCore) {
		this.isCore = isCore;
	}

}
