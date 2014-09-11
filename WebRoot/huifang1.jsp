<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>回访一</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css'/>" rel="stylesheet" />
	<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
	<style type="text/css">
		.containner{width:1024px; height:768px; margin:0 auto; background-color: #E0EEFB;}
		.containner .huifanginfo1{padding-top:10px; width:600px; margin:0 auto;}
		.containner .huifanginfo2{padding-top:20px; width:600px; margin:0 auto;}
		.hftitle{background:#3F89EC; font-size:16px; text-indent:10px; font-weight:bold; height:28px; line-height:28px; color:#fff; }
		.ipt100_24{width:140px;height:24px; padding:2px 5px; border:1px solid #CCC; color:#666;}
		table{line-height:26px;}
		.huifanginfo2 .btnline{text-indent:45px;}
	</style>
	<script type="text/javascript">
		alert("a");
	</script>
</head>

<body>
<div class="containner">

	<div class="huifanginfo1">
		<h2 class="hftitle">任务信息</h2>
		<table cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td width="25%" align="right">任务号&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hf1Map.tid }</td>
				<td width="25%" align="right">序号&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hf1Map.ttid }</td>
			</tr>
		</table>
		<br>
		<h2 class="hftitle">回访原始信息</h2>
		<table cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td width="25%" align="right">手机号码&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hf1Map.telnum }</td>
				<td width="25%" align="right">姓名&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hf1Map.xm }</td>
			</tr>
			<tr>
				<td align="right">手机套餐&nbsp;</td>
				<td align="left">&nbsp;${hf1Map.sjtc }</td>
				<td align="right">通迅地址&nbsp;</td>
				<td align="left">&nbsp;${hf1Map.txdz }</td>
			</tr>
			<tr>
				<td align="right">二次电话&nbsp;</td>
				<td align="left">&nbsp;</td>
				<td align="right">证件号码&nbsp;</td>
				<td align="left">&nbsp;${hf1Map.zjhm }</td>
			</tr>
			<tr>
				<td align="right">预警生效时间&nbsp;</td>
				<td align="left">&nbsp;${hf1Map.yjsxsj }</td>
				<td align="right"></td>
				<td align="left">&nbsp;</td>
			</tr>
		</table>
	</div>
	<form name="form1" action="${pageContext.request.contextPath }/huiFangOne">
	<input type="hidden" name="method" value="saveHuiFangOne"/>
	<input type="hidden" name="huifangonetxt" value="${hf1Map.tid }"/>
	<input type="hidden" name="huifangonetxt" value="${hf1Map.ttid }"/>
	
	<div class="huifanginfo2">
		<h2 class="hftitle">回访录入信息</h2>
		<table cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td width="25%" align="right">回访情况&nbsp;</td>
				<td width="25%" align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
				<td width="25%" align="right">回访姓名&nbsp;</td>
				<td width="25%" align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
			</tr>
			<tr>
				<td align="right">性别&nbsp;</td>
				<td align="left">&nbsp;
					<input type="radio" id="t1" name="huifangonetxt" value="1" checked="checked"/><label for="t1">男</label>
					<input type="radio" id="t2" name="huifangonetxt" value="0"/><label for="t2">女</label>
				</td>
				<td align="right">异网本网调查&nbsp;</td>
				<td align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
			</tr>
			<tr>
				<td align="right">渠道满意度调查&nbsp;</td>
				<td align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
				<td align="right">手机品牌型号&nbsp;</td>
				<td align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
			</tr>
			<tr>
				<td align="right">机卡分离&nbsp;</td>
				<td align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
				<td align="right">购卡点&nbsp;</td>
				<td align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
			</tr>
			<tr>
				<td align="right">备注信息&nbsp;</td>
				<td align="left">&nbsp;<input type="text" id="" name="huifangonetxt" class="ipt100_24"/></td>
				<td align="right">&nbsp;</td>
				<td align="left">&nbsp;</td>
			</tr>
		</table>
	</div>
	<div class="huifanginfo2">
	<p class="btnline">
		<input type="submit" value="保存" class="btn4"/>
	</p>
	</div>
	</form>
</div>
</body>
</html>
