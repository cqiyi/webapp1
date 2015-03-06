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
<title></title>
<link rel="shortcut icon" href="<s:url value='/assets/favicon.ico' />" type="image/x-icon" />
<link rel="stylesheet" href="<s:url value='/assets/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet" href="<s:url value='/assets/bootstrap/css/bootstrap.min.css'/>">

<link rel="stylesheet" href="<s:url value='/assets/kendo/styles/kendo.common.min.css'/>" />
<link rel="stylesheet" href="<s:url value='/assets/kendo/styles/kendo.silver.min.css'/>" />

    
<script type="text/javascript">
	var app = app || {
		apikey : '${apikey}',
		secret : '${secret}',
		appurl : '<s:url value="/" />'
	};
</script>
</head>
<body style="overflow:hidden">
	 <div id="grid"></div>
	<div style="display: none">
		<script type="text/javascript">
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
<script type="text/javascript" src="<s:url value='/assets/js/crypto-js/rollups/sha256.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/js/core.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/kendo/js/kendo.all.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/kendo/js/messages/kendo.messages.zh-CN.min.js' />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#grid").kendoGrid({
        dataSource: {
        	transport: {
                read: {
                    url: "<s:url value='/sql/rawsql_select_svnlog_total' />",
                    dataType: "json"
                }
            },
            pageSize: 20
        },
        height: $(document).height() - 3,
        scrollable: true,
        sortable: true,
        filterable: true,
        pageable: {
        	pageSizes: true,
        },
        columns: [
            { field: "author", title: "帐号"},
            { field: "displayName", title: "姓名"},
            { field: "job", title: "岗位", width: "140px"},
            {title: "提交数量（" + (new Date()).format('yyyy年M月d日）'),
		        columns: [
		            { field: "countA0", title: "今天"},
		            { field: "countA1", title: "昨天"},
		            { field: "countA2", title: "前天"},
		            { field: "countA3", title: "三天前"},
		            { field: "countA4", title: "四天前"},
		            { field: "countA5", title: "五天前"},
		            { field: "countA6", title: "六天前"},
		            { field: "countA7", title: "七天前"},
		            { field: "countA8", title: "八天前"},
		            { field: "countA9", title: "九天前"}]
            }]
    });
});
</script>
</html>