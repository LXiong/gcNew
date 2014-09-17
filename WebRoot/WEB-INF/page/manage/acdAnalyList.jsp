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
 	<!-- layer 弹出插件 start -->
	<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/layer/extend/layer.ext.js'/>"></script>
	<!-- layer 弹出插件 end -->
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">业务组分配统计&nbsp;[<s:property value="#session.vts.curCTS"/>]</h3>
   	<form action="<c:url value='/acdAnalyAction_home.action'/>" method="post">
   	<input type="hidden" name="cts" value="<s:property value="#session.vts.curCTS"/>"/>
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
			<li><label>开始日期：</label><input type="text" id="sdt" name="sdt" class="Wdate inputDefault" style="width:90px; height:18px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'edt\')||\'2050-01-01\'}',skin:'whyGreen'})" value="<s:property value="sdt"/>"/></li>
	        <li><label>结束日期：</label><input type="text" id="edt" name="edt" class="Wdate inputDefault" style="width:90px; height:18px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'sdt\')}',maxDate:'%y-%M-%d',skin:'whyGreen'})" value="<s:property value="edt"/>"/></li>
	        <li><input type="submit" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
		<ul class="queryWrap_ul_w100 right">
	        <li></li>
		</ul>
	</div>
    </form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="10%">队列编号</th>
                     <th width="10%">队列号码</th>
                     <th width="10%">队列名称</th>
                     <th width="10%">分配总次数</th>
                     <th width="10%">分配成功数</th>
                     <th width="10%">放弃数</th>
                     <th width="10%">溢出数</th>
                     <th width="10%">超时数</th>
                     <th width="10%">分配错误</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="acd" value="#session.vts.list">
				<tr align="center">
					<td>${acd.grpid }</td>
					<td>${acd.telnum }</td>
					<td>${acd.grpname }</td>
					<td>${acd.tn }</td>
					<td>${acd.ats }</td>
					<td>${acd.at1 }</td>
					<td>${acd.at2 }</td>
					<td>${acd.at3 }</td>
					<td>${acd.at4 }</td>
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
	
</div>
<script type="text/javascript">
	$(function(){
		var nowPage = parent.document.getElementById("curAcdAnalyPage").value;
		$("div.holder").jPages({
			containerID : "movies",
	        first : "首页",
	        previous : "上一页",
	        next : "下一页",
	        last : "尾页",
	        startPage : nowPage,
	        perPage : 26,
	        keyBrowse:true,
	        delay : 0,
	        callback : function( pages, items ){
				parent.document.getElementById("curAcdAnalyPage").value = pages.current;
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