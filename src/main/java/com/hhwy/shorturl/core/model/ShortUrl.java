package com.hhwy.shorturl.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
import com.hhwy.shorturl.core.BaseModel;
import com.hhwy.shorturl.core.Utility;

@Entity
@Table(name = "t_short_url")
public class ShortUrl extends BaseModel {

	/*
	 * �����ֵ
	 */
	public static final String SEQUENCE_NAME = "shorturl_last_index";

	public ShortUrl() {
	}

	public ShortUrl(String url) {
		this.orginUrl = url;
	}

	/*
	 * �̵�ַ����
	 */
	private String alias;

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/*
	 * ԭʼ�ĵ�ַ
	 */
	private String orginUrl;

	/*
	 * �������
	 */
	private int clickCount;
	/*
	 * ������ʽ�����ֶ�������������api����
	 */
	private String createType;

	/*
	 * ��ҳ�ı���
	 */
	private String title;

	/*
	 * �˶̵�ַ�Ƿ񹫿�
	 */
	private boolean isPublic;

	/*
	 * ����������ƣ��������ַʧЧ
	 */
	private int limitClicked;

	/*
	 * ʧЧ���������������ַʧЧ
	 */
	private int limitdSeconds;

	/**
	 * @return the orginUrl
	 */
	public String getOrginUrl() {
		return orginUrl;
	}

	/**
	 * @param orginUrl
	 *            the orginUrl to set
	 */
	public void setOrginUrl(String orginUrl) {
		this.orginUrl = orginUrl;
	}

	/**
	 * @return the clickCount
	 */
	public int getClickCount() {
		return clickCount;
	}

	/**
	 * @param clickCount
	 *            the clickCount to set
	 */
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	/**
	 * @return the createType
	 */
	public String getCreateType() {
		return createType;
	}

	/**
	 * @param createType
	 *            the createType to set
	 */
	public void setCreateType(String createType) {
		this.createType = createType;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the limitClicked
	 */
	public int getLimitClicked() {
		return limitClicked;
	}

	/**
	 * @param limitClicked
	 *            the limitClicked to set
	 */
	public void setLimitClicked(int limitClicked) {
		this.limitClicked = limitClicked;
	}

	/**
	 * @return the limitdSeconds
	 */
	public int getLimitdSeconds() {
		return limitdSeconds;
	}

	/**
	 * @param limitdSeconds
	 *            the limitdSeconds to set
	 */
	public void setLimitdSeconds(int limitdSeconds) {
		this.limitdSeconds = limitdSeconds;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, Utility.defaultJsonFeatures);
	}

}
