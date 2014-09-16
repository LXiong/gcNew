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
	<script type="text/javascript" src="<c:url value='/layer/extend/layer.ext.js'/>"></script>
	<!-- layer 弹出插件 end -->
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	<!-- ajax file upload -->
 	<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
 	<script type="text/javascript" src="<c:url value='/js/telnumber.js?v=4'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">${tname }&nbsp;号码管理</h3>
   	<form name="form1" action="<c:url value='/taskAction_telmanage.action'/>" method="post">
   	<input type="hidden" name="tid" value="${tid }"/>
   	<input type="hidden" name="tname" value="${tname }"/>
   	
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w300 left">
			<li><label>电话号码：</label><input type="text" name="telnum" class="ipt100" value="<s:property value="telnum"/>"/></li>
	        <li><input type="submit" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
		<ul class="queryWrap_ul_w500 right">
			<li>
	        	<label><input type="button" onclick="recall('${tid }','${tname }')" class="btn4" value="重呼所有" /></label>
	        </li>
			<li>
	        	<label>点击<a style="color:#00f" href="${pageContext.request.contextPath }/excelTemplate/huifang${kind }_import.xls">下载</a>模板文件</label>
	        </li>
	        <li>
	        	<input type="button" class="btn4" onclick="showSelFile('${tid }','${tname }','${kind }')" value="导入"/>
	        </li>
	        <li>
	        	<input type="button" class="btn4" onclick="location.href='${pageContext.request.contextPath }/taskAction_export.action?tid=${tid }&kind=${kind }'" value="导出"/>
	        </li>
			<li>
	        	<input type="button" class="btn4" onclick="emptyTasktel('${tname }')" value="清空"/>
	        </li>
	        <li>
	        	<input type="button" class="btn4" onclick="location.href='<c:url value='/taskAction_home.action'/>'" value="返回"/>
	        </li>
		</ul>
	</div>
    </form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="20%">电话编号</th>
                     <th width="20%">电话号码</th>
                     <th width="20%">状态</th>
                     <th width="20%">呼叫结果</th>
                     <th width="20%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="tel" value="#session.vts.list">
				<tr align="center">
					<td>${tel.ttid }</td>
					<td>${tel.telnum }</td>
					<td>${tel.state }</td>
					<td>${tel.callret }</td>
					<td>
						<a href="<c:url value='/taskAction_recall.action?tid=${tid }&ttid=${tel.ttid }&tname=${tname }'/>">重呼</a>&nbsp;&nbsp;
						<a href="javascript:deleteTaskTelPre('${tid }','${tel.ttid }')">删除</a>
						<input type="button" class="hide" onclick="deleteTaskTel()" value="删除"/>
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
		<form id="form2" name="form2" 
			action="${pageContext.request.contextPath }/taskAction_importTaskTel.action" 
			method="post" 
			enctype="multipart/form-data"
			onsubmit="return validateuploadInforFile(this)">
	    <input type="hidden" id="tidx" name="tid"/>
	    <input type="hidden" id="kindx" name="kind"/>
	    <div class="lab_ipt_item">
	    	<span class="lab120">您当前导入的文件：</span>
	        <div class="ipt-box">
	        	<label id="curFile"></label>&nbsp;&nbsp;
	        	<span class="asterisk"></span>
	        </div>
	    </div>
	    <input type="file" id="uploadExcel" name="uploadExcel" style="display:none;" onchange="showPopDiv()"/>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="submit" class="btn4" value="确定"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<!-- empty form3 -->
	<form id="form3" name="form3" action="<c:url value='/taskAction_emptyTaskTel.action'/>" method="post">
		<input type="hidden" name="tid" value="${tid }"/>
		<input type="hidden" name="kind" value="${kind }"/>
		<input type="hidden" name="tname" value="${tname }"/>
	</form>
	
	<!-- delete tel -->
	<form name="form4" action="<c:url value='/taskAction_deleteTaskTel.action'/>" method="post">
		<input type="hidden" id="del_tid" name="tid"/>
		<input type="hidden" id="del_ttid" name="ttid"/>
	</form>
	
</div>

<script type="text/javascript">
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