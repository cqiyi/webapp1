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
	 * 不需要进行权限集安全过滤的地址，可以使用通配符
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
			// TODO 对所有的REST接口进行安全认证
			
			/*
			 * 1、根据http头信息验证
			 * 2、根据地址栏参数验证
			 * 3、根据form表单参数进行验证
			 */
//			return true;
			
			
			
			throw RESTException.AUTHENTICATION_FAILS;
		}
		
		/*
		 * 除静态资源以外，不需要验证的地址
		 * 静态资源通配符配置在：applicationContext.xml 
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