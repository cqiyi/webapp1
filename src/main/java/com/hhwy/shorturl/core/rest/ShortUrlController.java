package com.hhwy.shorturl.core.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhwy.shorturl.core.RESTController;
import com.hhwy.shorturl.core.Utility;
import com.hhwy.shorturl.core.model.Parameter;
import com.hhwy.shorturl.core.model.ShortUrl;

@Controller
@RequestMapping(value = "/api/url", produces = "application/json;charset=UTF-8")
public class ShortUrlController extends RESTController {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String create(@RequestParam String url, HttpServletRequest request) {

		int next = getNextValue();
		Utility.println("next=" + next);

		ShortUrl shortUrl = new ShortUrl(url);
		shortUrl.setAlias(Utility.dec2HexN(next));
		getEbean().save(shortUrl);
		shortUrl.setAlias(request.getRequestURL().toString().replaceFirst("/api/url", "/")
				+ shortUrl.getAlias());

		return shortUrl.toString();
	}

	private synchronized int getNextValue() {
		Parameter param = getEbean().find(Parameter.class).where().eq("param_name", ShortUrl.SEQUENCE_NAME)
				.findUnique();

		int last = 0;
		try {
			last = Integer.parseInt(param.getParamValue()) + 1;
		} catch (Exception ex) {
			Utility.println(ex.toString());
		}

		param.setParamValue(String.valueOf(last));
		getEbean().save(param);

		return last;
	}
}
