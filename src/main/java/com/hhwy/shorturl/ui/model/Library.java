package com.hhwy.shorturl.ui.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hhwy.shorturl.core.BaseModel;
import com.hhwy.shorturl.core.model.Option;

/*
 * �����ֿ⣬ϵͳ֧�ֵ����й���
 */
@Entity
@Table(name = "t_ui_library")
public class Library extends BaseModel {

	/*
	 * ��������
	 */
	@Column(length = LITTLE)
	private String libName;

	/*
	 * ��ʾ���ƣ����ģ�
	 */
	@Column(length = LITTLE)
	private String displayName;

	/*
	 * ��ǩ�ƣ��ö��Ÿ���
	 */
	@Column(length = MIDDLE)
	private String tags;

	/*
	 * �����ĵ�ַ��Ϊ��׼Ŀ¼��WEBINF/ui
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
