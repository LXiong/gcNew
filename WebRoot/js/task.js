function telManage(tid, kind, tname)
{
	$("#p_tid").val(tid);
	$("#p_kind").val(kind);
	$("#p_tname").val(tname);
	document.form4.submit();
}

//save task
function saveTask(t,tid,tname,kind,taskinfo)
{
	$(".asterisk")[0].innerHTML="";
	var tit;
	if(t=="add")
	{
		tit="添加任务";
		$("#tstate_add_hide").hide();
	}
	else
	{
		$("#tstate_add_hide").show();
		tit="修改任务";
	}
	//
	$("#tidx").val(tid);
	$("#kindx").val(kind);
	$("#tnamex").val(tname);
	$("#taskinfox").val(taskinfo);
	$.layer({
		type: 1,
        title: tit,
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['450px','350px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popDiv'}
	});
}

$(function(){
	$("#tnamex").bind("blur",checkTname);
});

//validate task name
function checkTname()
{
	var tname = $("#tnamex").val();
	if(!tname)
	{
		$(".asterisk")[0].innerHTML="任务名称不能为空";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}

//submit task

function saveTaskBtn()
{
	if(!checkTname()) return false;
	
	$("#form1").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.closeAll();
			if(data=="updateok")
			{
				location.href="${pageContext.request.contextPath}/task-list.action?pageflag=update";
			}
			else if(data=="updateerr")
			{
				layer.msg("修改任务失败",2,5);
			}
			else if(data=="insertok")
			{
				location.href="${pageContext.request.contextPath}/task-list.action?pageflag=update";
			}
        }  
	}); 
    return false;	//not refresh page
}


//a->button
function deleteTaskPre(tid,tname,trn)
{
	if(trn>0)
	{
		layer.alert("请先清空任务中的号码，再删除该任务",111);
	}
	else
	{
		layer.confirm("确定要删除【"+tname+"】吗？",function(){
			deleteTask(tid,tname);
		});
	}
}

//delete task
function deleteTask(tid,tname)
{
	$("#del_tid").val(tid);
	$("#del_tname").val(tname);
	$("#form2").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			if(data=="ok")
			{
				location.href="${pageContext.request.contextPath}/task-list.action?pageflag=update";
			}
			else
			{
				layer.msg("删除任务前请清空任务队列中存在的号码",2,5);
			}
        }  
	}); 
    return false;	//not refresh page
}

//set acd for task
function setAcd(tid)
{
	$("#setacd_tidx").val(tid);
	//
	//获取回访类型
	$.ajax({
		type: "POST",
		dataType: "json",
		data: {tid: tid},
		url: "getAcdSelect.action",
		success: function(data) {
			var acdHTML=eval(JSON.stringify(data.html));
			$("#acdHTML").html(acdHTML);
		} 
	});
	//
	$.layer({
		type: 1,
        title: '指派业务组',
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['450px','300px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popSetAcdDiv'}
	});
}

function saveSetAcdBtn(cts)
{
	var tid = $("#setacd_tidx").val();
	$("#form3").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			var obj = eval('(' + data + ')');
			var acdlist = obj.acds;
			layer.closeAll();
			$("#OCXPlugin",window.parent.document)[0].ACDAllocTask(acdlist,tid);
			location.href="task-list.action";
        }  
	}); 
    return false;
}

