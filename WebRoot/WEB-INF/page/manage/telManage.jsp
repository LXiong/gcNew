<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>扶贫观察点管理系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css?v=3'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css?v=4'/>" rel="stylesheet" />
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<!-- 日期控件开始 -->
    <link type="text/css" href="<c:url value='/datePicker/skin/WdatePicker.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/datePicker/WdatePicker.js'/>"></script>
    <!-- 日期控件结束 -->
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	<script type="text/javascript" src="<c:url value='/js/changeTabColor.js'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">${tname }&nbsp;(
	<c:if test="${kind eq 0 }">标准</c:if>
	<c:if test="${kind eq 1 }">回访1</c:if>
	<c:if test="${kind eq 2 }">回访2</c:if>
	<c:if test="${kind eq 3 }">回访3</c:if>
	)&nbsp;&nbsp;号码管理</h3>
   	<form name="form1" action="<c:url value='/task-telmanage.action'/>" method="post">
   	<input type="hidden" name="tid" value="${tid }"/>
   	<input type="hidden" name="tname" value="${tname }"/>
   	<input type="hidden" name="kind" value="${kind }"/>
   	
	<div class="queryDiv_h80">
	   	<ul class="queryWrap_ul">
			<li>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;电话号码：</label><input type="text" name="telnum" class="ipt100 inputDefault" value="<s:property value="telnum"/>"/>
			</li>
	        <li>
	        	<label>&nbsp;&nbsp;&nbsp;&nbsp;状态：</label>
	        	<s:select name="ddstate" list="#{20:'不限',0:'未调度',1:'正在呼叫',8:'错号空号',9:'黑名单',10:'呼叫完成'}" listKey="key" listValue="value" value="ddstate" cssClass="inputDefault"></s:select>
	        </li>
	        <li>
	        	<label>&nbsp;&nbsp;&nbsp;&nbsp;结果：</label>
	        	<s:select name="callret" list="#{20:'不限',1:'呼叫接通',2:'呼叫失败'}" listKey="key" listValue="value" value="callret" cssClass="inputDefault"></s:select>
	        </li>
			<li>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;起始编号：</label>
				<input type="text" id="startnumx" name="start" class="ipt40 inputDefault" value="<s:property value="start"/>"/>
				<span class="asterisk"></span>
			</li>	        
	        <li>&nbsp;&nbsp;<input type="button" onclick="queryTelnum()" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
		<ul class="queryWrap_ul">
	        <li>
	        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="filterBlack()" class="btn4" value="号码过滤" />
	        </li>
			<li>
	        	&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="button" onclick="recall()" class="btn4" value="重呼所有" /></label>
	        </li>
			<li>
	        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="saveTaskTel('add','${tid }','','')" class="btn4" value="添加" />
	        </li>
			<li>
	        	&nbsp;&nbsp;&nbsp;&nbsp;<label><a style="color:#00f" href="${pageContext.request.contextPath }/excelTemplate/huifang${kind }_import.xls">下载</a>模板</label>
	        </li>
	        <li>
	        	&nbsp;&nbsp;<input type="button" class="btn4" onclick="showSelFile('${tid }','${tname }','${kind }')" value="导入"/>
	        </li>
	        <li>
	        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn4" onclick="location.href='${pageContext.request.contextPath }/task-export.action?tid=${tid }&kind=${kind }'" value="导出"/>
	        </li>
			<li>
	        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn4" onclick="emptyTasktel('${tname }')" value="清空"/>
	        </li>
	        <li>
	        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn4" onclick="location.href='<c:url value='/task-list.action'/>'" value="返回"/>
	        </li>
		</ul>
	</div>
    </form>
	<div class="content_List528">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="10%">电话编号</th>
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
						<a href="<c:url value='/huifang-viewtel.action?tid=${tid }&ttid=${ttid }&tname=${tname }&kind=${kind }'/>">查看详细</a>&nbsp;&nbsp;
						<c:if test="${tel.stateid ne 1 }">
						<a href="<c:url value='/task-recall.action?tid=${tid }&ttid=${tel.ttid }&tname=${tname }'/>">重呼</a>&nbsp;&nbsp;
						</c:if>
						<a href="javascript:saveTaskTel('edit','${tid }','${tel.ttid }','${telnum }')">修改</a>&nbsp;&nbsp;
						<a href="javascript:deleteTaskTel('${tid }','${tel.ttid }')">删除</a>
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
		<form id="form2" name="form2" 
			action="${pageContext.request.contextPath }/task-importTaskTel.action" 
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
		<div class="lab_ipt_item" id="importBtnDiv">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="submit" class="btn4" value="确定"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<!-- empty form3 -->
	<form id="form3" action="<c:url value='/task-emptyTaskTel.action'/>" method="post">
		<input type="hidden" name="tid" value="${tid }"/>
		<input type="hidden" name="kind" value="${kind }"/>
		<input type="hidden" name="tname" value="${tname }"/>
	</form>
	
	<!-- delete tel -->
	<form id="form4" action="<c:url value='/task-deleteTaskTel.action'/>" method="post">
		<input type="hidden" id="del_tid" name="tid"/>
		<input type="hidden" id="del_ttid" name="ttid"/>
	</form>
	
	<!--POP LAYER START-->
	<div id="popSaveTaskTelDiv" style="display:none;"> 
		<form id="form5" action="${pageContext.request.contextPath }/task-saveTaskTel.action" method="post">
	    <input type="hidden" id="save_tidx" name="tid"/>
	    <input type="hidden" id="save_ttidx" name="ttid"/>
	    <div class="lab_ipt_item">
	    	<span class="lab120">电话号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="save_telnumx" name="telnum" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" onclick="saveTaskTelBtn()" class="btn4" value="确定"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<!-- 号码过滤 -->
	<form id="form6" action="<c:url value='task-blackFilter.action?tid=${tid }'/>" method="post"></form>
	
	<!-- recall -->
	<form id="form7" action="<c:url value='task-recall.action?tid=${tid }&tname=${tname }&kind=${kind }'/>" method="post"></form>
	
</div>

<script type="text/javascript">
	$(function(){
		var nowPage = parent.document.getElementById("curTelnumPage").value;
		$("div.holder").jPages({
			containerID : "movies",
	        first : "首页",
	        previous : "上一页",
	        next : "下一页",
	        last : "尾页",
	        startPage : nowPage,
	        perPage : 24,
	        keyBrowse:true,
	        delay : 0,
	        callback : function( pages, items ){
				parent.document.getElementById("curTelnumPage").value = pages.current;
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
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/telnumber.js?v=19'/>"></script>
</body>
</html>