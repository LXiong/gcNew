<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>扶贫观察点管理系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/script/jquery-1.11.1.min.js'/>"></script>
	<!-- 日期控件开始 -->
    <link type="text/css" href="<c:url value='/datePicker/skin/WdatePicker.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/datePicker/WdatePicker.js'/>"></script>
    <!-- 日期控件结束 -->
    <script type="text/javascript" src="<c:url value='/script/sys_log.js'/>"></script>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">System Log</h3>
   	<form action="<c:url value='/systemLogAction_home.action'/>" method="post" id="searchForm" name="searchForm">
   	<ul class="queryWrap_ul">
		<li><label>开始日期：</label><input type="text" name="startdate" id="startdate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\')||\'2050-01-01\'}',skin:'whyGreen'})" value="<s:property value="startdate"/>"/></li>
        <li><label>结束日期：</label><input type="text" name="enddate" id="enddate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}',maxDate:'%y-%M-%d',skin:'whyGreen'})" value="<s:property value="enddate"/>"/></li>
        <li><input type="submit" id="searchImg" class="btn4" value="查&nbsp;&nbsp;询"/></li>
	</ul>
	<input type="hidden" id="totalPage" name="totalPage" value="<s:property value="totalPage"/>" />
   	<input type="hidden" id="curPage" name="curPage" value="<s:property value="curPage"/>" />
    </form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="10%">编号</th>
                     <th width="10%">来源</th>
                     <th width="10%">类型</th>
                     <th width="20%">日期时间</th>
                     <th width="50%">描述</th>
                 </tr>
             </thead>
             <tbody>
               	<s:iterator id="log" value="#session.vts.list">
				<tr align="center">
					<td><s:property value="#log.logid"/></td>
					<td><s:property value="#log.sender"/></td>
					<td>
						<s:if test="#log.msgtype==1">信息</s:if>
						<s:elseif test="#log.msgtype==2">警告</s:elseif>
						<s:else>错误</s:else>
					</td>
					<td><s:property value="#log.occdt"/></td>
					<td align="left" class="tabtd1">&nbsp;&nbsp;<s:property value="#log.content"/></td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
    <!-- js split page -->
    <div class="split_page_wrap">
		<a type="button" id="firstP" href="javascript:firstPage()">首页 </a>
        <a type="button" id="preP" href="javascript:prePage()">上一页&nbsp; </a>
		<label><s:property value="curPage" />/<s:property value="totalPage" />&nbsp;</label>
        <input id="num" type="text" class="ipt20" value="<s:property value="curPage"/>" onfocus="this.select()" />
        <a type="button" id="inputP" href="javascript:jumpPage()">跳转</a>
        <a type="button" id="nextP" href="javascript:nextPage()">下一页&nbsp;</a> 
        <a type="button" id="lastP" href="javascript:lastPage()">尾页 </a>
	</div>
</div>
</body>
</html>