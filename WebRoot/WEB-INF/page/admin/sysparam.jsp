<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<c:url value='/style/common_cn.css'/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/style/layout.css'/>" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
</head>
<body style="background:#E0EEFB;">
<div id="dbCon">
	<h3 class="h3_title">系统参数设定</h3>
	<div class="db-info">
		<form id="form1" action="<c:url value='/sysparam-save.action'/>" method="post">
        <div class="lab_ipt_item">
	    	<span class="lab120">默认外呼主叫：</span>
	        <div class="ipt-box">
	        	<input type="text" id="anix" name="ani" value="${ani }" maxlength="15" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">外呼等待时长(秒)：</span>
	        <div class="ipt-box">
	        	<input type="text" id="maxwaitx" name="maxwait" value="${maxwait }" maxlength="8" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div style="height:10px;"></div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="saveParamBtn()"/></div>
		</div>
		</form>
    </div>
</div>	
<script type="text/javascript" src="<c:url value='/js/sysparam.js?v=3'/>"></script>
<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
</body>
</html>