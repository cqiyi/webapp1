package com.hhwy.shorturl.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.springsupport.factory.EbeanServerFactoryBean;
import com.avaje.ebean.text.json.JsonContext;
import com.hhwy.shorturl.core.model.Parameter;
import com.hhwy.shorturl.core.model.ShortUrl;

public final class Installed {

	private static final String INSTALLED_SIGN_FILE = ".lock";
	private static final String CLASSES_NAME = "/classes/";

	public static final String PLATFORM_NAME = "Short URL Service Platform";
	public static final String APPHOME_KEY = "SHORTURL_HOME";

	/*
	 * ��������ָϵͳ�Ѿ���ʼ����ɣ���ʼ����װ�򵼽��漰��ؽӿڽ�����������ǰִ�У���ϵͳδ����ʱ���С�
	 */
	public static boolean getLocked() {
		return getLockedFile().exists();
	}

	private static File getLockedFile() {
		String path = Utility.urlDecode(Installed.class.getResource("").getPath());
		String filePath = path.substring(1, path.lastIndexOf(CLASSES_NAME) + CLASSES_NAME.length())
				+ INSTALLED_SIGN_FILE;
		return new File(filePath);
	}

	public static void execute() {
		Utility.println("-----------------------------------------------------\nϵͳ��ʼ��...");
		// String appHomeName =
		// GlobalMessageContext.Current.get("env.app_home_name");
		String appHome = System.getenv(APPHOME_KEY);

		Utility.println("���� " + PLATFORM_NAME + " ...");
		Utility.println("��ǰλ�ã�" + System.getProperty("user.dir"));
		if (StringUtils.isNotEmpty(appHome)) {
			System.setProperty("user.dir", appHome);
			Utility.println("�ض�������" + System.getProperty("user.dir"));
		} else {
			Utility.println("�������� '" + APPHOME_KEY + "' δ���ã������ض���.");
		}
		Utility.println("��ǰλ�ã�" + APPHOME_KEY + "=" + appHome);

		// TODO ��ʼ��Service��˵�Spring����
		applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ebeanServerFactory = applicationContext.getBean(EbeanServerFactoryBean.class);
		jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		jsonContext = getEbean().createJsonContext();

		startup();
		Utility.println("��ɡ�\n-----------------------------------------------------");
	}

	/*
	 * ��һ�ΰ�װ�������Ѿ���ʼ����ɵı�־
	 */
	public static void setLocked() {
		File file = getLockedFile();
		if (!file.exists()) {

			// ϵͳδ��װ��ִ�а�װ��ʼ��

			FileWriter fileWriter = null;
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				fileWriter.append("I am .");
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/*
	 * ÿ�η�����������ִ��
	 */
	public static void startup() {

		// ���̵�ַ�����в����Ƿ���ڣ��粻���ڣ����Զ�����
		Parameter param = getEbean().find(Parameter.class).where().eq("param_name", ShortUrl.SEQUENCE_NAME)
				.findUnique();
		if (param == null) {
			param = new Parameter();
			param.setParamName(ShortUrl.SEQUENCE_NAME);
			// ����ַ�����3λ
			param.setParamValue(String.valueOf((int) Math.pow(Utility.DIGITAL.length(), 2)));
			Utility.println(param.getCreated().toString());
			getEbean().save(param);
		} else {
			Utility.println("param.toString()=" + getJsonContext().toJsonString(param, true));
		}
	}

	private static EbeanServerFactoryBean ebeanServerFactory;

	public static EbeanServer getEbean() {
		try {
			return ebeanServerFactory.getObject();
		} catch (Exception e) {
			throw Utility.wrapRuntimeException(e);
		}
	}

	private static ApplicationContext applicationContext;

	public static ApplicationContext getCurrentContext() {
		if (applicationContext == null) {
			throw Utility.wrapRuntimeException(new Exception("applicationContext Has not been initialized."));
		}
		return applicationContext;
	}

	private static JdbcTemplate jdbcTemplate;

	public static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			throw Utility.wrapRuntimeException(new Exception("jdbcTemplate Has not been initialized."));
		}
		return jdbcTemplate;
	}

	private static JsonContext jsonContext;

	protected static JsonContext getJsonContext() {
		if (jdbcTemplate == null) {
			throw Utility.wrapRuntimeException(new Exception("JsonContext Has not been initialized."));
		}
		return jsonContext;
	}

	public static void main(String[] args) {
		System.out.println((int) Math.pow(Utility.DIGITAL.length(), 2));
	}
}
