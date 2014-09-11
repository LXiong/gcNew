<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>扶贫观察点管理系统</title>
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
 	<!-- layer 弹出插件 start -->
	<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
	<!-- layer 弹出插件 end -->
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">呼叫队列管理</h3>
   	<form action="<c:url value='/queueAction_home.action'/>" method="post" id="searchForm" name="searchForm">
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
			<li><label>开始日期：</label><input type="text" id="startdate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\')||\'2050-01-01\'}',skin:'whyGreen'})" value="<s:property value="startdate"/>"/></li>
	        <li><label>结束日期：</label><input type="text" id="enddate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}',maxDate:'%y-%M-%d',skin:'whyGreen'})" value="<s:property value="enddate"/>"/></li>
	        <li><input type="submit" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
		<ul class="queryWrap_ul_w100 right">
	        <li><input type="button" class="btn4" onclick="saveTask('add','0','','','')" value="添加任务"/></li>
		</ul>
	</div>
    </form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="10%">记录编号</th>
                     <th width="10%">任务名称</th>
                     <th width="30%">主叫号码</th>
                     <th>状态</th>
                     <th>类型</th>
                     <th>日期时间</th>
                     <th width="20%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="queue" value="#session.vts.list">
				<tr align="center">
					<td>${queue.tid }</td>
					<td>${queue.tname }</td>
					<td>${queue.ani }</td>
					<td>${queue.state }</td>
					<td>${queue.kind }</td>
					<td>${queue.cdt }</td>
					<td>
						
						<a href="javascript:saveTask('edit','${queue.tid }','${queue.tname }','${queue.ani }','${queue.overflowto }')">修改</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/queueAction_deleteQueue.action?tid=${queue.tid }">删除</a>
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
	    <input type="text" id="tzval" value="5" class="ipt20 inputDefault"/>
 		<button id="tiaozhuan" class="btn btn-primary">跳转</button>
	</div>
    <!-- jPage end -->
    
	
	<!--POP LAYER START-->
	<div id="popDiv" style="display:none;"> 
		<form name="form1" action="<c:url value='/queueAction_saveQueue.action'/>" method="post">
	    <input type="hidden" id="tidx" name="tid"/>
	    <div class="lab_ipt_item">
	    	<span class="lab120">任务名称：</span>
	        <div class="ipt-box">
	        	<input type="text" id="namex" name="name" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">外呼主叫号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="anix" name="ani" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="ywtype">
	    	<span class="lab120">业务类型：</span>
	        <div class="ipt-box">
	        	<s:select name="kind" list="#application.vta.GetList('taskkind')" listKey="id" listValue="str" cssStyle="height:28px;"></s:select>
	            <span class="asterisk"></span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="whnum">
	    	<span class="lab120">最大外呼线路数：</span>
	        <div class="ipt-box">
	        	<input type="text" id="maxlinex" name="maxline" value="0" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">(0则表示不限制)</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">分机号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="overflowtox" name="overflowto" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="submitSaveQueueBtn()"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
</div>
<script type="text/javascript">
	function saveTask(t,tid,tname,ai,overto)
	{
		var tit;
		if(t=="add")
		{
			tit="添加任务";
			$("#ywtype").show();
			$("#whnum").show();
		}
		else
		{
			tit="修改任务";
			$("#ywtype").hide();
			$("#whnum").hide();
		}
		//
		$("#tidx").val(tid);
		$("#namex").val(tname);
		$("#anix").val(ai);
		$("#overflowtox").val(overto);
		
		$.layer({
			type: 1,
	        title: tit,
	        offset: [($(window).height() - 290)/2+'px', ''],
	        border : [5, 0.5, '#666'],
	        area: ['450px','300px'],
	        shadeClose: false,
			bgcolor: '#fff',
			page:{dom:'#popDiv'}
		});
	}
	//提交按钮 
	function submitSaveQueueBtn()
	{
		document.form1.submit();
	}
	

	$(function(){
		$("div.holder").jPages({
			containerID : "movies",
	        first : "首页",
	        previous : "上一页",
	        next : "下一页",
	        last : "尾页",
	        perPage : 26,
	        keyBrowse:true,
	        delay : 5,
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
</body>
</html>