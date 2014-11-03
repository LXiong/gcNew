$(function(){
	//$("#hfnamex").bind("blur",checkHfname);
});

function changeCHKVal(obj)
{
	if(obj.checked==true)
	{
		obj.value=1;
	}
	else
	{
		obj.value=0;
	}
}

function checkHfname()
{
	var hfname = $("#hfnamex").val();
	if(!hfname)
	{
		layer.alert("回访姓名不能为空",111);
		return false;
	}
	else
	{
		return true;
	}
}

function saveHuifangBtn(flag,istp)
{
	$("#form1").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			if(istp=="1")
			{
				alert("保存成功!");
				location.href="agentanaly-answer.action";
			}
			else
			{
				alert("保存成功!");
			}
        }  
	}); 
    return false;	//not refresh page
}