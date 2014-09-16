//split agent page
$(function(){
	$("div.holder").jPages({
		containerID : "movies",
        first : "首页",
        previous : "上一页",
        next : "下一页",
        last : "尾页",
        perPage : 26,
        keyBrowse:true,
        delay : 5,
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

//pop save agent
function saveAgent(t,agtid,account,telnum,agtname,email)
{
	$(".asterisk").each(function(){
		this.innerHTML="*";
	});
	
	var tit;
	if(t=="add")
	{
		tit="添加话务员";
	}
	else
	{
		tit="修改话务员信息";
	}
	//
	$("#agtidx").val(agtid);
	$("#accountx").val(account);
	$("#telnumx").val(telnum);
	$("#agtnamex").val(agtname);
	$("#emailx").val(email);
	
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
	$("#accountx").bind("blur",checkAccount);
	$("#telnumx").bind("blur",checkTelnum);
	$("#agtnamex").bind("blur",checkAgtname);
});

function checkAccount()
{
	var acc = $("#accountx").val();
	if(!acc)
	{
		$(".asterisk")[0].innerHTML="登录账号不能为空";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}
function checkTelnum()
{
	var tel = $("#telnumx").val();
	var regexp=/^([0-9]|[-])+$/g;
	if(!tel)
	{
		$(".asterisk")[1].innerHTML="话务员号码不能为空";
		return false;
	}
	else if(!regexp.exec(tel))
	{
		$(".asterisk")[1].innerHTML="请输入合理的电话号码";
		return false;
	}
	else
	{
		$(".asterisk")[1].innerHTML="";
		return true;
	}
}
function checkAgtname()
{
	var agt = $("#agtnamex").val();
	if(!agt)
	{
		$(".asterisk")[2].innerHTML="姓名不能为空";
		return false;
	}
	else
	{
		$(".asterisk")[2].innerHTML="";
		return true;
	}
}

//save agent
function saveAgentBtn()
{
	if(!checkAccount()) return false;
	if(!checkTelnum()) return false;
	if(!checkAgtname()) return false;
	
	$("#form2").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.closeAll();
			if(data=="ok")
			{
				location.href="${pageContext.request.contextPath}/agentAction_home.action";
			}
			else if(data=="err_telexists")
			{
				layer.msg("保存话务员失败",2,5);
			}
			else if(data=="err_teloraccountexists")
			{
				layer.msg("保存话务员失败",2,5);
			}
			else
			{
				layer.msg("保存话务员失败",2,5);	
			}
        }  
	}); 
    return false;	//not refresh page
}

//delete agent
function deleteAgentPre(agtid)
{
	$("#del_agtid").val(agtid);
	layer.confirm("确定要删除吗？",function(){
		deleteAgent();
	});
}
function deleteAgent()
{
	document.form3.submit();
}

//init agent password
function initAgentpwdPre(agtid)
{
	$("#init_agtid").val(agtid);
	initAgentpwd();
}
function initAgentpwd()
{
	$("#form4").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.msg("密码初始化成功",1,111);
        }  
	}); 
    return false;
}

