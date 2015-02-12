package com.hhwy.shorturl.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.shorturl.core.BaseModel;

/*
 * ϵͳ�ֵ�ѡ��
 */
@Entity
@Table(name = "t_core_option")
public class Option extends BaseModel {

	@Column(length = LITTLE)
	private String optKey;

	@Column(length = MIDDLE)
	private String optName;

	@Column(length = LITTLE)
	private String optType;

	/*
	 * �Ƿ�ΪĬ��ֵ
	 */
	private Boolean isDefault;

	/*
	 * �Ƿ�Ϊϵͳ���õĺ��ģ��������޸ĺ�ɾ��
	 */
	private Boolean isCore;

	private Integer orderBy;

	public String getOptKey() {
		return optKey;
	}

	public void setOptKey(String optKey) {
		this.optKey = optKey;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getIsCore() {
		return isCore;
	}

	public void setIsCore(Boolean isCore) {
		this.isCore = isCore;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
}
