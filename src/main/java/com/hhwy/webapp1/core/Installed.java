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
	 * ��������ָϵͳ�Ѿ���ʼ����ɣ���ʼ����װ�򵼽��漰��ؽӿڽ�����������ǰִ�У���ϵͳδ����ʱ���С�
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
		Utility.println("-----------------------------------------------------\nϵͳ��ʼ��...");
		String appHomeName = GlobalMessageContext.Current
				.get("env.app_home_name");
		String appHome = System.getenv(appHomeName);

		String platformName = GlobalMessageContext.Current.get("pltform.name");
		Utility.println("���� " + platformName + " ...");
		Utility.println("��ǰλ�ã�" + System.getProperty("user.dir"));
		if (StringUtils.isNotEmpty(appHome)) {
			System.setProperty("user.dir", appHome);
			Utility.println("�ض�������" + System.getProperty("user.dir"));
		} else {
			Utility.println("�������� [" + appHomeName + "] δ���ã������ض���.");
		}
		Utility.println("��ǰλ�ã�" + appHomeName + "=" + appHome);
		startup();
		Utility.println("��ɡ�\n-----------------------------------------------------");
	}

	/*
	 * ��һ�ΰ�װʱִ�У���װ�ɹ���Ͳ�ִ����
	 */
	public static void initialize() {
		// TODO ��װ��ʼ��
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
			initialize();
		}
	}

	/*
	 * ÿ�η�����������ִ��
	 */
	public static void startup() {

	}
}
