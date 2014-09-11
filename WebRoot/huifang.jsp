<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>回访</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
	<form name="form2" action="${pageContext.request.contextPath }/huiFangTwo">
		<input type="hidden" name="method" value="query"/>
		<input type="hidden" id="" name="tid"/>
		<input type="hidden" id="" name="ttid"/>
	</form>
	<form name="form3" action="${pageContext.request.contextPath }/huiFangThree">
		<input type="hidden" name="method" value="query"/>
		<input type="hidden" id="" name="tid"/>
		<input type="hidden" id="" name="ttid"/>
	</form>
	<script type="text/javascript">
		function C_JS_DETECTCALL(param)
		{

			alert(param);
			document.form1.submit();
		}
	</script>
</body>
</html>
