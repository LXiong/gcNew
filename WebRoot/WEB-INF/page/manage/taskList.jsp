<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>扶贫观察点管理系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css?v=3'/>" rel="stylesheet" />
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
 	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	<script type="text/javascript" src="<c:url value='/js/changeTabColor.js'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">外呼任务管理</h3>
	<div class="content_List615">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
				<tr>
					<th width="4%">编号</th>
                    <th width="15%">任务名称</th>
                    <th width="4%">类型</th>
                    <th width="4%">座席组</th>
                    <th width="6%">号码总数</th>
                    <th width="4%">新建数</th>
                    <th width="4%">执行中</th>
                    <th width="6%">执行完成</th>
                    <th width="4%">接通数</th>
                    <th width="4%">接通率</th>
                    <th width="4%">完成率</th>
                    <th width="18%">
                    	<input type="button" class="btn btn-primary" onclick="saveTask('add','0','','0','')" value="添加"/>
					</th>
				</tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="task" value="#session.vts.list">
				<tr align="center">
					<td>${tid }</td>
					<td align="left" title="${tname }">&nbsp;
						<s:property value="tname.length()>13?tname.substring(0,12)+'...':tname"/>	
					</td>
					<td>
						<c:if test="${kind eq 0 }">标准</c:if>
						<c:if test="${kind eq 1 }">回访1</c:if>
						<c:if test="${kind eq 2 }">回访2</c:if>
						<c:if test="${kind eq 3 }">回访3</c:if>
					</td>
					<td>${acdnum }</td>
					<td>${trn }</td>
					<td>${nrn }</td>
					<td>${drn }</td>
					<td>${frn }</td>
					<td>${ans }</td>
					<td>${jtr }</td>
					<td>${frr }</td>
					<td>
						<a href="javascript:setAcd('${tid }')">指派座席组</a>&nbsp;&nbsp;
						<a href="javascript:telManage('${tid }','${kind }','${tname }')">号码管理</a>&nbsp;&nbsp;
						<a href="javascript:saveTask('edit','${tid }','${tname }','${kind }','${taskinfo }')">修改</a>&nbsp;&nbsp;
						<a href="javascript:deleteTaskPre('${tid }','${tname }','${trn }')">删除</a>
						<input type="button" class="hide" onclick="deleteTask('${tid }','${tname }')" value="删除"/>
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
		<form id="form1" action="<c:url value='/task-saveTask.action'/>" method="post">
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
	<form id="form2" name="form2" action="<c:url value='/task-deleteTask.action'/>" method="post">
		<input type="hidden" id="del_tid" name="tid"/>
		<input type="hidden" id="del_tname" name="tname"/>
	</form>
	
	<!--POP LAYER START-->
	<div id="popSetAcdDiv" style="display:none; border:1px solid #50A4C0; margin:10px; border-radius:4px;"> 
		<form id="form3" action="<c:url value='/task-setAcd.action'/>" method="post">
	    <input type="hidden" id="setacd_tidx" name="tid"/>
	   	<div id="acdHTML"></div>
	    <div style="height:10px;"></div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="saveSetAcdBtn('<s:property value="#session.vts.curCTS"/>')"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<!-- delete task -->
	<form name="form4" action="<c:url value='/task-telmanage.action'/>" method="post">
		<input type="hidden" id="p_tid" name="tid"/>
		<input type="hidden" id="p_kind" name="kind"/>
		<input type="hidden" id="p_tname" name="tname"/>
	</form>
	
</div>

<script type="text/javascript">
//split page task
$(function(){
	var nowPage = parent.document.getElementById("curTaskPage").value;
	var pflag = "${pageflag }";
	if(!pflag)
	{
		nowPage = 1;
	}
	$("div.holder").jPages({
		containerID : "movies",
        first : "首页",
        previous : "上一页",
        next : "下一页",
        last : "尾页",
        startPage : nowPage,
        perPage : 28,
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
//bind click event
document.onkeydown = function(e) {   
	var theEvent = e || window.event;   
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode; 
	if (code == 13) {   
		saveTaskBtn();
		return false;   
	}   
	return true;
}
</script>

<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/json_ie.js?v=1'/>"></script>
<script type="text/javascript" src="<c:url value='/js/task.js?v=40'/>"></script>
</body>
</html>