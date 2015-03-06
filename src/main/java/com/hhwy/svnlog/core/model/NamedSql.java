package com.hhwy.svnlog.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhwy.svnlog.core.BaseModel;

@Entity
@Table(name = "t_core_namedsql")
public class NamedSql extends BaseModel {
	private String sqlName;
	private String sqlStatement;
	private Integer executions;
	private Boolean isCore;

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getSqlStatement() {
		return sqlStatement;
	}

	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

	public Integer getExecutions() {
		return executions;
	}

	public void setExecutions(Integer executions) {
		this.executions = executions;
	}

	public Boolean getIsCore() {
		return isCore;
	}

	public void setIsCore(Boolean isCore) {
		this.isCore = isCore;
	}
}
