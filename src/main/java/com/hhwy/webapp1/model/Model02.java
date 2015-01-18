package com.hhwy.webapp1.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Version;

public class Model02 {
	
	@Id
	private Integer id;
	
	@Version
	private Integer version;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	private Integer int01;
	private Integer int02;
	private Integer int03;
	
	private String str01;
	private String str02;
	private String str03;

	private Double double01;
	private Double double02;
	private Double double03;

	private Date date01;
	private Date date02;
	private Date date03;

	private Boolean bool01;
	public Integer getInt01() {
		return int01;
	}
	public void setInt01(Integer int01) {
		this.int01 = int01;
	}
	public Integer getInt02() {
		return int02;
	}
	public void setInt02(Integer int02) {
		this.int02 = int02;
	}
	public Integer getInt03() {
		return int03;
	}
	public void setInt03(Integer int03) {
		this.int03 = int03;
	}
	public String getStr01() {
		return str01;
	}
	public void setStr01(String str01) {
		this.str01 = str01;
	}
	public String getStr02() {
		return str02;
	}
	public void setStr02(String str02) {
		this.str02 = str02;
	}
	public String getStr03() {
		return str03;
	}
	public void setStr03(String str03) {
		this.str03 = str03;
	}
	public Double getDouble01() {
		return double01;
	}
	public void setDouble01(Double double01) {
		this.double01 = double01;
	}
	public Double getDouble02() {
		return double02;
	}
	public void setDouble02(Double double02) {
		this.double02 = double02;
	}
	public Double getDouble03() {
		return double03;
	}
	public void setDouble03(Double double03) {
		this.double03 = double03;
	}
	public Date getDate01() {
		return date01;
	}
	public void setDate01(Date date01) {
		this.date01 = date01;
	}
	public Date getDate02() {
		return date02;
	}
	public void setDate02(Date date02) {
		this.date02 = date02;
	}
	public Date getDate03() {
		return date03;
	}
	public void setDate03(Date date03) {
		this.date03 = date03;
	}
	public Boolean getBool01() {
		return bool01;
	}
	public void setBool01(Boolean bool01) {
		this.bool01 = bool01;
	}
	public Boolean getBool02() {
		return bool02;
	}
	public void setBool02(Boolean bool02) {
		this.bool02 = bool02;
	}
	public Boolean getBool03() {
		return bool03;
	}
	public void setBool03(Boolean bool03) {
		this.bool03 = bool03;
	}
	private Boolean bool02;
	private Boolean bool03;
}
