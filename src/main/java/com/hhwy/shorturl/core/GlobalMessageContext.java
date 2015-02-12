package com.hhwy.shorturl.core;

import java.util.Locale;

public class GlobalMessageContext {

	public static GlobalMessageContext Current = new GlobalMessageContext();

	public final Locale defaultLocale = Locale.getDefault();

	public String get(String code) {
		return Utility.getCurrentWebApplicationContext().getMessage(code, null,
				defaultLocale);
	}

	public String get(String code, String defaultMessage) {
		return Utility.getCurrentWebApplicationContext().getMessage(code, null,
				defaultMessage, defaultLocale);
	}

	public String get(String code, Object[] args, String defaultMessage) {
		return Utility.getCurrentWebApplicationContext().getMessage(code, args,
				defaultMessage, defaultLocale);
	}

	public String get(String code, Object[] args) {
		return Utility.getCurrentWebApplicationContext().getMessage(code, args,
				defaultLocale);
	}
}
