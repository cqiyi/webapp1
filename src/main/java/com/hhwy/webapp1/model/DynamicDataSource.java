package com.hhwy.webapp1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Entity
public class DynamicDataSource {
	@Id
	private Integer id;
	
	private String name;
	
	private String classDriverName;
	
	private String jdbcUrl;
	
	private String userName;
	
	private String password;
	
	private Date created;
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Version
	private Integer version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getClassDriverName() {
		return classDriverName;
	}

	public void setClassDriverName(String classDriverName) {
		this.classDriverName = classDriverName;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DataSource getDataSource() {
		//TODO 动态数据源，功能未实现
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setUsername("sa");
		dataSource.setUrl("jdbc:h2:mem");
		dataSource.setPassword("");
		return dataSource;
	}
}
