package com.hhwy.webapp1.core;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;


/**
 * @author Administrator
 *
 */
public class ApplicationAuthenticationFilter extends AuthorizationFilter {

//	@Autowired
//	private SysAuthorizationMapper sysAuthorizationMapper;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

		Subject subject = getSubject(request, response);
		ShiroHttpServletRequest shiroRequest = request instanceof ShiroHttpServletRequest ? (ShiroHttpServletRequest) request : null;
		Utility.println("shiroRequest.getServletPath()=" + shiroRequest.getServletPath());
		if (subject.isAuthenticated() == false) {
			return false;
		}
		
		return true;
	}

}