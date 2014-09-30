<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css?v=2'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<!-- 日期控件开始 -->
    <link type="text/css" href="<c:url value='/datePicker/skin/WdatePicker.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/datePicker/WdatePicker.js'/>"></script>
    <!-- 日期控件结束 -->
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	<script type="text/javascript" src="<c:url value='/js/changeTabColor.js'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">座席组资料维护&nbsp;
	<s:select list="#session.vts.ctsList" onchange="changeServer(this)" listKey="ctsname" listValue="ctsname" value="#session.vts.curCTS" cssStyle="height:22px; margin:1px;"></s:select>
	</h3>
	<div class="content_List615">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="6%">组编号</th>
                     <th width="10%">组名称</th>
                     <th width="10%">组号码</th>
                     <th width="10%">ACW(秒)</th>
                     <th width="10%">主叫号码</th>
                     <th width="12%">最大等待时长(秒)</th>
                     <th width="10%">最大排队数目</th>
                     <th width="8%">溢出去向</th>
                     <!--
					 <th width="10%">任务名称</th>
                     -->
                     <th width="10%">
                     	<input type="button" class="btn btn-primary" onclick="saveAcd('add','0','<s:property value="#session.vts.curCTS"/>','','','','','','','')" value="添加"/>
                     </th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="acd" value="#session.vts.list">
				<tr align="center">
					<td>${acd.grpid }</td>
					<td>${acd.grpname }</td>
					<td>${acd.telnum }</td>
					<td>${acd.acw }</td>
					<td>${acd.ani }</td>
					<td>${acd.maxwaittime }</td>
					<td>${acd.maxwaitnum }</td>
					<td>${acd.overflowto }</td>
					<!--
					<td>
						<s:select list="#session.vts.list2" onchange="popSetTask('%{grpid}','%{grpname}',this)" listKey="tid" listValue="tname" value="taskid" cssClass="seldefault_w85"></s:select>
					</td>
					-->
					<td>
						<a href="javascript:saveAcd('edit','${acd.grpid }','<s:property value="#session.vts.curCTS"/>','${acd.grpname }','${acd.telnum }','${acd.acw }','${acd.ani }','${acd.maxwaittime }','${acd.maxwaitnum }','${acd.overflowto }')">修改</a>&nbsp;&nbsp;
						<a href="javascript:deleteAcd('${acd.grpid }')">删除</a>
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
		<form id="form2" action="<c:url value='/acd-saveAcd.action'/>" method="post">
	    <input type="hidden" id="grpidx" name="acdtxt"/>
	    <div class="lab_ipt_item">
	    	<span class="lab150">组名称：</span>
	        <div class="ipt-box">
	        	<input type="text" id="grpnamex" name="acdtxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="whnum">
	    	<span class="lab150">组号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="telnumx" name="acdtxt" value="0" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="whnum">
	    	<span class="lab150">主叫号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="anix" name="acdtxt" value="" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab150">电话结束等待：</span>
	        <div class="ipt-box">
	        	<input type="text" id="acwx" name="acdtxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab150">最大排队等待时长(秒)：</span>
	        <div class="ipt-box">
	        	<input type="text" id="maxwaittimex" name="acdtxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab150">最大等待线数：</span>
	        <div class="ipt-box">
	        	<input type="text" id="maxwaitnumx" name="acdtxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab150">队列溢出去向：</span>
	        <div class="ipt-box">
	        	<input type="text" id="overflowtox" name="acdtxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk"></span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" onclick="saveAcdBtn()" class="btn4" value="确定"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<!-- delete acd form -->
	<form id="form3" action="<c:url value='acd-deleteAcd.action'/>" method="post">
		<input type="hidden" name="cts" value="${cts }"/>
		<input type="hidden" id="del_grpid" name="grpid"/>
	</form>
	
	<!-- set acd task form -->
	<form name="form4" action="<c:url value='acd-setTask.action'/>" method="post">
		<input type="hidden" name="cts" value="${cts }"/>
		<input type="hidden" id="st_grpid" name="grpid"/>
		<input type="hidden" id="st_taskid" name="taskid"/>
	</form>
	
</div>
<script type="text/javascript">
	$(function(){
		var nowPage = parent.document.getElementById("curAcdPage").value;
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
				parent.document.getElementById("curAcdPage").value = pages.current;
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
			saveAcdBtn();
			return false;   
		}   
		return true;
	}
</script>

<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<script type="text/javascript" src="<c:url value='/js/acd.js?v=6'/>"></script>
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cts.js?v=2'/>"></script>
</body>
</html>