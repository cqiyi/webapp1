package com.hhwy.webapp1.core;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;


/**
 * @author Administrator
 *
 */
public class ApplicationAuthenticationFilter extends AuthorizationFilter {
	
	/*
	 * ����Ҫ����Ȩ�޼���ȫ���˵ĵ�ַ������ʹ��ͨ���
	 */
	private static final String[] ANON_URLS = {"/login", "/logout", "/**"};

//	@Autowired
//	private SysAuthorizationMapper sysAuthorizationMapper;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String requestURI = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
		Utility.println("requestURI=" + requestURI);

		if(pathMatcher.matches("/api", requestURI)){
			// TODO �����е�REST�ӿڽ��а�ȫ��֤
			
			/*
			 * 1������httpͷ��Ϣ��֤
			 * 2�����ݵ�ַ��������֤
			 * 3������form������������֤
			 */
//			return true;
			
			
			
			throw RESTException.AUTHENTICATION_FAILS;
		}
		
		/*
		 * ����̬��Դ���⣬����Ҫ��֤�ĵ�ַ
		 * ��̬��Դͨ��������ڣ�applicationContext.xml 
		 * /assets/** = anon
		 */
		for(String anon : ANON_URLS){
			if(pathMatcher.matches(anon, requestURI)){
				return true;
			}
		}
		
		if (subject.isAuthenticated()) {
			return true;
		}
		
		return false;
	}

}