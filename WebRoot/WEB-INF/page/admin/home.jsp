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
	<script type="text/javascript">
		function js_detectcall(call,param)
		{
			var tid = param.substring(param.length-2, param.length-1);
			var ttid = param.substring(param.length-4, param.length-3);
			//获取回访类型
			$.ajax({
				type: "POST",
				dataType: "json",
				data: {tid: tid},
				url: "huifangType.action",
				success: function(data) {
					$("#test")[0].href="huifang-list.action?flag="+data+"&tid="+tid+"&ttid="+ttid;
					$("#test")[0].click();
				}
			});
			
		}

		/*************** 实时刷新   ***************/ 
		/*
		function js_monitor_acdgrp(str){
			str = str.split(",");
			//获取表格对象
		    var tableObj = window.frames["mainFrame"].document.getElementById("monitorTab");
		    var start = window.frames["mainFrame"].document.getElementById("start"+str[0]);
			var stop = window.frames["mainFrame"].document.getElementById("stop"+str[0]);
		    if (tableObj == null)
			    return false;
		    if(str[1]=='停止'){
				tableObj.rows[str[0]].cells[0].innerHTML='<img src="images/uncall.gif"/>';
				start.disabled=false;
				stop.disabled=true;
				stop.removeAttribute("href"); 
				start.setAttribute("href","javascript:startQueue('"+str[0]+"')");
			}else{
				tableObj.rows[str[0]].cells[0].innerHTML='<img src="images/call.gif"/>';
				start.disabled=true;
				stop.disabled=false;
				start.removeAttribute("href"); 
				stop.setAttribute("href","javascript:stopQueue('"+str[0]+"')");
			}
			//获取表格总行数
			var tabrowlen = tableObj.rows.length;
			//获取表格总列数
			var tabcollen = tableObj.rows[0].cells.length;
			//将字符串转化成整型变量
			var i = parseInt(str[0]);
			
			tableObj.rows[i].cells[3].innerText=str[1];
			tableObj.rows[i].cells[4].innerText=str[2];
			tableObj.rows[i].cells[5].innerText=str[3];
			tableObj.rows[i].cells[6].innerText=str[4];
			tableObj.rows[i].cells[7].innerText=str[5];
			tableObj.rows[i].cells[8].innerText=str[6];
		}
		*/

		/*************** 座席分机监控  ***************/
		/*
		function js_seat_minitor(str){
			str = str.split(",");
			//获取表格对象
			var tableObj = window.frames["mainFrame"].document.getElementById("monitorTab");
			if (tableObj == null)
			    return false;
			var tabrowlen = tableObj.rows.length;
			var tabcolnum = tableObj.rows[0].cells.length-1;
			var i = parseInt(str[0])+1;
			tableObj.rows[i].cells[1].innerText=str[0];
			tableObj.rows[i].cells[2].innerText=str[1];
			tableObj.rows[i].cells[3].innerText=str[2];
			tableObj.rows[i].cells[4].innerText=str[3];
			tableObj.rows[i].cells[5].innerText=str[4];
			tableObj.rows[i].cells[6].innerText=str[5];	
			
		}
		*/
	</script>
