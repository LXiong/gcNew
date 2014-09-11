<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/menu.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/menu.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/updatepwd.js'/>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			confPass.init();
		});
	</script>
	<style type="text/css">
		#overlay{position:absolute;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);display:none;} 
		#win{position:absolute;top:50%;left:50%;width:500px;height:240px;background:#EAECEA;border:4px solid #F7F7F7;margin:-102px 0 0 -202px;display:none;} 
		h2{font-size:12px;height:18px;text-align:right;background:#3F89EC;border-bottom:3px solid #F7F7F7;padding:5px;cursor:move;} 
		h2 span{border:0px solid #f90;padding:0 2px;} 
	</style>
</head>
<body>
<div id="container">
	<!-- header -->
  	<div id="header">
  		<div class="tit1"><s:property value="#application.vta.product"/></div>
  		<div class="tit2"><s:property value="#application.vta.customer"/></div>
  		<div class="tit3"><s:property value="#application.vta.provider"/></div>
    </div>
    <!-- nav -->
  	<div id="nav">
    	<div class="nav_left">
    		<div class="nav_left_wel">
    		<span>欢迎：&nbsp;<s:property value="#session.vts.roleName"/></span><span><s:property value="#session.vts.username"/></span>
    		</div>
    		<div id="navigate" class="nav_left_path"></div>
        </div>
        <div class="nav_right">
            <span><a href="javascript:popDivOpen()" id="bt">修改密码</a></span>
            <span><a href="${pageContext.request.contextPath }/userAction_logout.action" onclick="return confirm('你确定要注销吗?')" target="_top">[&nbsp;注销&nbsp;]</a></span>
        </div>
    </div>
    <!-- main -->
  	<div id="main">
    	<div class="main_left">
            <ul class="menu">
                <s:property value="#application.vta.getMenuInfoByRoleID(#session.vts.roleID, #session.vts.account)" escape="false"/>
            </ul>
        </div>
        <div class="main_right">
            <iframe name="mainFrame" src="main.jsp" class="mainFrame" scrolling="no" marginwidth="1" marginheight="1" frameborder="0">
        		
            </iframe>
        </div>
        <div class="clear"></div>
  	</div>
  	<!-- footer -->
  	<div id="footer">
        <p>
        <a href="#">设为首页</a>&nbsp;|&nbsp;
        <a href="#">收藏本站</a>&nbsp;|&nbsp;
        <a href="#">联系我们</a>&nbsp;|&nbsp;
        <a href="#">帮助中心</a>&nbsp;|&nbsp;
        <a href="#">常见问题</a>
        <!-- 记录js分页当前页码 -->
        <input type="hidden" id="globalCurPage"/>
        </p>
        <span>Copyright @ 2005-2014 All Rights Reserved 版权所有 · 南京威帝通讯科技有限公司&nbsp;&nbsp;V140701</span>
    </div>
    <!-- print window -->
	<div style="height:0px;">
	<iframe id="printFrame" name="printFrame" scrolling="no" width="1024" height="0" marginwidth="0" marginheight="0" frameborder="0" ></iframe>
	</div>
</div>
<!-- update password start -->
<div id="overlay"></div> 
<div id="win" style="line-height: 30px">
	<h2 style="line-height:20px; text-align:left"><span><font color="#fff">修改密码</font></span></h2>
	<form name="passForm" action="" method="post">
	<div class="safe-list">
		<table width="450px" cellpadding="0" cellspacing="0">
			<tr>
				<td width="30%" align="right">账号:&nbsp;&nbsp;</td>
				<td width="35%" align="left"><s:property value="#session.vts.account"/></td>
				<td width="35%" align="left"></td>
			</tr>
			<tr>
				<td width="30%" align="right">原密码:&nbsp;&nbsp;</td>
				<td width="35%" align="left"><input id="oldpwd" name="oldpwd" class="inpasstxt" type="password" onfocus="this.className='inpasstxt_on'" onblur="this.className='inpasstxt_off'"/></td>
				<td width="35%" align="left"><span id="oldpwdtips" class="ptips"></span></td>
			</tr>
			<tr>
				<td width="30%" align="right">新密码:&nbsp;&nbsp;</td>
				<td width="35%" align="left"><input id="newpwd" name="newpwd" class="inpasstxt" type="password" onfocus="this.className='inpasstxt_on'" onblur="this.className='inpasstxt_off'"/></td>
				<td width="35%" align="left"><span id="newpwdtips" class="ptips"></span></td>
			</tr>
			<tr>
				<td width="30%" align="right">确认密码:&nbsp;&nbsp;</td>
				<td width="35%" align="left"><input id="repwd" name="repwd" class="inpasstxt" type="password" onfocus="this.className='inpasstxt_on'" onblur="this.className='inpasstxt_off'"/></td>
				<td width="35%" align="left"><span id="repwdtips" class="ptips"></span></td>
			</tr>
		</table>
	</div>
	<div style="margin-top:5px;">
		<input id="submitButton" type="button" value="确定" class="button4"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="close" type="button" value="取消" class="button4"/>
	</div>
	</form>
</div> 
<!-- update password end -->
</body>
</html>
