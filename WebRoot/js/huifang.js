$(function(){
	$("#hfnamex").bind("blur",checkHfname);
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

function saveHuifangOneBtn()
{
	if(!checkHfname()) return false;
	document.form1.submit();
	layer.msg("保存成功!",4);
}