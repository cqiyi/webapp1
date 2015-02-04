<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" session="false"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录 - <s:message code="app_name"/></title>
<link rel="shortcut icon" href="<s:url value='/assets/favicon.png' />">
<link rel="stylesheet" href="<s:url value='/assets/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet" href="<s:url value='/assets/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<s:url value='/assets/login.css'/>">
<script type="text/javascript">
	var app = app || {apikey: '${apikey}', secret: '${secret}', base: '<s:url value="" />'};
</script>
</head>
<body>
<div id="login-container">
	<div id="logo"><img src="<s:url value='/assets/logo-login.png'/>" alt="Logo"></div>
	<div id="login">
		<h3><s:message code="app_name"/></h3>
		<h5>欢迎登录，请输入您的登录信息</h5>
		<form id="login-form" action="<s:url value='/api/user/login' />" class="form">
			<div class="form-group">
				<label for="login-username">Username</label>
				<input type="text" class="form-control" name="login" placeholder="登录帐号">
			</div>
			<div class="form-group">
				<label for="login-password">Password</label>
				<input type="password" class="form-control" name="password" placeholder="密码">
			</div>
			<div class="form-group">
				<button type="submit" id="login-btn" class="btn btn-primary btn-block">登 &nbsp;录 &nbsp; <i class="fa fa-play-circle"></i></button>
			</div>
		</form><span id="message">&nbsp;</span>
	</div>
</div>
</body>
<script type="text/javascript" src="<s:url value='/assets/js/sea.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/js/core.js' />"></script>
<!-- 
<script type="text/javascript" src="<s:url value='/assets/js/CryptoJS/rollups/hmac-md5.js' />"></script>
 -->
<script type="text/javascript">
define('jquery', function($){
	alert($('form').html());
	$('form:first').rest(function(json, status){ 
		$("#message").html(json.responseText);
	});
});
</script>
</html>