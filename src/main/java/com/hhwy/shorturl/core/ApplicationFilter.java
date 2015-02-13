package com.hhwy.shorturl.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avaje.ebean.EbeanServer;
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

		// Utility.println("httpRequest.getRequestURI()=" +
		// httpRequest.getRequestURI());
		Utility.println("httpRequest.getServletPath()=" + httpRequest.getServletPath());
		String alias = httpRequest.getServletPath().substring(1);

		ShortUrl url = getEbean().find(ShortUrl.class).where().eq("alias", alias).findUnique();
		if (url != null) {
			url.setClickCount(url.getClickCount() + 1);
			getEbean().save(url);
			httpRespone.sendRedirect(url.getOrginUrl());
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
