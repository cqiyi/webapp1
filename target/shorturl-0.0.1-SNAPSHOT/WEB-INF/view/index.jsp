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
<title><s:message code="app_name" /></title>
<link rel="shortcut icon" href="<s:url value='/assets/favicon.ico' />" type="image/x-icon" />
<link rel="stylesheet" href="<s:url value='/assets/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet" href="<s:url value='/assets/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<s:url value='/assets/default.css'/>">
<script type="text/javascript">
	var app = app || {
		apikey : '${apikey}',
		secret : '${secret}',
		appurl : '<s:url value="/" />'
	};
</script>
</head>
<body>
	<div class="header">
		<h1>恒华短网址</h1>
	</div>
	<div class="container">
		<form class="form-horizontal col-md-8 col-md-offset-2" action="<s:url value='/api/url'/>">
			<div class="form-group">
				<input id="url" name="url" type="text" placeholder="请输入您的网址 . . ." class="form-control">
			</div>
			<div class="form-group pull-right">
				<button class="btn btn-info">
					<span class="glyphicon glyphicon-random"></span> 网址转换
				</button>
			</div>
		</form>
		<p id="message" class="c">
			<a class="btn btn-lg btn-default" target="_blank">http://hhwy.org/Dx5L</a><br/>
			<a href="#">复制到剪切板</a>
		</p>
	</div>
	<div id="footer" class="navbar-fixed-bottom">
		©2015 恒华科技 版权所有<span class="icp"><a href="http://www.miitbeian.gov.cn/" target="_blank">京ICP备10054994号-3</a></span>
	</div>
</body>
<script type="text/javascript" src="<s:url value='/assets/js/jquery-1.11.2.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/bootstrap/js/bootstrap.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/js/core.js' />"></script>
<script type="text/javascript">
	$(function() {
		$('form:first').rest(
				function(json) {
					$("#message").show().find("a.btn").html(json.alias).attr(
							"href", json.alias);
				});

	});
</script>
</html>