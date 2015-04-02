<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<c:url value='/style/common_cn.css'/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/style/layout.css?v=2'/>" />
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<style>
		table{border-top:1px solid #000; border-left:1px solid #000;}
		table td{border-right:1px solid #000; border-bottom:1px solid #000; cursor:pointer;}
		.quickdelete {
width: 32px;
height: 32px;
background: url(https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/global/img/quickdelete_9c14b01a.png) no-repeat;
background-position: 10px 10px;
position: absolute;
display: block;
}
	</style>
</head>
<body style="background:#E0EEFB;">
<div id="dbCon">
	<h3 class="h3_title" style="margin-top:60px;">拔打电话</h3>
	<div class="sys_call_info">
		<div style="height:20px;"></div>
		<div style="text-indent:50px; position:relative;">
			<label>电话号码：</label><input type="text" id="dnisTxt" style="border:1px solid #FF6600; width:198px; height:40px; line-height:40px; font-size:20px; color:#FF6633; text-indent:2px" maxlength="12"/>
			<a href="javascript:;" id="quickdelete" title="清空" class="quickdelete" style="top:4px; right:106px; display:none;"></a>
		</div>
		<div style="width:200px; height:200px; margin:0 auto;">
			<table id="callTab" width="200px" height="200px" style="text-align:center">
			    <tr>
			      <td>1</td>
			      <td>2</td>
			      <td>3</td>
			    </tr>
			    <tr>
			      <td>4</td>
			      <td>5</td>
			      <td>6</td>
			    </tr>
			    <tr>
			      <td>7</td>
			      <td>8</td>
			      <td>9</td>
			    </tr>
			    <tr>
			      <td>*</td>
			      <td>0</td>
			      <td>#</td>
			    </tr>
			</table>
			<div style="height:20px;"></div>
			<input type="button" onclick="onDial()" class="btn4" value="呼叫"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="onHook()" class="btn4" value="挂断"/>
    	</div>
    </div>
</div>	
<script type="text/javascript">
	$("td").bind("mouseover",function(){ 
	    $(this).css("background-color","#15a4fa"); 
	}); 
	$("td").bind("mouseout",function(){ 
	    $(this).css("background-color","#E0EEFB"); 
	}); 
	//
	$("#callTab tr td").click(function(){
		$("#dnisTxt").focus();
	    var dnis = $("#dnisTxt").val();  
	    var n = this.innerHTML;
	    if (dnis.length < 12) {
	      	$("#dnisTxt").val(dnis+n); 
	      	dnisBlur(); 
		}
	});
	//
	$("#dnisTxt").bind("focus", dnisBlur);
	$("#dnisTxt").bind("keyup", dnisKeyup);

	function dnisBlur()
	{
		var tel = $("#dnisTxt").val();
		if(tel.length>0)
		{
			$("#quickdelete")[0].style.display="block";
		}
	}

	function dnisKeyup()
	{
		var tel = $("#dnisTxt").val();
		if(tel.length==0)
		{
			$("#quickdelete")[0].style.display="none";
		}
		else
		{
			$("#quickdelete")[0].style.display="block";
		}
	}
	//
	$("#quickdelete").bind("click",function(){
		$("#dnisTxt").val('');
		document.getElementById("dnisTxt").focus();
		$("#quickdelete")[0].style.display="none";
	});
	
	document.getElementById("dnisTxt").focus();
	//
	var ocx = $("#OCXPlugin",window.parent.document)[0];
	//呼叫
  	function onDial()
  	{
		var tel = $("#dnisTxt").val();
		var regT = /^([0-9])+$/g;
		var regP = /0?(13|14|15|18)[0-9]{9}/;
		
		if(!tel)
		{
			alert("请输入要拔打的号码！");
			return false;
		}
		else if(!regT.exec(tel) && !regP.exec(tel))
		{
			alert("请输入合理的电话号码");
			return false;
		}
		else
		{
			var callid = ocx.GetCallID();
			ocx.doDialEx(tel,"b,,"+callid);
		}
  	}
  	//挂断
  	function onHook()
  	{
		ocx.doOnHook();
  	}
</script>
</body>
</html>