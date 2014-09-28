function login(){
	var account = $("#account").val();
	var password = $("#password").val();
	var vercode = $("#vercode").val();
	if(!account)
	{
		layer.alert("账号不能为空",111);
		return false;
	}
	if(!password)
	{
		layer.alert("密码不能为空",111);
		return false;
	}
	if(!vercode)
	{
		layer.alert("请输入验证码",111);
		return false;
	}
	var datajson = {"account":account, "password":password,"vercode":vercode};
	var url = 'ajaxlogin.action';
	$.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: datajson,
        success: responseLogin,
        error: function () {
            //alert(RES.REQUESTWRONG);
        }
    });
}

var responseLogin = function(data, textStatus, jqXHR)
{
	if(data.status=="ok")
	{
		if($('#rememberPass').is(':checked'))
		{
			Cookie.setCookie("accountgcnew",$("#account").val());
			Cookie.setCookie("passwordgcnew",$("#password").val());
		}
		else
		{
			Cookie.clearCookie("accountgcnew");	
			Cookie.clearCookie("passwordgcnew");
		}
		window.location.href="user-home.action";
	}
	else if(data.status=="vercodeerror")
	{
		layer.alert("验证码错误,请重新输入!",111);
	}
	else
	{
		layer.alert("账号或密码错误",111);
	}
	verImg.src = "verCode?"+Math.random();
}
