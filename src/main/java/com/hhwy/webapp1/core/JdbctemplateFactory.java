package com.hhwy.webapp1.core;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hhwy.webapp1.model.DynamicDataSource;

public final class JdbctemplateFactory {

	public static JdbcTemplate getDynamicJdbcTemplate(DynamicDataSource dds) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dds.getDataSource());
		return jdbcTemplate;
	}
}
