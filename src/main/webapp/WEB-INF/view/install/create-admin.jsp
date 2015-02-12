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
<title>创建系统管理员 - <s:message code="app_name"/></title>
<link rel="shortcut icon" href="<s:url value='/assets/favicon.png' />">
<link rel="stylesheet" href="<s:url value='/assets/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet" href="<s:url value='/assets/kendo/styles/kendo.common.min.css'/>">
<link rel="stylesheet" href="<s:url value='/assets/kendo/styles/kendo.bootstrap.min.css'/>">
<style type="text/css">
html { 
	font-size: 12px; 
	font-family: Arial, Helvetica, sans-serif; 
}
.k-invalid-msg
{
  display: none !important;
}
 .k-invalid
 {
   border: 1px solid red;
 }
.k-textbox {
    width: 11.8em;
}
 #main form {
     padding: 30px;
 }
 #main h3 {
     font-weight: normal;
     font-size: 1.4em;
     margin: 0;
     padding: 0 0 20px;
 }

 #main ul {
     list-style-type: none;
     margin: 0;
     padding: 0;
 }
 #main li {
     margin: 5px 0;
 }
 
 label {
     display: inline-block;
     width: 100px;
     text-align: right;
     padding-right: 10px;
 }
 
 label.checkbox{
     display: inline-block;
     width:300px;
     padding-left:120px;
     text-align:left;
 }

 .required {
     font-weight: bold;
 }
 .accept, .status {
     padding-left: 90px;
 }
 .confirm {
     text-align: right;
 }

 .valid {
	background-color: #f0fff0;
 }

 .invalid {
	background-color: #fff0f0
 }
 span.k-tooltip {
     margin-left: 6px;
 }
 textarea{
	 width:400px;
	 height:60px;
 }
</style>

<script src="<s:url value='/assets/kendo/js/jquery.min.js'/>"></script>
<script src="<s:url value='/assets/kendo/js/kendo.all.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/assets/js/underscore-min.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/js/json2.js' />"></script>
<script type="text/javascript" src="<s:url value='/assets/js/core.js' />"></script>
<script type="text/javascript">
	var app = app || {apikey: '${apikey}', secret: '${secret}', appurl: '<s:url value="/" />'};
</script>
</head>
<body>
 <div id="example">
 <div class="main-section k-header">
 <div id="main">
     <form class="k-content" id="mainForm">
         <h3>创建系统管理员</h3>
         <ul>
             <li>
                 <label for="loginName" class="required">登录名</label>
                 <input type="text" id="loginName" name="loginName" class="k-textbox" required validationMessage="请输入登录名" style="width: 260px;" />
             </li>
             <li>
                 <label for="displayName" class="required">真实姓名</label>
                 <input type="text" id="displayName" name="displayName" class="k-textbox" required validationMessage="请输入真实姓名" style="width: 260px;" />
             </li>
             <li>
                 <label for="password1" class="required">密码</label>
                 <input type="password" id="password1" name="password1" class="k-textbox" required validationMessage="请输入密码" style="width: 260px;" />
             </li>
             <li>
                 <label for="password2" class="required">密码确认</label>
                 <input type="password" id="password2" name="password2" class="k-textbox" required validationMessage="请输入密码确认" style="width: 260px;" />
             </li>
             <li>
                 <label class="checkbox"><input type="checkbox" name="isMustChangePassword" />下次登陆必须修改密码</label>
             </li>
             <li>
                 <label>备注</label>
                 <textarea></textarea>
             </li>
             <li  class="confirm">
                 <button class="k-button k-primary" type="submit">保存</button>
             </li>
             <li class="status">&nbsp;</li>
         </ul>
     </form>
 </div>
 </div>
<script type="text/javascript">
$(document).ready(function() {
   	 var errorTemplate = '<div class="k-widget k-tooltip k-tooltip-validation" style="margin:0.5em">#=message#</div>'

    var validator = $("form").kendoValidator({errorTemplate: errorTemplate}).data("kendoValidator");

    var tooltip = $("form").kendoTooltip({
      filter: ".k-invalid",
      content: function(e) {
        var errorMessage = $("form").find("[data-for=" + e.target.attr("name") + "]");
        return errorMessage.text();
      }
    });

        var status = $(".status");

        $("form").submit(function(event) {
            event.preventDefault();
            if (validator.validate()) {
                status.text("Hooray! Your main has been booked!")
                    .removeClass("invalid")
                    .addClass("valid");
            } else {
                status.text("Oops! There is invalid data in the form.")
                    .removeClass("valid")
                    .addClass("invalid");
            }
        });
    });
</script>
 </div>
</body>
<script type="text/javascript">
$(function(){
    $('form:first').rest(function(data){
        $("#message").html(data.message);
    });
	
});
</script>
</html>