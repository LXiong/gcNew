<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>电话自动外呼系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<!-- menu plugin start -->
	<link type="text/css" href="<c:url value='/style/menu.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/menu.js?v=8'/>"></script>
	<!-- menu plugin end -->
</head>
<body>
<div id="container">
<a id="popHuifang" target="mainFrame"></a>
	<!-- header -->
  	<div id="header">
  		<div class="tit1"><s:property value="#application.vta.product"/></div>
  		<div class="tit2"><s:property value="#application.vta.customer"/>
  			<!-- js 客户端测试 -->
  			<!-- 
  			<input type="button" onclick="js_detectcall('callin','ani=808;dnis=10086;param=a,1,1;')" value="测试弹屏"/>
  			<input type="button" onclick="js_monitor_acdgrp('5,933300,呼叫,0,0,0,0.00%,0/0')" value="测试业务组监控"/>
  			<input type="button" onclick="js_seat_minitor('0,正常,来电,*9000#,agt000')" value="测试分机监控"/>
  			<input id="button1" type="button" value="Button" onclick="changeOCX()"/>
  			-->
			<object id="OCXPlugin" classid="clsid:9730588D-7548-42E8-8779-F98D76A2A09E" width="0" height="0"></object>
  		</div>
  		<div class="tit3"><s:property value="#application.vta.provider"/></div>
    </div>
    <!-- nav -->
  	<div id="nav">
    	<div class="nav_left">
    		<div class="nav_left_wel">
    		<c:choose>
        		<c:when test="${sessionScope.vts.roleID eq 1 }">
        			<c:set var="homePage" value="sysparam-init.action"/>
        		</c:when>
        		<c:when test="${sessionScope.vts.roleID eq 2 }">
        			<c:set var="homePage" value="task-list.action"/>
        		</c:when>
        		<c:otherwise>
        			<c:set var="homePage" value="agentanaly-answer.action"/>
        		</c:otherwise>
        	</c:choose>
        	<span><a href="<c:url value='${homePage }'/>" target="mainFrame" title="首页" style="color:#fff;">首页</a></span>
    		<span>欢迎：&nbsp;<s:property value="#session.vts.roleName"/></span>
    		<span>
    			<s:property value="#session.vts.username"/>&nbsp;&nbsp;
    			<c:if test="${sessionScope.vts.roleID ne 1 }">[<s:property value="#session.vts.agttelnum"/>]</c:if>
    		</span>
    		</div>
    		<div id="navigate" class="nav_left_path">
    		</div>
        </div>
        <div class="nav_right">
            <span><a class="menu_righta" href="javascript:showUpdatePwdDiv()" id="bt">修改密码</a></span>
            <span><a class="menu_righta" href="javascript:logout()">[&nbsp;注销&nbsp;]</a></span>
            <c:if test="${sessionScope.vts.roleID eq 1 }">
            <span><a class="menu_righta" href="javascript:viewOCXLog()">查看日志</a></span>
            </c:if>
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
            <iframe id="mainFrame" name="mainFrame" src="<c:url value='${homePage }'/>" class="mainFrame" scrolling="no" marginwidth="1" marginheight="1" frameborder="0">
        		
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
        <input type="hidden" id="curAcdPage" value="1"/>
        <input type="hidden" id="curAgentPage" value="1"/>
        <input type="hidden" id="curSubtelPage" value="1"/>
        <input type="hidden" id="curBlackPage" value="1"/>
        <!-- 记录js分页当前页码 end -->
        </p>
        <span>Copyright @ 2005-2014 All Rights Reserved 版权所有 · 南京威帝通讯科技有限公司&nbsp;&nbsp;V140901</span>
        <!--
        <div id="ocxLog" style="font-size:12px; text-align:left; text-indent:10px;">vtcx log</div>
        -->
    </div>
    <!-- print window -->
	<div style="height:0px;">
	<iframe id="printFrame" name="printFrame" scrolling="no" width="1024" height="0" marginwidth="0" marginheight="0" frameborder="0" ></iframe>
	</div>
</div>

<!--POP UPDATEPASS START-->
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
<!--POP UPDATEPASS END-->


<!--POP OCXLOG START-->
<div id="popOCXLogDiv" style="display:none;"> 
	<div style="width:750px; height:300px; max-height:260px; overflow-y:auto;">
		<table width="100%" cellpadding="0" cellspacing="0" style="border-top:1px solid #3B9FFF; border-left:1px solid #3B9FFF; line-height:18px;">
	    	<thead class="tab_border">
	    		<tr style="font-weight:bold;">
		    		<td width="20%">&nbsp;时间&nbsp;</td>
		    		<td>&nbsp;日志信息</td>
		    	</tr>
	    	</thead>
	    	<tbody id="ocxLogTabId" class="tab_border"></tbody>
	    </table>
    </div>
</div>
<!--POP OCXLOG END -->

<form id="form2" action="<c:url value='/user-logout.action'/>" method="post"></form>
<!--POP LAYER END-->
<script type="text/javascript" src="<c:url value='/js/updatepwd.js?v=3'/>"></script>
<script type="text/javascript" src="<c:url value='/js/CM.ocxlog_view.js?v=1'/>"></script>
<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript">
	//logout
	function logout()
	{
		layer.confirm("确定要注销吗？",function(){
			$("#form2").ajaxSubmit({ 
				success:function(data){ //提交成功的回调函数
					location.href="index.action";
		        }  
			}); 
		    return false;
		});
	}
