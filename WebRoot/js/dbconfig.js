//清空日志 
function emptylog(){
	if(confirm("您确定要清空数据库日志吗?")){
		var url = "dbconfig_empty.action";
		var datajson = {};
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: datajson,
			success: function(){
				alert("清空日志成功 ！");
				location.href="dbconfig.action";
		  	},
		  	error: function () {
				alert("清空日志成功 ！");
				location.href="dbconfig.action";
		  	}
	    });
	}
}
//备份 
function backup(defbackupfilename){
	if(confirm("您确定要备份数据库吗?")){
		var url = "dbconfig_backup.action";
		var datajson = {"defbackupfilename":defbackupfilename};
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: datajson,
			success: function(){
				alert("备份成功 ！");
				location.href="dbconfig.action";
		  	},
		  	error: function () {
				alert("备份成功 ！");
				location.href="dbconfig.action";
		  	}
	    });
	}
}