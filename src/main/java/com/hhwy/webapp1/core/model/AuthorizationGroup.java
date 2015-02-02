package com.hhwy.webapp1.core.model;

public enum AuthorizationGroup {

	/*
	 * 系统检修模式超级管理员，具有一切权限，能够修改系统内置的参数及基础数据
	 */
	Maintenance,

	/*
	 * 超级管理员，整个系统不做任何权限控制，但不能修改系统内置的基础数据，对系统检修模式的功能和数据不可见
	 */
	Administrator,

	/*
	 * 增强的管理员（能够看到自己部门及所有下级部门的业务数据）
	 */
	PowerManager,

	/*
	 * 一般管理员，能够看到自己部门的数据
	 */
	Manager,

	/*
	 * 常规用户，只能看到自己的数据
	 */
	General,
}
