<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css?v=1'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">座席分机监控&nbsp;
	<s:select list="#session.vts.ctsList" onchange="changeServer(this,'subtelmonitor')" listKey="ctsname" listValue="ctsname" value="#session.vts.curCTS" cssStyle="height:22px; margin:1px;"></s:select>
	</h3>
	<div class="content_List615" style="height:635px; max-height:630px; overflow-y:auto;">
		<table id="subTelMonitorTab" cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="4%">编号</th>
                     <th width="10%">分机号码</th>
                     <th width="10%">状态</th>
                     <th width="10%">方向</th>
                     <th width="10%">对方号码</th>
                     <th width="10%">登录话务员</th>
                     <th width="20%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies"></tbody>
		</table>
	</div>
</div>
<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/subtel-monitor.js?v=3'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cts.js?v=3'/>"></script>
<script type="text/javascript">
	$(function(){
		var curCts = "<s:property value='#session.vts.curCTS'/>";
		//window.parent.document.getElementById("OCXPlugin").LookACD("cts100");
		$("#OCXPlugin",window.parent.document)[0].LookSubTel(curCts);
	});
</script>
</body>
</html>