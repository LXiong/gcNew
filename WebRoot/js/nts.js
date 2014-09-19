//pop
function saveNts(t)
{
	var tit;
	if(t=="add")
	{
		tit="添加NTS";
	}
	else
	{
		tit="修改NTS";
	}
	$.layer({
		type: 1,
        title: tit,
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['450px','400px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popDiv'}
	});
}
//save
function submitSaveNtsBtn()
{
	$("#form2").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.closeAll();
			location.href="${pageContext.request.contextPath}/ntsAction_home.action";
        }  
	}); 
    return false;	//not refresh page
}

//delete agent
function deleteNtsPre()
{
	layer.confirm("确定要删除吗？",function(){
		deleteNts();
	});
}
function deleteNts()
{
	document.form3.submit();
}
