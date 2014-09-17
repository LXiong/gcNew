//pop save agent
function saveAgent(t,agtid,account,master,telnum,agtname,email)
{
	$(".asterisk").each(function(){
		this.innerHTML="*";
	});
	$("#ismasterchk")[0].checked=false;
	//
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
	if(master==1)
	{
		$("#ismasterchk")[0].checked=true;
	}
	else
	{
		$("#ismasterchk")[0].checked=false;
	}	
	
	//
	$("#agtidx").val(agtid);
	$("#accountx").val(account);
	$("#ismasterx").val(master);
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
		bgcolor: '#EEF1F8',
		page:{dom:'#popDiv'}
	});
}


$(function(){
	$("#accountx").bind("blur",checkAccount);
	$("#telnumx").bind("blur",checkTelnum);
	$("#agtnamex").bind("blur",checkAgtname);
	$("#emailx").bind("blur",checkEmail);
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
function checkEmail()
{
	var email = $("#emailx").val();
	var regexp=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(email!="" && !regexp.exec(email))
	{
		$(".asterisk")[3].innerHTML="请输入合法的邮箱";
		return false;
	}
	else
	{
		$(".asterisk")[3].innerHTML="";
		return true;
	}
}

// is master
function checkMaster(obj)
{
	if(obj.checked)
	{
		$("#ismasterx").val(1);
	}
	else
	{
		$("#ismasterx").val(0);
	}
}

//save agent
function saveAgentBtn()
{
	if(!checkAccount()) return false;
	if(!checkTelnum()) return false;
	if(!checkAgtname()) return false;
	if(!checkEmail()) return false;
	
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

