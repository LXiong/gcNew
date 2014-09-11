<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>扶贫观察点管理系统</title>
		<style>
		.box_wrap {
		    width: 866px;
		    height: 672px;
		    margin: 0px auto;
		    background: url('${pageContext.request.contextPath}/images/err_back.png') repeat-x scroll 0% 0% transparent;
		    position: relative;
		}
		.top_box0311 {
		    height:195px;
		    overflow:hidden;
		    padding-top:105px;
		    padding-left:180px;
		    position:relative;
		    font-size:18px;
		    font-weight:bold;
		    color:#5DA4EC;
		    line-height:24px;
		}
		.pt-360 {
		    padding-top: 35px;
		}
		.refurbish_btn {
		    text-align: center;
		    padding-top: 20px;
		}
		.again_login {
			background:#9BD678;
		    width: 151px;
		    height: 56px;
		    display: block;
		    margin: 0px auto;
		    outline: medium none;
		    cursor: pointer;
		    text-align:center;
		    line-height:56px;
		    font-size:22px;
		}
		a{
		    text-decoration: none;
		    color: #fff;
		    font-family: "Microsoft YaHei","宋体",Arial,sans-serif;
		    
		}
		*{
		    margin: 0px;
		    padding: 0px;
		}
		</style>
	</head>
	<body>
		<div class="box_wrap">
			<div class="top_box0311">
				<div style="float:left; width:147px; height:188px; background: url(${pageContext.request.contextPath}/images/timeout.png)"></div>
				<div style="float:left; width:400px; height:188px; padding-top:50px; padding-left:10px;">
				<p>您的操作已超时，请重新登录</p>
				</div>
			</div>
			<div class="pt-360">
				<div>
					<a href="${pageContext.request.contextPath }/index.action" target="_top" class="again_login">
						重新登录
					</a>
				</div>				
			</div>
		</div>
	</body>
</html>
