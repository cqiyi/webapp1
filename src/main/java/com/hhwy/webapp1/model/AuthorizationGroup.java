package com.hhwy.webapp1.model;


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
	 * 一般管理员
	 */
	Manager,

	/*
	 * 常规用户
	 */
	General,
}
