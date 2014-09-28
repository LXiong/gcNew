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
	<!-- 日期控件开始 -->
    <link type="text/css" href="<c:url value='/datePicker/skin/WdatePicker.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/datePicker/WdatePicker.js'/>"></script>
    <!-- 日期控件结束 -->
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
	<h3 class="h3_title">座席组监控&nbsp;
	<s:select list="#session.vts.ctsList" onchange="changeServer(this)" listKey="ctsname" listValue="ctsname" value="#session.vts.curCTS" cssStyle="height:22px; margin:1px;"></s:select>
	</h3>
	<div class="content_List615">
		<table id="acdMonitorTab" cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="4%">组编号</th>
                     <th width="8%">组号码</th>
                     <th width="8%">组名称</th>
                     <th width="6%">外呼主叫</th>
                     <th width="4%">任务</th>
                     <th width="4%">状态</th>
                     <th width="6%">添加中继数</th>
                     <th width="4%">外呼数</th>
                     <th width="4%">应答数</th>
                     <th width="4%">应答率</th>
                     <th width="6%">在线客服</th>
                     <th width="14%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
             	<s:iterator id="monitor" value="#session.vts.list">
				<tr align="center">
					<td>${grpid }</td>
					<td>${telnum }</td>
					<td>${grpname }</td>
					<td>${ani }</td>
					<td>${taskid }</td>
					<td>${curstate }</td>
					<td>${addtrk }</td>
					<td>${obt_num }</td>
					<td>${ans_num }</td>
					<td>${ans_rate }</td>
					<td>${agt_num }</td>
					<td>
						<a href="javascript:setCaller('${grpid }','${ani }')">主叫</a>&nbsp;&nbsp;
						<a href="javascript:editTrunk('${grpid }','1')" title="增加中继数">++</a>&nbsp;&nbsp;
						<a href="javascript:editTrunk('${grpid }','-1')" title="减少中继数">--</a>&nbsp;&nbsp;
						<a href="javascript:editCall('${grpid }','1')">启动</a>&nbsp;&nbsp;
						<a href="javascript:editCall('${grpid }','0')">停止</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<!-- jPage start -->
   	<div class="holder left"></div>
   	<div id="legend1" class="holder left"></div>
    <!-- Item oriented legend -->
    <div id="legend2" class="holder left"></div>
    <div class="left">
	    <input type="text" id="tzval" value="1" class="ipt20 inputDefault"/>
 		<button id="tiaozhuan" class="btn btn-primary">跳转</button>
	</div>
    <!-- jPage end -->
    <!--POP LAYER START-->
	<div id="popDiv" style="display:none;"> 
		<form id="form1" action="<c:url value='/acdmonitor-setCaller.action'/>" method="post">
	    <input type="hidden" id="grpidx" name="grpid"/>
	    <div class="lab_ipt_item" id="whnum">
	    	<span class="lab150">主叫号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="anix" name="ani" value="" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" onclick="setCallerBtn()" class="btn4" value="确定"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
</div>
<script type="text/javascript">
	$(function(){
		$("div.holder").jPages({
			containerID : "movies",
	        first : "首页",
	        previous : "上一页",
	        next : "下一页",
	        last : "尾页",
	        startPage : 1,
	        perPage : 28,
	        keyBrowse:true,
	        delay : 0,
	        callback : function( pages, items ){
		        $("#legend1").html("&nbsp;&nbsp;当前第"+pages.current+"页 ,&nbsp;&nbsp;总共"+pages.count+"页,&nbsp;&nbsp;");
		        $("#legend2").html("当前显示第"+items.range.start+" - "+items.range.end+"条记录,&nbsp;&nbsp;总共"+items.count+"条记录&nbsp;&nbsp;");
		    }
		});
	      /* when button is clicked */
    	$("#tiaozhuan").click(function(){
      		/* get given page */
			var page = parseInt( $("#tzval").val() );

      		/* jump to that page */
      		$("div.holder").jPages( page );

    	});
	});
</script>
<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<script type="text/javascript" src="<c:url value='/js/acd_monitor.js?v=7'/>"></script>
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cts.js?v=2'/>"></script>
</body>
</html>