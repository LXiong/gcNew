function saveSubTel(t,cts,telid,telnum,defagent,defacd,defcmd,clientname)
{
	$(".asterisk").each(function(){
		this.innerHTML="*";
	});
	var tit;
	if(t=="add")
	{
		tit="为【"+cts+"】添加座席分机";
	}
	else
	{
		tit="修改【"+cts+"】座席分机";
	}
	//
	$("#telidx").val(telid);
	$("#telnumx").val(telnum);
	$("#defagentx").val(defagent);
	$("#defacdx").val(defacd);
	$("#defcmdx").val(defcmd);
	$("#clientnamex").val(clientname);
	
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
	$("#telnumx").bind("blur", checkTelnum);
	$("#defagentx").bind("blur", checkDefagent);
	$("#defacdx").bind("blur", checkDefacd);
	$("#defcmdx").bind("blur", checkDefcmd);
	$("#clientnamex").bind("blur", checkClient);
});

function checkTelnum()
{
	var telnum = $("#telnumx").val();
	var regexp=/^([0-9]|[-])+$/g;
	
	if(!telnum)
	{
		$(".asterisk")[0].innerHTML="号码不能为空";
		return false;
	}
	else if(!regexp.exec(telnum))
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
function checkDefagent()
{
	var defagent = $("#defagentx").val();
	var regexp=/^([0-9]|[-])+$/g;
	
	if(!defagent)
	{
		$(".asterisk")[1].innerHTML="号码不能为空";
		return false;
	}
	else if(!regexp.exec(defagent))
	{
		$(".asterisk")[1].innerHTML="号码不合理";
		return false;
	}
	else
	{
		$(".asterisk")[1].innerHTML="";
		return true;
	}
}
function checkDefacd()
{
	var defacd = $("#defacdx").val();
	var regexp=/^([0-9]|[-])+$/g;
	
	if(!defacd)
	{
		$(".asterisk")[2].innerHTML="号码不能为空";
		return false;
	}
	else if(!regexp.exec(defacd))
	{
		$(".asterisk")[2].innerHTML="号码不合理";
		return false;
	}
	else
	{
		$(".asterisk")[2].innerHTML="";
		return true;
	}
}
function checkDefcmd()
{
	var defcmd = $("#defcmdx").val();
	if(!defcmd)
	{
		$(".asterisk")[3].innerHTML="命令不能为空";
		return false;
	}
	else
	{
		$(".asterisk")[3].innerHTML="";
		return true;
	}
}
function checkClient()
{
	var clientname = $("#clientnamex").val();
	if(!clientname)
	{
		$(".asterisk")[4].innerHTML="账号不能为空";
		return false;
	}
	else
	{
		$(".asterisk")[4].innerHTML="";
		return true;
	}
}

//save
function saveSubTelBtn()
{
	if(!checkTelnum()) return false
	if(!checkDefagent()) return false
	if(!checkDefacd()) return false
	if(!checkDefcmd()) return false
	if(!checkClient()) return false
	$("#form2").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.closeAll();
			location.href="${pageContext.request.contextPath}/subtel-list.action?pageflag=update";
        }  
	}); 
    return false;	//not refresh page
}

function deleteSubtel(telid)
{
	$("#del_telid").val(telid);
	layer.confirm("确定要删除吗？",function(){
		$("#form3").ajaxSubmit({ 
			success:function(data){ //提交成功的回调函数
				location.href="subtel-list.action?pageflag=update";		
	        }  
		}); 
	    return false;
	});
}

