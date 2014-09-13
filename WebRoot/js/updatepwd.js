function showUpdatePwdDiv()
{
	//clear
	$("#oldpwdx").val('');
	$("#newpwdx").val('');
	$("#repwdx").val('');
	$(".asterisk").each(function(){
		this.innerHTML="*";
	});
	//
	$.layer({
		type: 1,
        title: '修改密码',
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['450px','280px'],
        shadeClose: false,
		bgcolor: '#fff',
		page:{dom:'#popDiv'}
	});
}

$(function(){
	$("#oldpwdx").bind('blur',checkOldpwd);
	$("#newpwdx").bind('blur',checkNewpwd);
	$("#repwdx").bind('blur',checkRepwd);
});

function checkOldpwd()
{
	var oldpwd = $("#oldpwdx").val();
	if(!oldpwd)
	{
		$(".asterisk")[0].innerHTML="原密码不能为空";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}
function checkNewpwd()
{
	var newpwd = $("#newpwdx").val();
	if(!newpwd)
	{
		$(".asterisk")[1].innerHTML="新密码不能为空";
		return false;
	}
	else
	{
		$(".asterisk")[1].innerHTML="";
		return true;
	}
}
function checkRepwd()
{
	var oldpwd = $("#oldpwdx").val();
	var newpwd = $("#newpwdx").val();
	var repwd = $("#repwdx").val();
	if(!repwd)
	{
		$(".asterisk")[2].innerHTML="确认密码不能为空";
		return false;
	}
	else if(repwd != newpwd)
	{
		$(".asterisk")[2].innerHTML="确认密码和新密码不一致";
		return false;
	}
	else
	{
		$(".asterisk")[2].innerHTML="";
		return true;
	}
}

function validatePwdinput(form)
{
	if(!checkOldpwd()) return false;
	if(!checkNewpwd()) return false;
	if(!checkRepwd()) return false;
	
	$("#form1").ajaxSubmit({ 
        success:function(data){ //提交成功的回调函数
			layer.closeAll(); 
			if(data=="true")
			{
				layer.msg("密码修改成功",2,1);
			}
			else
			{
				layer.msg("密码修改失败",2,5);
			}
        }  
	}); 
    return false;	//not refresh page
}