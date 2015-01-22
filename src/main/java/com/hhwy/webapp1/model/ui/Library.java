package com.hhwy.webapp1.model.ui;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hhwy.webapp1.core.BaseModel;
import com.hhwy.webapp1.model.core.Option;

/*
 * 构件仓库，系统支持的所有构件
 */
@Entity
@Table(name = "t_ui_library")
public class Library extends BaseModel {

	/*
	 * 构建名称
	 */
	@Column(length = LITTLE)
	private String libName;

	/*
	 * 显示名称（中文）
	 */
	@Column(length = LITTLE)
	private String displayName;

	/*
	 * 标签云，用逗号隔开
	 */
	@Column(length = MIDDLE)
	private String tags;

	/*
	 * 构建的地址，为基准目录：WEBINF/ui
	 */
	@Column(length = MIDDLE)
	private String libPath;

	/*
	 * 
	 */
	@Column(length = LONG)
	private String nativeContent;

	@Column(length = SHORT)
	private String relaseVersion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Option> libArgs;

}
