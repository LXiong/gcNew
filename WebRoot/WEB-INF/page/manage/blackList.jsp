<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>扶贫观察点管理系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	
 	
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
 	<!-- layer 弹出插件 start -->
	<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
	<!-- layer 弹出插件 end -->
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css?v=1'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	<script type="text/javascript" src="<c:url value='/js/black.js?v=1'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">呼叫黑名单</h3>
   	<form action="<c:url value='/blackAction_home.action'/>" method="post" id="searchForm" name="searchForm">
   	<div class="queryDiv">
   		<ul class="queryWrap_ul_w600 left">
	        <li><label>电话号码：</label><input type="text" class="ipt155" name="telnum" id="telnum" value="<s:property value="telnum"/>"/></li>
	        <li><input type="submit" id="searchImg" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
		<ul class="queryWrap_ul_w100 right">
	        <li><input type="button" class="btn4" onclick="saveBlackNumber('add','','','')" value="添加"/></li>
		</ul>
   	</div>
   	
    </form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="10%">记录编号</th>
                     <th width="10%">电话号码</th>
                     <th width="30%">备注信息</th>
                     <th width="20%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="black" value="#session.vts.list">
				<tr align="center">
					<td><s:property value="#black.bid"/></td>
					<td><s:property value="#black.telnum"/></td>
					<td align="left" class="tabtd1">&nbsp;&nbsp;<s:property value="#black.noteinfo"/></td>
					<td>
						<a href="javascript:saveBlackNumber('edit','<s:property value="#black.bid"/>','<s:property value="#black.telnum"/>','<s:property value="#black.noteinfo"/>')">修改</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/blackAction_deleteTelnum.action?bid=<s:property value='#black.bid'/>">删除</a>
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
		<form name="form1" action="<c:url value='/blackAction_saveTelnum.action'/>" method="post">
	    <input type="hidden" id="bidx" name="bid"/>
	    <div class="lab_ipt_item">
	    	<span class="lab100">电话号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="telnumx" name="telnum" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="h132">
	    	<span class="lab100">备注信息：</span>
	        <div class="h132 ipt-box">
	        	<textarea id="noteinfox" name="noteinfo" class="ipt_textarea_w300 inputDefault" style="font-size:12px;"></textarea>
	            <span></span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab100"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="submitSaveNumberBtn()"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
</div>
<script type="text/javascript">
	
	//jPage分页 
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