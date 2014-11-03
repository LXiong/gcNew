<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>电话自动外呼系统</title>
	<link type="text/css" href="<c:url value='/style/common_cn.css'/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value='/style/layout.css?v=1'/>" rel="stylesheet" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="cache-control" content="no-cache"/>
 	<meta http-equiv="expires" content="0"/>
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
    <style type="text/css">
    	.huifanginfo1{padding-top:10px; width:600px; margin:0 auto;}
		.huifanginfo2{padding-top:20px; width:600px; margin:0 auto;}
		.hftitle1{background:#ddd; color:#666666; font-size:14px; text-indent:10px; height:28px; line-height:28px; border:1px solid #ddd;}
		.hftitle2{background:#FFB848; color:#fff; font-size:16px; text-indent:10px; height:28px; line-height:28px;}
		.hftitle3{background:#35AA47; color:#fff; font-size:16px; text-indent:10px; font-weight:bold; height:28px; line-height:28px;}
		.ipt100_24{width:100px;height:20px; padding:2px 5px; border:1px solid #CCC; color:#000;}
		table{line-height:26px;}
		.huifanginfo2 .btnline{padding-left:185px;}
		
		.hftab1{border-top:0; border-color:#ddd;}
		.hftab1 td{border:1; border-color:#DDDDDD}
		
		.hftab2{border-top:0; border-color:#FCCB7E;}
		.hftab2 td{border:1; border-color:#DDDDDD}
		
		.hftab3{border-top:0; border-color:#77E588;}
		.hftab3 td{border:1; border-color:#DDDDDD}
		
    </style>
</head>
<body>
<div id="contentWrap">
	<h3 class="h3_title">回访一</h3>
	<div class="huifanginfo1">
		<h2 class="hftitle1">任务信息</h2>
		<table cellpadding="0" cellspacing="0" class="hftab1">
			<tr>
				<td width="25%" align="right">任务号&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hfMap.tid }</td>
				<td width="25%" align="right">序号&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hfMap.ttid }</td>
			</tr>
		</table>
		<br/>
		<h2 class="hftitle2">回访原始信息</h2>
		<table cellpadding="0" cellspacing="0" border="1" class="hftab2">
			<tr>
				<td width="25%" align="right">手机号码&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hfMap.telnum }</td>
				<td width="25%" align="right">姓名&nbsp;</td>
				<td width="25%" align="left">&nbsp;${hfMap.xm }</td>
			</tr>
			<tr>
				<td align="right">手机套餐&nbsp;</td>
				<td align="left">&nbsp;${hfMap.sjtc }</td>
				<td align="right">通迅地址&nbsp;</td>
				<td align="left">&nbsp;${hfMap.txdz }</td>
			</tr>
			<tr>
				<td align="right">二次电话&nbsp;</td>
				<td align="left">&nbsp;</td>
				<td align="right">证件号码&nbsp;</td>
				<td align="left">&nbsp;${hfMap.zjhm }</td>
			</tr>
			<tr>
				<td align="right">预警生效时间&nbsp;</td>
				<td align="left">&nbsp;${hfMap.yjsxsj }</td>
				<td align="right"></td>
				<td align="left">&nbsp;</td>
			</tr>
		</table>
	</div>
	
	<form id="form1" action="${pageContext.request.contextPath }/huifang-save.action" method="post">
	<input type="hidden" name="flag" value="${flag }"/>
	<input type="hidden" name="tid" value="${hfMap.tid }"/>
	<input type="hidden" name="ttid" value="${hfMap.ttid }"/>
	
	<div class="huifanginfo2">
		<h2 class="hftitle3">回访录入信息</h2>
		<table cellpadding="0" cellspacing="0" border="1" class="hftab3">
			<tr>
				<td width="25%" align="right">回访情况&nbsp;</td>
				<td width="25%" align="left">&nbsp;
					<s:select name="hfqk" list="#application.vta.GetList('hfqk')" listKey="id" listValue="str" value="hfqk"></s:select>
				</td>
				<td width="25%" align="right">回访姓名&nbsp;</td>
				<td width="25%" align="left">&nbsp;<input type="text" id="hfnamex" name="hfname" value="${hfname }" class="ipt100_24"/></td>
			</tr>
			<tr>
				<td align="right">性别&nbsp;</td>
				<td align="left">&nbsp;
					<s:radio name="sex" list="#application.vta.GetList('sex')" listKey="id" listValue="str" value="sex"/>
				</td>
				<td align="right">异网本网调查&nbsp;</td>
				<td align="left">&nbsp;
					<input name="ywbwdc" value="0" type="checkbox" <c:if test="${ywbwdc eq 1 }">checked="checked"</c:if> onclick="changeCHKVal(this)"/>
				</td>
			</tr>
			<tr>
				<td align="right">渠道满意度调查&nbsp;</td>
				<td align="left">&nbsp;
					<s:radio name="manyid" list="#application.vta.GetList('manyi')" listKey="id" listValue="str" value="manyid"/>
				</td>
				<td align="right">手机品牌型号&nbsp;</td>
				<td align="left">&nbsp;<input type="text" name="phonemodel" value="${phonemodel }" class="ipt100_24"/></td>
			</tr>
			<tr>
				<td align="right">机卡分离&nbsp;</td>
				<td align="left">&nbsp;
					<input name="jkfl" value="0" type="checkbox" <c:if test="${jkfl eq 1 }">checked="checked"</c:if> onclick="changeCHKVal(this)"/>
				</td>
				<td align="right">购卡点&nbsp;</td>
				<td align="left">&nbsp;<input type="text" name="gkd" value="${gkd }" class="ipt100_24"/></td>
			</tr>
			<tr>
				<td align="right">备注信息&nbsp;</td>
				<td align="left">&nbsp;<input type="text" name="remark" value="${remark }" class="ipt100_24"/></td>
				<td align="right">&nbsp;</td>
				<td align="left">&nbsp;</td>
			</tr>
		</table>
	</div>
	<div class="huifanginfo2">
	<p class="btnline">
		<input type="button" onclick="saveHuifangBtn('${flag}','1')" value="保存" class="btn4"/>
		<input style="margin-left:110px;" type="button" onclick="location.href='agentanaly-answer.action'" value="返回" class="btn4"/>
	</p>
	</div>
	</form>
</div>
<script type="text/javascript" src="<c:url value='/layer/layer.min.js'/>"></script>
<!-- ajax file upload -->
<script type="text/javascript" src="<c:url value='/js/jquery.form-3.46.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/huifang.js?v=6'/>"></script>
</body>
</html>