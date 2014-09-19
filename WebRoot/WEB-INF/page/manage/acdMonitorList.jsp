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
	<h3 class="h3_title">业务组监控&nbsp;[<s:property value="#session.vts.curCTS"/>]</h3>
   	<form action="<c:url value='/acdAnalyAction_home.action'/>" method="post">
   	<input type="hidden" name="cts" value="<s:property value="#session.vts.curCTS"/>"/>
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
			<li></li>
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
                     <th width="4%">编号</th>
                     <th width="10%">名称</th>
                     <th width="4%">状态</th>
                     <th width="4%">T+</th>
                     <th width="8%">号码总数</th>
                     <th width="8%">完成数</th>
                     <th width="8%">百分比</th>
                     <th width="8%">外呼数</th>
                     <th width="8%">应答数</th>
                     <th width="8%">在线客服</th>
                     <th width="20%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
				<tr align="center">
					<td>1</td>
					<td>业务1</td>
					<td>呼叫</td>
					<td>23</td>
					<td>20</td>
					<td>10</td>
					<td>50%</td>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>
						<a href="#">启动</a>&nbsp;
						<a href="#">暂停</a>&nbsp;
						<a href="#">++</a>&nbsp;
						<a href="#">--</a>&nbsp;
						<a href="#">设置</a>&nbsp;
					</td>
				</tr>
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