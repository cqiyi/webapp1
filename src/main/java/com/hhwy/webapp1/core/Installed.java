package com.hhwy.webapp1.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Installed {

	private static final String INSTALLED_SIGN_FILE = ".installed";

	public static Installed Current = new Installed();

	public void execute() {
		String appHomeName = GlobalMessageContext.Current
				.get("env.app_home_name");
		String appHome = System.getenv(appHomeName);

		String platformName = GlobalMessageContext.Current.get("pltform.name");
		String filePath = Installed.class.getResource("") + INSTALLED_SIGN_FILE;
		System.out.println(filePath);
		System.out.println("启动 " + platformName + " ...");
		System.out.println("当前位置：" + appHomeName + "=" + appHome);
		System.setProperty("user.dir", appHome);

		File file = new File(INSTALLED_SIGN_FILE);
		System.out.println(file.getAbsolutePath());
		if (file.exists()) {
			return;
		}

		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.append(new Date().toString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		initialize();
	}

	public void initialize() {
		// TODO 安装初始化
	}
}
