<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>扶贫观察点管理系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css?v=1'/>" rel="stylesheet" />
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
 	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">任务及号码管理</h3>
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
			<li></li>
		</ul>
		<ul class="queryWrap_ul_w100 right">
	        <li><input type="button" class="btn4" onclick="saveTask('add','0','','0','0','')" value="添加"/></li>
		</ul>
	</div>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="6%">任务编号</th>
                     <th width="10%">任务名称</th>
                     <th width="6%">任务类型</th>
                     <th width="6%">任务状态</th>
                     <th width="8%">业务组(个)</th>
                     <th width="6%">号码总数</th>
                     <th width="6%">新建数</th>
                     <th width="6%">执行中</th>
                     <th width="6%">执行完成</th>
                     <th width="8%">呼叫接通数</th>
                     <th width="20%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="task" value="#session.vts.list">
				<tr align="center">
					<td>${task.tid }</td>
					<td align="left">&nbsp;${task.tname }</td>
					<td>
						<c:if test="${task.kind eq 0 }">标准</c:if>
						<c:if test="${task.kind eq 1 }">回访1</c:if>
						<c:if test="${task.kind eq 2 }">回访2</c:if>
						<c:if test="${task.kind eq 3 }">回访3</c:if>
					</td>
					<td>
						<c:if test="${task.state eq 0 }">新建</c:if>
						<c:if test="${task.state eq 1 }">激活</c:if>
						<c:if test="${task.state eq 9 }">停止</c:if>
						<c:if test="${task.state eq 10 }">呼叫完成</c:if>
					</td>
					<td>${task.acdnum }</td>
					<td>${task.trn }</td>
					<td>${task.nrn }</td>
					<td>${task.drn }</td>
					<td>${task.frn }</td>
					<td>${task.ans }</td>
					<td>
						<a href="javascript:setAcd('${task.tid }')">指派业务组</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/taskAction_telmanage.action?tid=${task.tid}&tname=${task.tname}&kind=${task.kind}">号码管理</a>&nbsp;&nbsp;
						<a href="javascript:saveTask('edit','${task.tid }','${task.tname }','${task.kind }','${task.state }','${task.taskinfo }')">修改</a>&nbsp;&nbsp;
						<a href="javascript:deleteTaskPre('${task.tid }','${task.tname }','${task.trn }')">删除</a>
						<input type="button" class="hide" onclick="deleteTask('${task.tid }','${task.tname }')" value="删除"/>
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
		<form id="form1" action="<c:url value='/taskAction_saveTask.action'/>" method="post">
	    <input type="hidden" id="tidx" name="tid"/>
	    <div class="lab_ipt_item">
	    	<span class="lab120">任务名称：</span>
	        <div class="ipt-box">
	        	<input type="text" id="tnamex" name="tname" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="ywtype">
	    	<span class="lab120">任务类型：</span>
	        <div class="ipt-box">
	        	<s:select id="kindx" name="kind" list="#application.vta.GetList('taskkind')" listKey="id" listValue="str" cssStyle="height:28px;"></s:select>
	            <span class="asterisk"></span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="tstate_add_hide">
	    	<span class="lab120">任务状态：</span>
	        <div class="ipt-box">
	        	<s:select id="statex" name="state" list="#application.vta.GetList('taskstate')" listKey="id" listValue="str" cssStyle="height:28px;"></s:select>
	            <span class="asterisk"></span>
	        </div>
	    </div>
	    <div class="h132">
	    	<span class="lab120">任务信息：</span>
	        <div class="h132 ipt-box">
	        	<textarea id="taskinfox" name="taskinfo" class="ipt_textarea_w300 inputDefault" style="font-size:12px;"></textarea>
	            <span></span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="saveTaskBtn()"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<!-- delete task -->
	<form id="form2" name="form2" action="<c:url value='/taskAction_deleteTask.action'/>" method="post">
		<input type="hidden" id="del_tid" name="tid"/>
		<input type="hidden" id="del_tname" name="tname"/>
	</form>
	
	<!--POP LAYER START-->
	<div id="popSetAcdDiv" style="display:none; border:1px solid #50A4C0; margin:10px 0 0 30px; border-radius:4px;"> 
		<form name="form3" action="<c:url value='/taskAction_setAcd.action'/>" method="post">
	    <input type="hidden" id="setacd_tidx" name="tid"/>
	    
	   ${html }
	    <div style="height:10px;"></div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="saveSetAcdBtn()"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
</div>

<script type="text/javascript">
//split page task
$(function(){
	var nowPage = parent.document.getElementById("curTaskPage").value;
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
			parent.document.getElementById("curTaskPage").value = pages.current;
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
<script type="text/javascript" src="<c:url value='/js/task.js?v=8'/>"></script>
</body>
</html>