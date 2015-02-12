package com.hhwy.shorturl.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.serializer.SerializerFeature;

public final class Utility {

	public static Log logger = LogFactory.getLog(Utility.class);

	/**
	 * 产生随机字符
	 * */
	public static Random randGen = new Random();
	private static char[] character = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	public static final String randomString(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("企图生成长度小于1的随机字符串，length=" + length);
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = character[randGen.nextInt(character.length - 1)];
		}
		return new String(randBuffer);
	}

	/**
	 * 
	 * 获取文件内容的MD5摘要信息
	 */
	public static String md5File(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new IllegalArgumentException("获取文件内容的MD5摘要信息，文件不存在，fileName=" + fileName);
		}
		MessageDigest digest = null;
		int len;
		try {
			byte buffer[] = new byte[1024];
			digest = MessageDigest.getInstance("MD5");
			FileInputStream in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	/* 获取字符串的md5 */
	public static String md5Hash(String str) {
		BigInteger bigInt = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			bigInt = new BigInteger(1, digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return bigInt.toString(16);
	}

	public static final String ILLEGAL_CHARACTER = " ,;-%&?.|`";

	/**
	 * 字符串中是否包含非法字符
	 * 
	 * @return
	 */
	public static boolean hasIllegalCharacter(String str) {
		return StringUtils.containsAny(str, ILLEGAL_CHARACTER.toCharArray());
	}

	public static String getRandomUUID() {
		String str = java.util.UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY);
		return str.toLowerCase();

	}

	public static SerializerFeature[] defaultJsonFeatures = { SerializerFeature.UseISO8601DateFormat,
	// SerializerFeature.WriteMapNullValue,
	// SerializerFeature.WriteNullNumberAsZero,
	// SerializerFeature.WriteNullStringAsEmpty,
	// SerializerFeature.WriteNullListAsEmpty
	};

	public static WebApplicationContext getCurrentWebApplicationContext() {
		return ContextLoader.getCurrentWebApplicationContext();
	}

	public static void println(String str) {
		System.out.println(str);
	}

	public static String urlDecode(String str) {
		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return str;
		}
	}

	/*
	 * 抛出运行期异常
	 */
	public static RuntimeException wrapRuntimeException(Exception exception) {
		exception.printStackTrace();
		RuntimeException re = new RuntimeException(exception);
		return re;
	}

	/*
	 * 获取当前系统时间
	 */
	public static Timestamp getNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	/*
	 * 采用sha256加密算法加密
	 */
	public static String sha256Hash(String source) {
		return new Sha256Hash(source).toString();
	}
}
