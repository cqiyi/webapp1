package com.hhwy.shorturl.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hhwy.shorturl.core.BaseModel;

@Entity
@Table(name = "t_core_organization")
public class Organization extends BaseModel {

	/*
	 * 组织机构自动生成的key，自动生成，比较短
	 */
	@Column(length = SHORT)
	private String orgkey;

	@Column(length = MIDDLE)
	private String orgkeyPath;

	/*
	 * 组织机构编码
	 */
	@Column(length = SHORT)
	private String orgCode;

	@Column(length = SHORT)
	private String orgName;

	@Column(length = SHORT)
	private String parentId;

	/*
	 * 组织机构类型，字典
	 */
	@OneToOne
	private Option orgType;

	private Integer orderBy;

	@OneToOne
	@Column(updatable = false)
	private User createUser;

	@OneToOne
	private User updateUser;

	public String getOrgkey() {
		return orgkey;
	}

	public void setOrgkey(String orgkey) {
		this.orgkey = orgkey;
	}

	public String getOrgkeyPath() {
		return orgkeyPath;
	}

	public void setOrgkeyPath(String orgkeyPath) {
		this.orgkeyPath = orgkeyPath;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Option getOrgType() {
		return orgType;
	}

	public void setOrgType(Option orgType) {
		this.orgType = orgType;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

}
