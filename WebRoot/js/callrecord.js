$(function(){
	$("#waittimex").bind("blur",checkWaittime);
	$("#calltimex").bind("blur",checkCalltime);
});

function checkWaittime()
{
	var waittime = $("#waittimex").val();
	var regexp = /^[0-9]*$/;
	if(!waittime)
	{
		$(".asterisk")[0].innerHTML="等待时长不能为空";
		return false;
	}
	else if(!regexp.exec(waittime))
	{
		$(".asterisk")[0].innerHTML="等待时长只能是数字";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}
function checkCalltime()
{
	var calltime = $("#calltimex").val();
	var regexp = /^[0-9]*$/;
	if(!calltime)
	{
		$(".asterisk")[1].innerHTML="通话时长不能为空";
		return false;
	}
	else if(!regexp.exec(calltime))
	{
		$(".asterisk")[1].innerHTML="通话时长只能是数字";
		return false;
	}
	else
	{
		$(".asterisk")[1].innerHTML="";
		return true;
	}
}

function queryCall()
{
	if(!checkWaittime()) return false;
	if(!checkCalltime()) return false;
	document.form1.submit();
}
//play music
function play(ip,t,w)
{
	$.layer({
		type: 1,
        title: "正在播放:"+t,
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [0, 0.5, '#666'],
        area: ['400px','45px'],
        shadeClose: false,
		bgcolor: '#9DA7B0',
		page:{dom:'#popMusicDiv'}
	});
	var info = "<embed src='"+ip+"/"+w+"' hidden='no' height='45' width='400' palette='red|white'></embed>";
	$("#popMusicDiv")[0].innerHTML=info;
}
