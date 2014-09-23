
//set caller
function setCaller(grpid,ani)
{
	$(".asterisk").each(function(){
		this.innerHTML="*";
	});
	
	$("#grpidx").val(grpid);
	$("#anix").val(ani);
	$.layer({
		type: 1,
        title: "设置外呼主叫",
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['450px','140px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popDiv'}
	});
}


$(function(){
	$("#anix").bind("blur",checkAni);
});

function checkAni()
{
	var ani = $("#anix").val();
	var regexp=/^([0-9]|[-])+$/g;
	if(!ani)
	{
		$(".asterisk")[0].innerHTML="主叫号码不能为空";
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
function setCallerBtn()
{
	if(!checkAni()) return false;
	
	//
	$("#form1").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.closeAll();
			location.href="${pageContext.request.contextPath}/acdmonitor-list.action";
        }  
	}); 
    return false;	//not refresh page
}

