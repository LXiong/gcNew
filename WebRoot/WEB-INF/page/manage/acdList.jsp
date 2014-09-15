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
	<h3 class="h3_title">业务组档案管理</h3>
	<form name="form1" action="<c:url value='/acdAction_home.action'/>" method="post">
	<div class="queryDiv">
	   	<ul class="queryWrap_ul_w600 left">
	   		<li><label>服务器别名：</label><input type="text" name="cts" class="ipt100" value="<s:property value="cts"/>"/></li>
	        <li><input type="submit" class="btn4" value="查&nbsp;&nbsp;询"/></li>
		</ul>
		<ul class="queryWrap_ul_w100 right">
	        <li><input type="button" class="btn4" onclick="saveAcd('add','','','','','','','','')" value="添加"/></li>
		</ul>
	</div>
	</form>
	<div class="content_List568">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="10%">组编号</th>
                     <th width="10%">组名称</th>
                     <th width="10%">组号码</th>
                     <th width="10%">ACW</th>
                     <th width="10%">最大等待时长(秒)</th>
                     <th width="10%">最大排队数目</th>
                     <th width="10%">溢出去向</th>
                     <th width="10%">操作</th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="acd" value="#session.vts.list">
				<tr align="center">
					<td>${acd.grpid }</td>
					<td>${acd.grpname }</td>
					<td>${acd.telnum }</td>
					<td>${acd.acw }</td>
					<td>${acd.maxwaittime }</td>
					<td>${acd.maxwaitnum }</td>
					<td>${acd.overflowto }</td>
					<td>
						<a href="javascript:saveAcd('edit','${acd.grpid }','${cts }','${acd.grpname }','${acd.telnum }','${acd.acw }','${acd.maxwaittime }','${acd.maxwaitnum }','${acd.overflowto }')">修改</a>&nbsp;&nbsp;
						<a href="javascript:deleteAcdPre('${acd.grpid }')">删除</a>
						<input type="button" class="hide" onclick="deleteAcd()" value="删除"/>
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
		<form name="form2" action="<c:url value='/acdAction_saveAcd.action'/>" method="post">
	    <div class="lab_ipt_item">
	    	<span class="lab150">服务器别名：</span>
	        <div class="ipt-box">
	        	<input type="text" id="ctsx" name="acdtxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
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
	            <span class="asterisk"></span>
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
	
	<!-- delete acd form -->
	<form name="form3" action="<c:url value='acdAction_deleteAcd.action'/>" method="post">
		<input type="hidden" name="cts" value="${cts }"/>
		<input type="hidden" id="del_grpid" name="grpid"/>
	</form>
	
</div>
<script type="text/javascript">
	function saveAcd(t,grpid,cts,grpname,telnum,acw,maxwaittime,maxwaitnum,overflowto)
	{
		var tit;
		if(t=="add")
		{
			tit="添加业务组";
		}
		else
		{
			tit="修改业务组信息";
		}
		//
		$("#grpidx").val(grpid);
		$("#ctsx").val(cts);
		$("#grpnamex").val(grpname);
		$("#telnumx").val(telnum);
		$("#acwx").val(acw);
		$("#maxwaittimex").val(maxwaittime);
		$("#maxwaitnumx").val(maxwaitnum);
		$("#overflowtox").val(overflowto);
		
		
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

	function deleteAcdPre(grpid)
	{
		$("#del_grpid").val(grpid)
		layer.confirm("确定要删除吗？",function(){
			deleteAcd();
		});
	}
	function deleteAcd()
	{
		document.form3.submit();
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