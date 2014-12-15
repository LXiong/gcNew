$(function(){
	//
	$(".asterisk").each(function(){
		this.innerHTML="";
	});
	//
	$("#ipx").bind("blur",checkIp);
	$("#portx").bind("blur",checkPort);
	$("#subtelx").bind("blur",checkSubtel);
	$("#agttelx").bind("blur",checkAgttel);
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
		$(".asterisk")[1].innerHTML="端口不能为空";
		return false;
	}
	else if(!reg.exec(port))
	{
		$(".asterisk")[1].innerHTML="请输入合理的数字";
		return false;
	}
	else
	{
		$(".asterisk")[1].innerHTML="";
		return true;
	}
}

function checkSubtel()
{
	var subtel = $("#subtelx").val();
	var reg = /^[0-9]*[0-9][0-9]*$/;
	if(!subtel)
	{
		$(".asterisk")[3].innerHTML="分机号不能为空";
		return false;
	}
	else if(!reg.exec(subtel))
	{
		$(".asterisk")[3].innerHTML="请输入合理的分机号";
		return false;
	}
	else
	{
		$(".asterisk")[3].innerHTML="";
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

function checkAgttel()
{
	var agttel = $("#agttelx").val();
	var reg = /^[0-9]*[0-9][0-9]*$/;
	if(!agttel)
	{
		$(".asterisk")[4].innerHTML="话务员号码不能为空";
		return false;
	}
	else if(!reg.exec(agttel))
	{
		$(".asterisk")[4].innerHTML="请输入合理的号码";
		return false;
	}
	else
	{
		$(".asterisk")[4].innerHTML="";
		return true;
	}
}

//绑定话务员号码
function bindAgtBtn()
{
	if(!checkAgttel()) return;
	$("#OCXPlugin",window.parent.document)[0].AgentBind($("#agttelx").val());
	alert("绑定成功");
}