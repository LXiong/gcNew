<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<c:url value='/style/style.css'/>" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/dbconfig.js'/>"></script>
</head>
<body style="background:#E0EEFB;">
<div id="dbCon">
	<div class="db-info">
    	<p>数据库版本：<s:property value="#session.vts.map.serverinfo"/></p>
        <p>数据库名称：<s:property value="#session.vts.map.databasename"/></p>
        <p>数据库文件列表：</p>
        <table cellpadding="0" cellspacing="0">
        	<tr>
            	<td>逻辑名</td>
                <td>磁盘文件名</td>
                <td>大小</td>
            </tr>
            <s:iterator id="datafile" value="#session.vts.list" status="data">
			<tr align="center" style="font-size: 12px; height: 20px">
				<td align="left">&nbsp;<s:property value="#datafile.name"/></td>
				<td align="left">&nbsp;<s:property value="#datafile.filename"/></td>
				<td align="center"><s:property value="#datafile.sizex"/></td>
			</tr>
			</s:iterator>
        </table>
        <p>上次清理时间：<s:property value="#session.vts.map.lasttrunkdt"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="emptylog()" value="清空日志" class="button4"/></p>
        <p>备份路径(数据库服务器所在的本地路径)：</p>
        <p><input type="text" id="defbackupfilename" name="defbackupfilename" value="<s:property value="#session.vts.map.defbackupfilename"/>" class="inptxt3" onfocus="this.className='input_on3'" onblur="this.className='input_off3'"/></p>
        <p>上次备份时间：<s:property value="#session.vts.map.lastbackupdt"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="backup('<s:property value="#session.vts.map.defbackupfilename"/>')" value="立即备份" class="button4"/></p>
        <ul class="db-index">
        	<li><input type="radio" name="dbindex" value="0" checked class="lab-rad"/><label class="lab-midd">清空索引</label></li>
			<li><input type="radio" name="dbindex" value="1" class="lab-rad"/><label class="lab-midd">创建索引</label></li>
			<li><input type="radio" name="dbindex" value="2" class="lab-rad"/><label class="lab-midd">重建索引</label></li>
			<li><input type="button" value="执行" onclick="indexManage()" class="button4"/></li>
        </ul>
    </div>
</div>	
</body>
</html>