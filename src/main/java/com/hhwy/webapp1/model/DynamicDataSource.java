package com.hhwy.webapp1.model;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DynamicDataSource {

	public DataSource getDataSource() {
		//TODO ��̬����Դ������δʵ��
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setUsername("sa");
		dataSource.setUrl("jdbc:h2:mem");
		dataSource.setPassword("");
		return dataSource;
	}
}
