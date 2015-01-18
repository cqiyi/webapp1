package com.hhwy.webapp1.model;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DynamicDataSource {

	public DataSource getDataSource() {
		//TODO 动态数据源，功能未实现
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setUsername("sa");
		dataSource.setUrl("jdbc:h2:mem");
		dataSource.setPassword("");
		return dataSource;
	}
}
