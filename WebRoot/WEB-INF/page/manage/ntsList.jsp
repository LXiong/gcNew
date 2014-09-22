<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css'/>" rel="stylesheet" />
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
 	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
 	<!-- jPage 分页插件 start -->
 	<link type="text/css" href="<c:url value='/jPage/jPages.css?v=1'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/jPage/jPages.js'/>"></script>
 	<!-- jPage 分页插件  end -->
 	<script type="text/javascript" src="<c:url value='/js/changeTabColor.js'/>"></script>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">NTS服务器维护</h3>
	<div class="content_List615">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="10%">账号</th>
                     <th width="10%">密码</th>
                     <th width="20%">限制IP</th>
                     <th width="10%">接收群发消息</th>
                     <th width="20%">备注</th>
                     <th width="20%">
                     	<input type="button" class="btn btn-primary" onclick="saveNts('add','','','','','')" value="添加"/>
                     </th>
                 </tr>
             </thead>
             <tbody id="movies">
             	<s:iterator id="nts" value="#session.vts.list">
				<tr align="center">
					<td>${account }</td>
					<td>${pwd }</td>
					<td>${ip_allow }</td>
					<td>
						<c:if test="${anyone eq 1}">√</c:if>
					</td>
					<td>${content }</td>
					<td>
						<a href="javascript:saveNts('edit','${account }','${pwd }','${ip_allow }','${anyone }','${content }')">修改</a>&nbsp;&nbsp;
						<a href="javascript:deleteNtsPre('${account }')">删除</a>
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
		<form id="form2" action="<c:url value='/ntsAction_saveNts.action'/>" method="post">
	    <!-- add 0, update 1 -->
	    <input type="hidden" value="0" id="addup" name="ntstxt"/>
	    <div class="lab_ipt_item">
	    	<span class="lab120">账号：</span>
	        <div class="ipt-box">
	        	<input type="text" id="accountx" name="ntstxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">密码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="pwdx" name="ntstxt" class="ipt_text_w150 inputDefault" />
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">限制IP：</span>
	        <div class="ipt-box">
	        	<input type="text" id="ipallowx" name="ntstxt" class="ipt_text_w150 inputDefault" />
	            <span class=""></span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">是否接收群发消息：</span>
	        <div class="ipt-box">
	        	<input type="checkbox" id="anyonemsgx" onclick="checkAnyonemsg(this)" style="margin-top:6px;"/>
	        	<input type="hidden" id="isanyonemsgx" name="ntstxt" value="0"/>
	            <span class=""></span>
	        </div>
	    </div>
	    <div class="h132">
	    	<span class="lab120">备注信息：</span>
	        <div class="h132 ipt-box">
	        	<textarea id="contentx" name="ntstxt" class="ipt_textarea_w300 inputDefault" style="font-size:12px;"></textarea>
	            <span></span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="submitSaveNtsBtn()"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<form name="form3" action="<c:url value='/ntsAction_deleteNts.action'/>" method="post">
		<input type="hidden" id="del_account" name="account"/>
	</form>
	
</div>
<script type="text/javascript">
//jPage分页 
$(function(){
	var nowPage = parent.document.getElementById("curBlackPage").value;
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
			parent.document.getElementById("curBlackPage").value = pages.current;
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
<script type="text/javascript" src="<c:url value='/js/nts.js?v=4'/>"></script>
</body>
</html>