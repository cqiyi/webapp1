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
				<input type="text" id="orginUrl" name="orginUrl" placeholder="请输入您的网址 . . ." class="form-control">
				<button type="button" class="btn btn-link" id="more">
					<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span> 更多
				</button>
			</div>
			<div class="form-group more">
				<div class="input-group">
					<div class="input-group-addon">
						<b>http://hhwy.org/</b>
					</div>
					<input type="text" class="form-control" id="alias" name="alias" placeholder="短网址" style="width: 140px">
				</div>
			</div>
			<div class="form-group pull-right">
				<button class="btn btn-primary">
					<span class="glyphicon glyphicon-random"></span> 生成短网址
				</button>
			</div>
		</form>
		<div class="row error">
			<div class="alert alert-danger col-md-8 col-md-offset-2" role="alert">
				<strong>错误：</strong> <span id="err">短网址已存在，请重新输入。</span>
			</div>
		</div>
		<div id="message">
			<h3>
				<span class="label label-default">http://hhwy.org/Dx5L</span>
			</h3>
			<p>
				<a class="btn btn-link" target="_blank"><span class="glyphicon glyphicon-share" aria-hidden="true"></span> 新窗口打开
				</a>
			</p>
		</div>
	</div>
	<div id="footer" class="navbar-fixed-bottom">
		©2015 恒华科技 版权所有<span class="icp"><a href="http://www.miitbeian.gov.cn/" target="_blank">京ICP备10054994号-3</a></span>
	</div>
	<div style="display: none">
		<script>
			var _hmt = _hmt || [];
			(function() {
				var hm = document.createElement("script");
				hm.src = "//hm.baidu.com/hm.js?31adb78aa26ff9cfbbcef455ce2cac5e";
				var s = document.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(hm, s);
			})();
		</script>
	</div>

</body>
<script type="text/javascript" src="<s:url value='/assets/js/jquery-1.11.2.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/bootstrap/js/bootstrap.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/js/crypto-js/rollups/sha256.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/js/core.js' />"></script>
<script type="text/javascript">
	$(function() {
		$('[data-toggle="tooltip"]').tooltip();
		$('#more').click(function() {
			$(this).hide();
			$('.more').show('slow');
		});
		
		$('form:first').submit(function(){
			if($('#orginUrl').val() == ''){
				$('#message').hide();
				$('.error').show().find('#err').html('网址不能为空');
				return false;
			}
			var r = /^(http|https):\/\//i;
			if(!r.test($('#orginUrl').val())){
				$('#message').hide();
				$('.error').show().find('#err').html('网址不合法');
				return false;
			}
		}).rest(function(json) {
			if (json.alias) {
				$('.error').hide();
				$('#message span.label').html(json.alias);
				$('#message a').attr('href', json.alias);
				$('#message').show();
			} else {
				$('#message').hide();
				$('.error').show().find('#err').html(json.message);

			}
		});
	});
</script>
</html>