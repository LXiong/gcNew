<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css?v=3'/>" rel="stylesheet" />
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
	<h3 class="h3_title">话务员资料维护</h3>
	<div class="content_List615">
		<table cellpadding="0" cellspacing="0" class="tab_border">
			<thead class="tab_head">
                 <tr>
                     <th width="15%">登录账号</th>
                     <th width="15%">话务员号码</th>
                     <th width="15%">管理员</th>
                     <th width="15%">姓名</th>
                     <th width="15%">电子邮箱</th>
                     <th width="20%">
                     	<input type="button" class="btn btn-primary" onclick="saveAgent('add','0','','0','','','','')" value="添加"/>
                     </th>
                 </tr>
             </thead>
             <tbody id="movies">
               	<s:iterator id="agt" value="#session.vts.list">
				<tr align="center">
					<td>${account }</td>
					<td>${telnum }</td>
					<td>
						<c:if test="${ismaster eq 1}">√</c:if>
					</td>
					<td>${agtname }</td>
					<td>${email }</td>
					<td>
						<!-- a to button 解决ajax submit提交问题 -->
						<a href="javascript:initAgentpwd('${agtid }')">密码初始化</a>&nbsp;&nbsp;
						<a href="javascript:saveAgent('edit','${agtid }','${account }','${ismaster }','${telnum }','${agtname }','${email }')">修改</a>&nbsp;&nbsp;
						<a href="javascript:deleteAgent('${agtid }')">删除</a>
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
		<form id="form2" action="<c:url value='/agent-saveAgent.action'/>" method="post">
	    <input type="hidden" id="agtidx" name="agttxt"/>
	    <div class="lab_ipt_item">
	    	<span class="lab120">登录账号：</span>
	        <div class="ipt-box">
	        	<input type="text" id="accountx" name="agttxt" class="ipt_text_w150 inputDefault" maxlength="15"/>
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">是否为管理员：</span>
	        <div class="ipt-box">
	        	<input type="checkbox" id="ismasterchk" onclick="checkMaster(this)" style="margin-top:6px;"/>
	        	<input type="hidden" id="ismasterx" name="agttxt" value="0"/>
	            <span class=""></span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">话务员号码：</span>
	        <div class="ipt-box">
	        	<input type="text" id="telnumx" name="agttxt" class="ipt_text_w150 inputDefault"  maxlength="15"/>
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item" id="whnum">
	    	<span class="lab120">姓名：</span>
	        <div class="ipt-box">
	        	<input type="text" id="agtnamex" name="agttxt" value="0" class="ipt_text_w150 inputDefault"  maxlength="10"/>
	            <span class="asterisk">*</span>
	        </div>
	    </div>
	    <div class="lab_ipt_item">
	    	<span class="lab120">电子邮箱：</span>
	        <div class="ipt-box">
	        	<input type="text" id="emailx" name="agttxt" class="ipt_text_w150 inputDefault"  maxlength="20"/>
	            <span class="asterisk"></span>
	        </div>
	    </div>
		<div class="lab_ipt_item">
			<span class="lab120"></span>
			<div class="ipt-box"><input type="button" class="btn4" value="确定" onclick="saveAgentBtn()"/></div>
			<div class="ipt-box" style="margin-left:20px;"><input type="button" class="btn4" value="取消" onclick="layer.closeAll()"/></div>
		</div>	
		</form>
	</div>
	<!--POP LAYER END-->
	
	<!-- delete agent form -->
	<form id="form3" action="<c:url value='/agent-deleteAgent.action'/>" method="post">
		<input type="hidden" id="del_agtid" name="agtid"/>
	</form>
	
</div>
<script type="text/javascript">
//split agent page
$(function(){
	var nowPage = parent.document.getElementById("curAgentPage").value;
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
			parent.document.getElementById("curAgentPage").value = pages.current;
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
		saveAgentBtn();
		return false;   
	}   
	return true;
}
</script>
<!-- layer 弹出插件 start -->
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- layer 弹出插件 end -->
<script type="text/javascript" src="<c:url value='/js/agent.js?v=14'/>"></script>
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
</body>
</html>