package com.hhwy.shorturl.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.shorturl.core.BaseModel;

/*
 * 系统参数
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
	 * 系统核心参数，只有Maintenance级别的用户可以修改
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
