
function deleteTaskTelPre(tid,ttid)
{
	$("#del_tid").val(tid);
	$("#del_ttid").val(ttid);
	layer.confirm("确定要删除吗？",function(){
		deleteTaskTel();
	});
}

//delete task
function deleteTaskTel()
{
	document.form4.submit();
}
