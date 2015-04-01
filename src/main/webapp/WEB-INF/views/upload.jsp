<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>upload file</title>
</head>
<body>
	<form method="POST" enctype="multipart/form-data"
		action="<s:url value='/upload' />">
		<input type="file" name="file1"><input type="submit"
			value="UPLOAD !">
	</form>
</body>
</html>