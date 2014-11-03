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
	if(flag==1 || flag==2)
	{
		//if(!checkHfname()) return false;
	}
	else if(flag==3)
	{
		
	}
	else
	{
		layer.alert("flag error",111);
		return false;
	}
	$("#form1").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			if(istp=="1")
			{
				//layer.alert("保存成功!",111,function(){
				//	location.href="agentanaly-answer.action";
				//});
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