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
	<h3 class="h3_title">座席分机管理</h3>
   	<form action="<c:url value='/subTelAction_home.action'/>" method="post">
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
			<li><label>开始日期：</label><input type="text" id="sdt" name="sdt" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'edt\')||\'2050-01-01\'}',skin:'whyGreen'})" value="<s:property value="sdt"/>"/></li>
	        <li><label>结束日期：</label><input type="text" id="edt" name="edt" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'sdt\')}',maxDate:'%y-%M-%d',skin:'whyGreen'})" value="<s:property value="edt"/>"/></li>
	        <li><input type="submit" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
		<ul class="queryWrap_ul_w100 right">
	        <li><input type="button" class="btn4" onclick="saveSubTel('add','','','','','','','')" value="添加"/></li>
		</ul>
	</div>
    </form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="8%">telid</th>
                     <th width="8%">telnum</th>
                     <th width="8%">fn_m</th>
                     <th width="8%">fn_n</th>
                     <th width="8%">fn_d</th>
                     <th width="8%">fn_t</th>
                     <th width="8%">fn_i</th>
                     <th width="8%">def_agent</th>
                     <th width="8%">def_acd</th>
                     <th width="8%">def_cmd</th>
                     <th width="8%">clientname</th>
                     <th width="8%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="sub" value="#session.vts.list">
				<tr align="center">
					<td>${sub.telid }</td>
					<td>${sub.telnum }</td>
					<td>${sub.fn_m }</td>
					<td>${sub.fn_n }</td>
					<td>${sub.fn_d }</td>
					<td>${sub.fn_t }</td>
					<td>${sub.fn_i }</td>
					<td>${sub.def_agent }</td>
					<td>${sub.def_acd }</td>
					<td>${sub.def_cmd }</td>
					<td>${sub.clientname }</td>
					<td>
						<a href="javascript:saveSubTel('edit','${sub.cts }','${sub.telid }','${sub.telnum }','${sub.def_agent }','${sub.def_acd }','${sub.def_cmd }','${sub.clientname }')">修改</a>&nbsp;&nbsp;
						<a href="<c:url value='subTelAction_deleteSubTel.action?cts=${sub.cts }&telid=${sub.telid }'/>">删除</a>
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
		<form name="form1" action="<c:url value='/subTelAction_saveSubTel.action'/>" method="post">
	    <div class="lab_ipt_item">
	    	<span class="lab150">语音服务器别名：</span>
	        <div class="ipt-box">
	        	<input type="text" id="ctsx" name="subteltxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <input type="hidden" id="telidx" name="subteltxt"/>
	    <div class="lab_ipt_item">
	    	<span class="lab150">分机号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="telnumx" name="subteltxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab150">默认话务员号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="defagentx" name="subteltxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="whnum">
	    	<span class="lab150">默认业务组号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="defacdx" name="subteltxt" value="0" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk"></span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab150">默认命令：</span>
	        <div class="ipt-box">
	        	<input type="text" id="defcmdx" name="subteltxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab150">绑定的客户端帐号：</span>
	        <div class="ipt-box">
	        	<input type="text" id="clientnamex" name="subteltxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="submit" class="btn4" value="确定"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
</div>
<script type="text/javascript">
	function saveSubTel(t,cts,telid,telnum,defagent,defacd,defcmd,clientname)
	{
		var tit;
		if(t=="add")
		{
			tit="添加座席分机";
		}
		else
		{
			tit="修改座席分机";
		}
		//
		$("#ctsx").val(cts);
		$("#telidx").val(telid);
		$("#telnumx").val(telnum);
		$("#defagentx").val(defagent);
		$("#defacdx").val(defacd);
		$("#defcmdx").val(defcmd);
		$("#clientnamex").val(clientname);
		
		$.layer({
			type: 1,
	        title: tit,
	        offset: [($(window).height() - 290)/2+'px', ''],
	        border : [5, 0.5, '#666'],
	        area: ['450px','340px'],
	        shadeClose: false,
			bgcolor: '#fff',
			page:{dom:'#popDiv'}
		});
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