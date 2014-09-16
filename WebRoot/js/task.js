//split page task
$(function(){
	$("div.holder").jPages({
		containerID : "movies",
        first : "首页",
        previous : "上一页",
        next : "下一页",
        last : "尾页",
        perPage : 26,
        keyBrowse:true,
        delay : 0,
        callback : function( pages, items ){
	        $("#legend1").html("&nbsp;&nbsp;当前第"+pages.current+"页 ,&nbsp;&nbsp;总共"+pages.count+"页,&nbsp;&nbsp;");
	        $("#legend2").html("当前显示第"+items.range.start+" - "+items.range.end+"条记录,&nbsp;&nbsp;总共"+items.count+"条记录&nbsp;&nbsp;");
	    }
	});
      /* when button is clicked */
	$("#tiaozhuan").click(function(){
  		/* get given page */
		var page = parseInt( $("#tzval").val() );

  		/* jump to that page */
  		$("div.holder").jPages( page );
	});
});

//save task
function saveTask(t,tid,tname,kind,taskinfo)
{
	$(".asterisk")[0].innerHTML="";
	var tit;
	if(t=="add")
	{
		tit="添加任务";
		$("#tidx").val(0);
		$("#kindx").val(0);
	}
	else
	{
		tit="修改任务";
		$("#tidx").val(tid);
		$("#kindx").val(kind);
	}
	//
	$("#tnamex").val(tname);
	$("#taskinfox").val(taskinfo);
	$.layer({
		type: 1,
        title: tit,
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['450px','300px'],
        shadeClose: false,
		bgcolor: '#fff',
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
				location.href="${pageContext.request.contextPath}/taskAction_home.action";
			}
			else if(data=="updateerr")
			{
				layer.msg("修改任务失败",2,5);
			}
			else if(data=="insertok")
			{
				
				location.href="${pageContext.request.contextPath}/taskAction_home.action";
				
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
				location.href="${pageContext.request.contextPath}/taskAction_home.action";
			}
			else
			{
				layer.msg("删除任务前请清空任务队列中存在的号码",2,5);
			}
        }  
	}); 
    return false;	//not refresh page
}
