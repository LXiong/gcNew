var confPass = function(){
	function init(){
		$('#oldpwd').bind('blur',checkOldPwd);
		$('#newpwd').bind('blur',checkNewPwd);
		$('#repwd').bind('blur',checkRePwd);
		$('#submitButton').bind('click',submitPassInfo);
	}
	function checkOldPwd(){
		var oldpwd = $("#oldpwd").val();
		if(oldpwd==""){
			$("#oldpwdtips").html("原密码不能为空");
			passForm.oldpwd.focus(); 
			return false;
		}else{
			$("#oldpwdtips").html("");
			return true;
		}
	}
	function checkNewPwd(){
		var newpwd = $("#newpwd").val();
		if(newpwd==""){
			$("#newpwdtips").html("新密码不能为空");
			passForm.newpwd.focus(); 
			return false;
		}else{
			$("#newpwdtips").html("");
			return true;
		}
	}
	function checkRePwd(){
		var newpwd = $("#newpwd").val();
		var repwd = $("#repwd").val();
		if(repwd==""){
			$("#repwdtips").html("确认密码不能为空");
			passForm.repwd.focus(); 
			return false;
		}else if(newpwd!=repwd){
			$("#repwdtips").html("两次密码输入不一致");
			passForm.repwd.focus(); 
			return false;
		}else{
			$("#repwdtips").html("");
			return true;
		}
	}
	
	function submitPassInfo(){
		if(checkOldPwd() && checkNewPwd() && checkRePwd()){
			var url = "updatepwd.action";
			var oldpwd = $("#oldpwd").val();
			var newpwd = $("#newpwd").val();
			var datajson = {"oldpwd":oldpwd, "newpwd":newpwd};
			$.ajax({
	            type: "POST",
	            url: url,
	            dataType: "json",
	            data: datajson,
	            success: resUpdatePass,
	            error: function () {
	                alert("修改失败");
	            }
		    });
		}
	}
	function resUpdatePass(data, textStatus, jqXHR){
		var oWin = document.getElementById("win");
		var oLay = document.getElementById("overlay");
		if(data.status=="1"){
			oLay.style.display = "none"; 
			oWin.style.display = "none"
			alert("密码修改成功");
		}else{
			alert("原密码输入错误");
		}
	}
	return {
		init:function(){init();}
	}
}();

function popDivOpen(){
	var oWin = document.getElementById("win");
	var oLay = document.getElementById("overlay"); 
	var oBtn = document.getElementById("bt");
	var oClose = document.getElementById("close"); 
	var oH2 = oWin.getElementsByTagName("h2")[0]; 
	// 清空input输入框
	$("#oldpwd").val('');
	$("#newpwd").val('');
	$("#repwd").val('');
	var oldpwdtips = document.getElementById("oldpwdtips");
	var newpwdtips = document.getElementById("newpwdtips");
	var repwdtips = document.getElementById("repwdtips");
	oldpwdtips.innerHTML="";
	newpwdtips.innerHTML="";
	repwdtips.innerHTML="";
	
	var bDrag = false; 
	var disX = disY = 0; 
	oLay.style.display = "block"; 
	oWin.style.display = "block" 
	oClose.onclick = function(){ 
		oLay.style.display = "none"; 
		oWin.style.display = "none" 
	}; 
	oClose.onmousedown = function(event){ 
		(event || window.event).cancelBubble = true; 
	}; 
	oH2.onmousedown = function(event){ 
		var event = event || window.event; 
		bDrag = true; 
		disX = event.clientX - oWin.offsetLeft; 
		disY = event.clientY - oWin.offsetTop; 
		this.setCapture && this.setCapture(); 
		return false 
	}; 
	document.onmousemove = function(event){ 
		if (!bDrag) return; 
		var event = event || window.event; 
		var iL = event.clientX - disX; 
		var iT = event.clientY - disY; 
		var maxL = document.documentElement.clientWidth - oWin.offsetWidth; 
		var maxT = document.documentElement.clientHeight - oWin.offsetHeight; 
		iL = iL < 0 ? 0 : iL; 
		iL = iL > maxL ? maxL : iL; 
		iT = iT < 0 ? 0 : iT; 
		iT = iT > maxT ? maxT : iT; 

		oWin.style.marginTop = oWin.style.marginLeft = 0; 
		oWin.style.left = iL + "px"; 
		oWin.style.top = iT + "px"; 
		return false 
	}; 
	document.onmouseup = window.onblur = oH2.onlosecapture = function(){ 
		bDrag = false; 
		oH2.releaseCapture && oH2.releaseCapture(); 
	}; 
}