</script>
<script type="text/javascript">
	/*************** 弹屏   ***************/ 
	function js_detectcall(line,ani,dnis,param){
		param = param.split(",");
		if(param[0]=="a")
		{
			//获取回访类型
			$.ajax({
				type: "POST",
				dataType: "json",
				data: {tid: param[1]},
				url: "huifangType.action",
				success: function(data) {
					$("#popHuifang")[0].href="huifang-list.action?flag="+data+"&tid="+param[1]+"&ttid="+param[2];
					$("#popHuifang")[0].click();
				}
			});
		}
		else
		{
			return false;
		}
	}

	/*************** 业务组监控   ***************/ 
	/*
	5,,呼叫,0,0,0,0.00%,0/0
	@@ani,@@autocalldesc,@@trkapp,@@callnum,@@ansnum,@@ansrate,@@freenum/@@onlinenum"
	组编号,主叫号码,呼叫状态,补充中继数,呼叫总数,应答数,应答率,空闲座席数/在线总数,
	*/
	function js_monitor_acdgrp(fromClientCts, str){
		//check cts
		var curCts = "<s:property value='#session.vts.curCTS'/>";
		if(curCts==fromClientCts.toLowerCase())
		{
			//
			str = str.split(",");
			//获取表格对象
		    var tabObj = window.frames["mainFrame"].document.getElementById("acdMonitorTab");
		    if (tabObj == null)
			    return false;
			//将字符串转化成整型变量
			var i = parseInt(str[0]);
			if(tabObj.rows[i] == null)
				return false;
			tabObj.rows[i].cells[3].innerText=str[1];
			tabObj.rows[i].cells[4].innerText=str[2];
			tabObj.rows[i].cells[5].innerText=str[3];
			tabObj.rows[i].cells[6].innerText=str[4];
			tabObj.rows[i].cells[7].innerText=str[5];
			tabObj.rows[i].cells[8].innerText=str[6];
			tabObj.rows[i].cells[9].innerText=str[7];
			tabObj.rows[i].cells[10].innerText=str[8];
		}
		else
		{
			
		}
	}
	
	/*************** 座席分机监控  ***************/
	/*
	js提供的内容:电话编号,分机状态,呼叫方向,对方号码,登录话务员
	*/
	function js_seat_minitor(fromClientCts, str, listen){
		//check cts
		var curCts = "<s:property value='#session.vts.curCTS'/>";
		if(curCts==fromClientCts.toLowerCase())
		{
			str = str.split(",");
			//获取表格对象
			var tabObj = window.frames["mainFrame"].document.getElementById("subTelMonitorTab");
			//alert(tabObj);
			if (tabObj == null)
			    return false;
			var i = parseInt(str[0])+1;
			var tbrow;
			if(i >= tabObj.rows.length)
			{
				tbrow = tabObj.insertRow();
				tbrow.insertCell(0);
				tbrow.cells[0].innerText=str[0];
				tbrow.insertCell(1);
				tbrow.cells[1].innerText=str[1];
				tbrow.insertCell(2);
				tbrow.insertCell(3);
				tbrow.insertCell(4);
				tbrow.insertCell(5);
				tbrow.insertCell(6);
			}
			else
			{
				tbrow=tabObj.rows[i];
			}
			tbrow.cells[2].innerText=str[2];
			tbrow.cells[3].innerText=str[3];
			tbrow.cells[4].innerText=str[4];
			tbrow.cells[5].innerText=str[5];
			if(parseInt(listen)==0)
			{
				tbrow.cells[6].innerText='';
			}
			else
			{
				var tel = tbrow.cells[1].innerText;
				tbrow.cells[6].innerHTML="<a href=\"javascript:listen('"+tel+"')\">监听</a>";
			}
		}
		else
		{
			
		}
	}
</script>


<!-- ocx event -->
<script type="text/javascript" for="OCXPlugin" event="OnLog(info)">
	//js format date
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}

	//$("#ocxLog")[0].innerHTML=info;
	//get current time
	var d = new Date().Format("yyyy-MM-dd hh:mm:ss");
	//
	//显示OCX日志信息在弹出层
	var ocxTab = document.getElementById("ocxLogTabId").insertRow(0);
	var dtCol = ocxTab.insertCell(0);
	var ifCol = ocxTab.insertCell(1);
	dtCol.innerHTML="&nbsp;"+d;
	ifCol.innerHTML="&nbsp;"+info;
	
</script>

<script type="text/javascript" for="OCXPlugin" event="OnRing(line,ani,dnis,param)">
	//$("#ocxLog")[0].innerHTML=param;
	js_detectcall(line,ani,dnis,param);
</script>

<script type="text/javascript" for="OCXPlugin" event="OnACDReport(fromClientCts,str)">
	js_monitor_acdgrp(fromClientCts, str);
</script>

<script type="text/javascript" for="OCXPlugin" event="OnSubTelReport(fromClientCts,str,listen)">
	js_seat_minitor(fromClientCts, str, listen);
</script>

<script type="text/javascript">
	function changeOCX(){
		var ocxplugin = document.getElementById("OCXPlugin");
		ocxplugin.SetLine("1","100","2");
	}
</script>
<script type="text/javascript">
	$(function(){
		//判断OCX
		if(document.all.OCXPlugin.object==null)
		{
			//alert("VTCX控件不存在或没有正确加载，请使用360兼容模式或IE浏览器");
		}
		else
		{
		}
	});
</script>
<!-- 链接服务器并成功登录时发生的事件 -->
<script type="text/javascript" for="OCXPlugin" event="OnConnect(serverip,port,client)">
	//alert("链接服务器成功! IP:"+serverip+", 端口："+port+", 客户端账号:"+client);
</script>
<!-- 链接中断或无法链接服务器时,发生的事件 -->
<script type="text/javascript" for="OCXPlugin" event="OnError(code,info)">
	//alert("链接中断或无法链接服务器, 原因:"+info);
</script>

</body>
</html>
