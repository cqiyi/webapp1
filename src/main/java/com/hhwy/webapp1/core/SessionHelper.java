package com.hhwy.webapp1.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.hhwy.webapp1.core.model.User;

public class SessionHelper {

	public static final String CURRENT_USER = "com.hhwy.webapp1.core.sessionhelper._current_user";
	public static final String CURRENT_LOGIN_TIME = "com.hhwy.webapp1.core.sessionhelper._current_login_time";
	public static final String CURRENT_ROOT_TREE_NODE = "com.hhwy.webapp1.core.sessionhelper._current_permission";
	public static final String CURRENT_ORGANIZATION = "com.hhwy.webapp1.core.sessionhelper._current_organization";

	// public static SysResourceService sysResourceService;

	public static void login(User user, String password) {
		//登录验证之前，先将保存当前用户
		SessionHelper.setAttribute(CURRENT_USER, user);
		Subject currentUser = SecurityUtils.getSubject();
		setAttribute(CURRENT_LOGIN_TIME, new Date());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getId(),
				password);
		// ”Remember Me” built-in, just do this:
		token.setRememberMe(false);

		currentUser.login(token);
	}

	public static Subject getCurrentSubject() {
		return SecurityUtils.getSubject();
	}

	public static User getCurrentUser() {
		Object obj = getAttribute(CURRENT_USER);
		return obj instanceof User ? (User) obj : null;
	}

	public static String getCurrentUserId() {
		User user = getCurrentUser();
		return user != null ? user.getId() : StringUtils.EMPTY;

	}

	public static String getCurrentDisplayName() {
		User user = getCurrentUser();
		return user != null ? user.getDisplayName() : StringUtils.EMPTY;
	}

	public static String getLoginTime() {
		Object obj = (Date) getAttribute(CURRENT_LOGIN_TIME);
		if (obj instanceof Date) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return formatter.format((Date) obj);
		}

		return StringUtils.EMPTY;
	}

	// public static boolean IsBusinessDepartment(){
	// SysOrganization org = (SysOrganization)
	// getAttribute(CURRENT_ORGANIZATION);
	// return org != null && org.getDescription() != null &&
	// org.getDescription().indexOf("业务") >= 0;
	// }

	public static void setAttribute(Object key, Object value) {
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
	}

	public static Object getAttribute(Object key) {
		return SecurityUtils.getSubject().getSession().getAttribute(key);
	}

	// public static TreeNode getMenuRoot() {
	// SysUser user = getCurrentSysUser();
	// Utility.println("user=" + user);
	// if (user != null && user.getMustChangePassword() == false &&
	// sysResourceService != null) {
	// try {
	// TreeNode root = sysResourceService.getMenuRoot(user.getId());
	// if (root != null && root.getChildren() != null)
	// return root;
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	// TreeNode root = new TreeNode();
	// root.setChildren(new ArrayList<TreeNode>());
	// return root;
	// }

}
