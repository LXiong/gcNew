<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<script type="text/javascript" src="<c:url value='/layer/extend/layer.ext.js'/>"></script>
	<!-- layer 弹出插件 end -->
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	<script type="text/javascript" src="<c:url value='/js/changeTabColor.js'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">任务完成统计</h3>
   	<c:if test="${sessionScope.vts.roleID ne 3 }">
   	<form action="<c:url value='/agentanaly-taskcomplete.action'/>" method="post">
	<div class="queryDiv">
	   	<ul class="queryWrap_ul left">
	   		<li><label>话务员号码：</label>
	   			<s:select list="alist" cssStyle="width:80px; height:22px;" headerKey="" headerValue="--请选择话务员--" listKey="telnum" listValue="account" name="telnum" value="telnum"></s:select>
	   		</li><li><input type="submit" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
	</div>
    </form>
    </c:if>
    <c:choose>
    	<c:when test="${sessionScope.vts.roleID ne 3 }">
    		<c:set var="divh" value="content_List568"></c:set>
    	</c:when>
    	<c:otherwise>
    		<c:set var="divh" value="content_List615"></c:set>
    	</c:otherwise>
    </c:choose>
	<div class="${divh }">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head2">
                 <tr>
                     <th width="10%">任务号</th>
                     <th width="20%">任务名称</th>
                     <th width="10%">成功数</th>
                     <th width="10%">失败数</th>
                     <th width="10%">号码总数</th>
                     <th width="10%">接通总数</th>
                     <th width="10%">成功率</th>
                 </tr>
             </thead>
             <tbody id="movies">
             	<c:forEach items="${tcList }" var="ls">
				<tr align="center">
					<td>${ls.tid }</td>
					<td>${ls.tname }</td>
					<td>${ls.cgn }</td>
					<td>${ls.sbn }</td>
					<td>${ls.tn }</td>
					<td>${ls.ans }</td>
					<td>${ls.cgr }</td>
				</tr>
				</c:forEach>
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
		$("div.holder").jPages({
			containerID : "movies",
	        first : "首页",
	        previous : "上一页",
	        next : "下一页",
	        last : "尾页",
	        startPage : 1,
	        perPage : 25,
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
</body>
</html>