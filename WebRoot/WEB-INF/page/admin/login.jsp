<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/cookie.js'/>"></script>
 	<script type="text/javascript" src="<c:url value='/js/login.js?v=4'/>"></script>
 	
 	<!-- layer 弹出插件 start -->
	<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
	<!-- layer 弹出插件 end -->
	
 	<script type="text/javascript">
	$(function(){
		//账号输入框获取焦点 
 		document.all.account.focus();
 		//记住密码 
		var account = Cookie.getCookie("accountgcnew");
		var password =  Cookie.getCookie("passwordgcnew");
		if(account){
			$("#account").val(account);
			$("#password").val(password);
			$("#rememberPass").attr("checked", true); 
		}
		else
		{
			$("#account").val('');
			$("#password").val('');
			$("#rememberPass").attr("checked", false); 
		}
	});
 	
 	document.onkeydown = function(e) {   
		var theEvent = e || window.event;   
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode; 
		if (code == 13) {   
    		login();
    		return false;   
		}   
		return true;
	}
 	</script>
 	<title>电话自动外呼系统</title>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
</head>
<body>
<div class="login_wrap">
   	<div class="login_main">
       	<div class="login_main_up">
           	<div class="login_product"><s:property value="#application.vta.product"/></div>
            <div class="login_company"><strong><s:property value="#application.vta.customer"/></strong></div>
		</div>
		<div class="login_main_down">
           	<div class="login_content">
               	<table class="table_login" border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr height="28px">
							<th class="login_label">账号&nbsp;</th>
							<td><input type="text" id="account" name="account"  autocomplete="off" value="" placeholder="输入账号" tabindex="1" class="ipt155 inputDefault"/></td>
						</tr>
						<tr height="28px">
							<th class="login_label">密码&nbsp;</th>
							<td><input type="password" id="password" name="password"  autocomplete="off" value="" placeholder="输入密码" tabindex="2" class="ipt155 inputDefault"/></td>
						</tr>
						<tr height="28px">
							<th class="login_label">验证码&nbsp;</th>
							<td align="left">
								<input type="text" id="vercode" name="vercode" placeholder="输入验证码" class="ipt70 inputDefault" maxlength="4"/>
								<input type="hidden" id="bpath" value="${pageContext.request.contextPath }"/>
								<img src="${pageContext.request.contextPath }/verCode" id="verImg" onclick="changeVerCode()" class="cursor_p" height="20" width="60"/>
							</td>
						</tr>
						<tr height="28px">
							<th></th>
							<td>
								<input type="checkbox" id="rememberPass" name="rememberPass" value="yes"/><label for="rememberPass">记住密码</label>
							</td>
						</tr>
						<tr height="24px">
							<th></th>
							<td>
								<input type="button" onclick="login()" value="登&nbsp;&nbsp;录" class="btn4"/>
							</td>
						</tr>
					</tbody>
				</table>
            </div>
        </div>
    </div>
</div>
</body>
</html>