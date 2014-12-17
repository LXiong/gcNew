<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='style/layout.css?v=3'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='js/jquery-1.11.1.min.js'/>"></script>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">今日通话情况分析</h3>
	<div class="content_List615">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead>
                 <tr>
                     <td width="10%">类别</td>
                     <td width="10%">呼叫次数</td>
                     <td width="10%">通话时长(秒)</td>
                     <td width="10%">平均通话时长(秒)</td>
                     <td width="10%">等待时长(秒)</td>
                     <td width="10%">平均等待时长(秒)</td>
                 </tr>
             </thead>
             <tbody>
				<tr align="center">
					<td>总体</td>
					<td>${agtMap.tn }</td>
					<td>${agtMap.tt }</td>
					<td>${agtMap.at }</td>
					<td>${agtMap.tw }</td>
					<td>${agtMap.atw }</td>
				</tr>
				<tr align="center">
					<td>呼入</td>
					<td>${agtMap.itn }</td>
					<td>${agtMap.itt }</td>
					<td>${agtMap.iat }</td>
					<td>${agtMap.iw }</td>
					<td>${agtMap.iaw }</td>
				</tr>
				<tr align="center">
					<td>外呼</td>
					<td>${agtMap.otn }</td>
					<td>${agtMap.ot }</td>
					<td>${agtMap.oat }</td>
					<td>${agtMap.ow }</td>
					<td>${agtMap.oaw }</td>
				</tr>
			</tbody>
		</table>
		<br/>
	</div>
</div>
</body>
</html>