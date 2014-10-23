$(function(){
	$(".asterisk").each(function(){
		this.innerHTML="";
	});
	$("#anix").bind("blur",checkAni);
	$("#maxwaitx").bind("blur",checkMaxwait);
});


function checkAni()
{
	var ani = $("#anix").val();
	var regexp=/^([0-9]|[-])+$/g;
	if(!ani)
	{
		$(".asterisk")[0].innerHTML="外呼主叫不能为空";
		return false;
	}
	else if(!regexp.exec(ani))
	{
		$(".asterisk")[0].innerHTML="号码不合理";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}

function checkMaxwait()
{
	var maxwait = $("#maxwaitx").val();
	var regexp= /^\d+$/;
	if(!maxwait)
	{
		$(".asterisk")[1].innerHTML="等待时长不能为空";
		return false;
	}
	else if(!regexp.exec(maxwait))
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
function saveParamBtn(cts)
{
	if(!checkAni()) return false;
	if(!checkMaxwait()) return false;
	
	$("#form1").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.msg("设置成功",1,111,function(){
				$("#OCXPlugin",window.parent.document)[0].NoticeFetch(cts,"");
				location.href="sysparam-list.action";
			});
        }  
	}); 
    return false;	//not refresh page
}