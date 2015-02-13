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
	 * 锁定后，是指系统已经初始化完成，初始化安装向导界面及相关接口仅允许在锁定前执行，即系统未锁定时运行。
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
		Utility.println("-----------------------------------------------------\n系统初始化...");
		// String appHomeName =
		// GlobalMessageContext.Current.get("env.app_home_name");
		String appHome = System.getenv(APPHOME_KEY);

		Utility.println("启动 " + PLATFORM_NAME + " ...");
		Utility.println("当前位置：" + System.getProperty("user.dir"));
		if (StringUtils.isNotEmpty(appHome)) {
			System.setProperty("user.dir", appHome);
			Utility.println("重定向至：" + System.getProperty("user.dir"));
		} else {
			Utility.println("环境变量 '" + APPHOME_KEY + "' 未设置，忽略重定向.");
		}
		Utility.println("当前位置：" + APPHOME_KEY + "=" + appHome);

		// TODO 初始化Service后端的Spring容器
		applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ebeanServerFactory = applicationContext.getBean(EbeanServerFactoryBean.class);
		jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		jsonContext = getEbean().createJsonContext();

		startup();
		Utility.println("完成。\n-----------------------------------------------------");
	}

	/*
	 * 第一次安装后，设置已经初始化完成的标志
	 */
	public static void setLocked() {
		File file = getLockedFile();
		if (!file.exists()) {

			// 系统未安装，执行安装初始化

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
	 * 每次服务启动都会执行
	 */
	public static void startup() {

		// 检查短地址的序列参数是否存在，如不存在，则自动创建
		Parameter param = getEbean().find(Parameter.class).where().eq("param_name", ShortUrl.SEQUENCE_NAME)
				.findUnique();
		if (param == null) {
			param = new Parameter();
			param.setParamName(ShortUrl.SEQUENCE_NAME);
			// 短网址，最短3位
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
