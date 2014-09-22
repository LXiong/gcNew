<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css?v=2'/>" rel="stylesheet" />
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
 	<script type="text/javascript" src="<c:url value='/js/changeTabColor.js'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">接听查询</h3>
   	<form action="<c:url value='/agentAnalyAction_answer.action'/>" method="post">
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
			
			<li><label>话务员号码：</label><input type="text" name="agent" class="ipt100 inputDefault" value="${agent }" maxlength="12"/></li>
	        <li>
	        	<label>呼叫方向：</label>
	        	<s:select name="callio" list="#{1:'呼入',2:'呼出'}" listKey="key" listValue="value" value="%{callio}" cssClass="inputDefault"></s:select>
			</li>
			<li><label>电话号码：</label><input type="text" name="telnum" class="ipt100 inputDefault" value="${telnum }" maxlength="12"/></li>
	        <li>
	        	<input type="submit" class="btn4" value="查&nbsp;&nbsp;询"/>
	        </li>
		</ul>
		<ul class="queryWrap_ul_w100 right">
	        <li></li>
		</ul>
	</div>
    </form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head2">
                 <tr>
                     <th width="8%">编号</th>
                     <th width="8%">主叫号码</th>
                     <th width="8%">被叫号码</th>
                     <th width="8%">呼叫日期</th>
                     <th width="8%">任务编号</th>
                     <th width="8%">电话编号</th>
                     <th width="8%">等待时长</th>
                     <th width="8%">通话时长</th>
                     <th width="8%">录音标识</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="answer" value="#session.vts.list">
				<tr align="center">
					<td>${cid }</td>
					<td>${ani }</td>
					<td>${dnis }</td>
					<td>${callio }</td>
					<td>${calldt }</td>
					<td>${tid }</td>
					<td>${ttid }</td>
					<td>${wait }</td>
					<td>${talk }</td>
					<td>${rectag }</td>
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
	
</div>
<script type="text/javascript">
	$(function(){
		var nowPage = parent.document.getElementById("curAgentAnalyPage").value;
		$("div.holder").jPages({
			containerID : "movies",
	        first : "首页",
	        previous : "上一页",
	        next : "下一页",
	        last : "尾页",
	        startPage : nowPage,
	        perPage : 25,
	        keyBrowse:true,
	        delay : 0,
	        callback : function( pages, items ){
				parent.document.getElementById("curAgentAnalyPage").value = pages.current;
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