</head>
<body>
<div id="container">
<a id="test" target="mainFrame"></a>
	<!-- header -->
  	<div id="header">
  		<div class="tit1"><s:property value="#application.vta.product"/></div>
  		<div class="tit2"><s:property value="#application.vta.customer"/>
  			<input type="button" onclick="js_detectcall('callin','ani=808;dnis=10086;param=a,1,1;')" value="测试一"/>
  			<input type="button" onclick="js_monitor_acdgrp('1,2,2,2,2,2,2')" value="测试二"/>
  		</div>
  		<div class="tit3"><s:property value="#application.vta.provider"/></div>
    </div>
    <!-- nav -->
  	<div id="nav">
    	<div class="nav_left">
    		<div class="nav_left_wel">
    		<span>欢迎：&nbsp;<s:property value="#session.vts.roleName"/></span><span><s:property value="#session.vts.username"/></span>
    		</div>
    		<div id="navigate" class="nav_left_path">
    			
    		</div>
        </div>
        <div class="nav_right">
        	<span>
        		<label>当前服务器：</label><s:select list="#session.vts.ctsList" onchange="changeServer(this)" listKey="ctsinfo" listValue="ctsname" value="#session.vts.curCTS" cssStyle="height:22px; margin:1px;"></s:select>
        	</span>
            <span><a class="menu_righta" href="javascript:showUpdatePwdDiv()" id="bt">修改密码</a></span>
            <span><a class="menu_righta" href="javascript:popLogoutDiv()" target="_top">[&nbsp;注销&nbsp;]</a></span>
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
        <!-- 记录js分页当前页码 start -->
        <input type="hidden" id="curTaskPage" value="1"/>
        <input type="hidden" id="curTelnumPage" value="1"/>
        <input type="hidden" id="curAcdAnalyPage" value="1"/>
        <input type="hidden" id="curAcdPage" value="1"/>
        <input type="hidden" id="curAgentAnalyPage" value="1"/>
        <input type="hidden" id="curAgentPage" value="1"/>
        <input type="hidden" id="curCallRecordPage" value="1"/>
        <input type="hidden" id="curSubtelPage" value="1"/>
        <input type="hidden" id="curBlackPage" value="1"/>
        <!-- 记录js分页当前页码 end -->
        </p>
        <span>Copyright @ 2005-2014 All Rights Reserved 版权所有 · 南京威帝通讯科技有限公司&nbsp;&nbsp;V140901</span>
    </div>
    <!-- print window -->
	<div style="height:0px;">
	<iframe id="printFrame" name="printFrame" scrolling="no" width="1024" height="0" marginwidth="0" marginheight="0" frameborder="0" ></iframe>
	</div>
</div>

<!--POP LAYER START-->
<div id="popDiv" style="display:none;"> 
	<form id="form1" action="<c:url value='/user-updatePwd.action'/>" method="post" onsubmit="return validatePwdinput(this)">
    <div class="lab_ipt_item">
    	<span class="lab120">账号：</span>
        <div class="ipt-box">
        	<label><s:property value="#session.vts.account"/></label>
        </div>
    </div>
    <div class="lab_ipt_item">
    	<span class="lab120">原密码：</span>
        <div class="ipt-box">
        	<input type="text" id="oldpwdx" name="oldpwd" class="ipt_text_w150 inputDefault" />
            <span class="asterisk">*</span>
        </div>
    </div>
    <div class="lab_ipt_item" id="ywtype">
    	<span class="lab120">新密码：</span>
        <div class="ipt-box">
        	<input type="password" id="newpwdx" name="newpwd" class="ipt_text_w150 inputDefault" />
            <span class="asterisk">*</span>
        </div>
    </div>
    <div class="lab_ipt_item" id="whnum">
    	<span class="lab120">确认密码：</span>
        <div class="ipt-box">
        	<input type="password" id="repwdx" class="ipt_text_w150 inputDefault" />
            <span class="asterisk">*</span>
        </div>
    </div>
	<div class="lab_ipt_item">
		<span class="lab120"></span>
		<div class="ipt-box"><input type="submit" class="btn4" value="确定"/></div>
		<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
	</div>	
	</form>
</div>
<!--POP LAYER END-->
<script type="text/javascript" src="<c:url value='/js/updatepwd.js?v=3'/>"></script>
<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript">

	//logout
	function popLogoutDiv()
	{
		layer.confirm("确定要注销吗？",function(){
			location.href="user-logout.action";
		});
	}
	//change cts server
	function changeServer(obj)
	{
		var ctsVal = obj.options[obj.selectedIndex].text;
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			dataType: "json",
			data: {cts: ctsVal},
			url: "saveCTS.action",
			success: function() {
				
			}
		});
	}
</script>
</body>
</html>
