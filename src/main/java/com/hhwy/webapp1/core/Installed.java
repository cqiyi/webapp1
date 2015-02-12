package com.hhwy.webapp1.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public final class Installed {

	private static final String INSTALLED_SIGN_FILE = ".lock";
	private static final String CLASSES_NAME = "/classes/";

	/*
	 * 锁定后，是指系统已经初始化完成，初始化安装向导界面及相关接口仅允许在锁定前执行，即系统未锁定时运行。
	 */
	public static boolean getLocked(){
		return getLockedFile().exists();
	}
	
	private static File getLockedFile(){
		String path = Utility.urlDecode(Installed.class.getResource("")
				.getPath());
		String filePath = path.substring(1, path.lastIndexOf(CLASSES_NAME)
				+ CLASSES_NAME.length())
				+ INSTALLED_SIGN_FILE;
		return new File(filePath);
	}

	public static void execute() {
		Utility.println("-----------------------------------------------------\n系统初始化...");
		String appHomeName = GlobalMessageContext.Current
				.get("env.app_home_name");
		String appHome = System.getenv(appHomeName);

		String platformName = GlobalMessageContext.Current.get("pltform.name");
		Utility.println("启动 " + platformName + " ...");
		Utility.println("当前位置：" + System.getProperty("user.dir"));
		if (StringUtils.isNotEmpty(appHome)) {
			System.setProperty("user.dir", appHome);
			Utility.println("重定向至：" + System.getProperty("user.dir"));
		} else {
			Utility.println("环境变量 [" + appHomeName + "] 未设置，忽略重定向.");
		}
		Utility.println("当前位置：" + appHomeName + "=" + appHome);
		startup();
		Utility.println("完成。\n-----------------------------------------------------");
	}

	/*
	 * 第一次安装时执行，安装成功后就不执行了
	 */
	public static void initialize() {
		// TODO 安装初始化
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
			initialize();
		}
	}

	/*
	 * 每次服务启动都会执行
	 */
	public static void startup() {

	}
}
