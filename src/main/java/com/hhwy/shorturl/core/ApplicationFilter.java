package com.hhwy.shorturl.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.avaje.ebean.EbeanServer;
import com.hhwy.shorturl.core.model.AccessToken;
import com.hhwy.shorturl.core.model.ShortUrl;

public class ApplicationFilter implements Filter {

	private EbeanServer getEbean() {
		return Installed.getEbean();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpRespone = (HttpServletResponse) response;

		// Utility.println("httpRequest.getServletPath()=" +
		// httpRequest.getServletPath());
		String alias = httpRequest.getServletPath().substring(1);

		// 响应短地址的请求
		ShortUrl url = getEbean().find(ShortUrl.class).where().eq("alias", alias).findUnique();
		if (url != null) {
			Utility.println("短地址：" + httpRequest.getServletPath() + " ...");
			url.setClickCount(url.getClickCount() + 1);
			getEbean().save(url);
			httpRespone.sendRedirect(url.getOrginUrl());
			return;
		}

		// 识别为api REST接口，进行安全验证
		if (alias.startsWith("api/")) {
			Utility.println("REST API接口：" + httpRequest.getServletPath() + " ...");
			String apikey = httpRequest.getHeader("apikey");
			String salt = httpRequest.getHeader("salt");
			String authenticationToken = httpRequest.getHeader("authentication-token");

			AccessToken token = Installed.getEbean().find(AccessToken.class).where().eq("apikey", apikey).findUnique();
			if (token != null) {
				// CryptoJS.SHA256(app.apikey + salt + app.secret)
				String hashed = Utility.sha256Hash(apikey + salt + token.getSecret());
				if (StringUtils.equals(authenticationToken, hashed)) {
					chain.doFilter(request, response);
					return;
				}
			}

			httpRequest.getRequestDispatcher("/unusual/authentication-fails").forward(httpRequest, httpRespone);
			return;
		}

		Utility.println("静态资源：" + httpRequest.getServletPath() + " ...");
		// 其他请求默认为视图，不进行安全验证
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
