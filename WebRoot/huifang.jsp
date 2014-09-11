<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
		This is Huifang JSP page.
		<input type="button" value="DetectCall" onclick="C_JS_DETECTCALL('ani=808;dnis=10086;param=a,1,1;')"/>
		<br>
		
		<form name="form1" action="${pageContext.request.contextPath }/huiFangOne">
			<input type="hidden" name="method" value="query"/>
			<input type="hidden" id="" name="tid"/>
			<input type="hidden" id="" name="ttid"/>
		</form>
		
	<script type="text/javascript">
		function C_JS_DETECTCALL(param)
		{
			
			document.form1.submit();
		}
	</script>
	</body>
	
</html>
