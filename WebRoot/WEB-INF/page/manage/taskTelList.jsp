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
 	
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">呼叫队列管理</h3>
   	<form action="<c:url value='/taskTelAction_home.action'/>" method="post" id="searchForm" name="searchForm">
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
			<li><label>开始日期：</label><input type="text" id="startdate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\')||\'2050-01-01\'}',skin:'whyGreen'})" value="<s:property value="startdate"/>"/></li>
	        <li><label>结束日期：</label><input type="text" id="enddate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}',maxDate:'%y-%M-%d',skin:'whyGreen'})" value="<s:property value="enddate"/>"/></li>
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
                     <th width="12%">任务编号</th>
                     <th width="12%">任务名称</th>
                     <th width="12%">任务类型</th>
                     <th width="12%">号码总数</th>
                     <th width="12%">新建数</th>
                     <th width="12%">执行中</th>
                     <th width="12%">执行完成</th>
                     <th width="12%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="queue" value="#session.vts.list">
				<tr align="center">
					<td>${queue.tid }</td>
					<td>${queue.tname }</td>
					<td>
						<c:if test="${queue.kind eq 0 }">标准</c:if>
						<c:if test="${queue.kind eq 1 }">回访1</c:if>
						<c:if test="${queue.kind eq 2 }">回访2</c:if>
						<c:if test="${queue.kind eq 3 }">回访3</c:if>
					</td>
					<td>${queue.trn }</td>
					<td>${queue.nrn }</td>
					<td>${queue.drn }</td>
					<td>${queue.frn }</td>
					<td>
						<a href="javascript:showSelFile('${queue.tid }','${queue.tname }','${queue.kind }')">导入</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/taskTelAction_export.action?tid=${queue.tid }&kind=${queue.kind }">导出</a>
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
		<iframe name="importFrame" style="display:none;"></iframe>
		<form id="form2" name="form2" 
			action="${pageContext.request.contextPath }/taskTelAction_importTaskTel.action" 
			method="post" 
			enctype="multipart/form-data"
			onsubmit="return validateuploadInforFile(this)">
	    <input type="hidden" id="tidx" name="tid"/>
	    <input type="hidden" id="kindx" name="kind"/>
	    <div class="lab_ipt_item">
	    	<span class="lab120">您当前导入的文件：</span>
	        <div class="ipt-box"><label id="curFile"></label></div>
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
	
</div>

<script>
	//
	function showSelFile(tid,tname,kind)
	{
		$("#uploadExcel").click();
		$("#tidx").val(tid);
		$("#kindx").val(kind);
	}
	//
	function showPopDiv()
	{
		$("#curFile")[0].innerHTML=$("#uploadExcel").val();
		$.layer({
			type: 1,
	        title: '导入 数据',
	        offset: [($(window).height() - 290)/2+'px', ''],
	        border : [5, 0.5, '#666'],
	        area: ['450px','200px'],
	        shadeClose: false,
			bgcolor: '#fff',
			page:{dom:'#popDiv'}
		});
	}

	function validateuploadInforFile(form)
	{
		if(!validateExcelUpLoadFile(form)) return false;
		$("#form2").ajaxSubmit({ 
            success:function(data){ //提交成功的回调函数
    			layer.closeAll(); 
            	if(!data)
            	{ 
    				layer.msg("数据导入成功",2,1);
            	}
            	else
            	{
    				layer.msg("数据导入失败，请重新导入 ",2,5);
            	}
            }  
		}); 
        return false;	//not refresh page
	}

	//导入Excel文件时进行文件格式校验 
	function validateExcelUpLoadFile(form)
	{
		var fileName = form2.uploadExcel.value;
	    if (fileName != "" ) {
	        var fileType = (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)).toLowerCase();
	        var suppotFile = ["xls", "XLS", "xlsx", "XLSX"];
	        for (var i = 0; i < suppotFile.length; i++) {
	            if (suppotFile[i] == fileType) {
	                return true;
	            } else {
	                continue;
	            }
	        }
	        alert("文件格式不正确！");
	        return false;
	    } else {
	        alert("请选择你需要导入的文件");
	        return false;
	    }	
	}
	
</script>
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