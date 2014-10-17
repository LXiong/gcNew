
//set caller
function setCaller(grpid)
{
	$(".asterisk").each(function(){
		this.innerHTML="*";
	});
	
	$("#grpidx").val(grpid);
	$("#anix").val($("#ani"+grpid)[0].innerHTML);
	$.layer({
		type: 1,
        title: "设置外呼主叫",
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['450px','140px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popDiv'}
	});
}


$(function(){
	$("#anix").bind("blur",checkAni);
});

function checkAni()
{
	var ani = $("#anix").val();
	var regexp=/^([0-9]|[-])+$/g;
	if(!ani)
	{
		$(".asterisk")[0].innerHTML="主叫号码不能为空";
		return false;
	}
	else if(!regexp.exec(ani))
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
function setCallerBtn(cts)
{
	if(!checkAni()) return false;
	$("#OCXPlugin",window.parent.document)[0].SetACDCaller(cts,$("#grpidx").val(),$("#anix").val());
	layer.closeAll();
	$("#OCXPlugin",window.parent.document)[0].LookACD(cts);
	$("#OCXPlugin",window.parent.document)[0].NoticeFetch(cts);
}

function editTrunk(cts, grpid,trknum)
{
	$("#OCXPlugin",window.parent.document)[0].SetACDTrunkChg(cts,grpid,trknum);
}

function editCall(cts,grpid, callstate)
{
	$("#OCXPlugin",window.parent.document)[0].SetACDCall(cts,grpid,callstate);
}