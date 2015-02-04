package com.hhwy.webapp1.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Installed {

	private static final String INSTALLED_SIGN_FILE = ".lock";
	private static final String CLASSES_NAME = "/classes/";

	public static Installed Current = new Installed();

	public void execute() {
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
		String path = Utility.URLDecode(Installed.class.getResource("")
				.getPath());
		String filePath = path.substring(1, path.lastIndexOf(CLASSES_NAME)
				+ CLASSES_NAME.length())
				+ INSTALLED_SIGN_FILE;
		File file = new File(filePath);
		if (!file.exists()) {

			// 系统未安装，执行安装初始化

			FileWriter fileWriter = null;
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				fileWriter.append("OK.");
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			initialize();
		}
		Utility.println("完成。\n-----------------------------------------------------");
	}

	public void initialize() {
		// TODO 安装初始化
	}
}
