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
	<h3 class="h3_title">数据库维护</h3>
	<div class="db-info">
    	<p>数据库版本：<s:property value="#session.vts.map.serverinfo"/></p>
        <p>数据库名称：<s:property value="#session.vts.map.databasename"/></p>
        <p>数据库文件列表：</p>
        <table cellpadding="0" cellspacing="0">
        	<tr class="tab_head">
            	<td>逻辑名</td>
                <td>磁盘文件名</td>
                <td>大小</td>
            </tr>
            <s:iterator id="datafile" value="#session.vts.list" status="data">
			<tr align="center" style="font-size: 12px; height: 20px">
				<td align="left">&nbsp;<s:property value="#datafile.name"/></td>
				<td align="left">&nbsp;<s:property value="#datafile.filename"/></td>
				<td align="center"><s:property value="#datafile.size"/></td>
			</tr>
			</s:iterator>
        </table>
        <p>上次清理时间：<s:property value="#session.vts.map.lasttrunkdt"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="emptylog()" value="清空日志" class="btn4"/></p>
        <p>备份路径(数据库服务器所在的本地路径)：</p>
        <p><input type="text" id="defbackupfilename" name="defbackupfilename" value="<s:property value="#session.vts.map.defbackupfilename"/>" class="ipt_text_w180 inputDefault"/></p>
        <p>上次备份时间：<s:property value="#session.vts.map.lastbackupdt"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="backup('<s:property value="#session.vts.map.defbackupfilename"/>')" value="立即备份" class="btn4"/></p>
    </div>
</div>	
<script type="text/javascript" src="<c:url value='/js/dbconfig.js?v=6'/>"></script>
<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
</body>
</html>