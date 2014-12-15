$(function(){
	$(".asterisk")[0].innerHTML="";
	$(".asterisk")[1].innerHTML="";
	//
	$("#ipx").bind("blur",checkIp);
	$("#portx").bind("blur",checkPort);
	$("#subtelx").bind("blur",checkSubtel);
});


function checkIp()
{
	var ip = $("#ipx").val();
	var reg = /((?:(?:25[0-5]|2[0-4]\d|[01]?\d?\d)\.){3}(?:25[0-5]|2[0-4]\d|[01]?\d?\d))/;
	if(!ip)
	{
		$(".asterisk")[0].innerHTML="服务器IP地址不能为空";
		return false;
	}
	else if(!reg.exec(ip))
	{
		$(".asterisk")[0].innerHTML="请输入合理的IP地址";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}

function checkPort()
{
	var port = $("#portx").val();
	var reg = /^[0-9]*[0-9][0-9]*$/;
	if(!port)
	{
		$(".asterisk")[0].innerHTML="端口不能为空";
		return false;
	}
	else if(!reg.exec(port))
	{
		$(".asterisk")[0].innerHTML="请输入合理的数字";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}

function checkSubtel()
{
	var subtel = $("#subtelx").val();
	var reg = /^[0-9]*[0-9][0-9]*$/;
	if(!subtel)
	{
		$(".asterisk")[1].innerHTML="分机号不能为空";
		return false;
	}
	else if(!reg.exec(subtel))
	{
		$(".asterisk")[1].innerHTML="请输入合理的分机号";
		return false;
	}
	else
	{
		$(".asterisk")[1].innerHTML="";
		return true;
	}
}

//链接服务器
function connectNTSServerBtn(acc,pwd)
{
	if(!checkIp()) return;
	if(!checkPort()) return ;
	$("#OCXPlugin",window.parent.document)[0].Connect($("#ipx").val(),$("#portx").val(),$("#accountx").val(),'12345');
	alert("链接成功");
	location.href="sysparam-init.action";
}

//绑定分机
function bindSubTelBtn(acc,pwd)
{
	if(!checkSubtel()) return;
	$("#OCXPlugin",window.parent.document)[0].Bind($("#subtelx").val());
	alert("绑定成功");
}

