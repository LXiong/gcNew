//清空日志 
function emptylog(){
	layer.confirm("您确定要清空数据库日志吗?",function(){
		var datajson = {};
		$.ajax({
			type: "POST",
			url: "dbconfig-empty.action",
			data: datajson,
			success: function(data){
				if(data=="ok"){
					location.href="dbconfig.action";
				}
		  	}
	    });
	});
}
//备份 
function backup(defbackupfilename){
	layer.confirm("您确定要备份数据库吗?",function(){
		var datajson = {"defbackupfilename":defbackupfilename};
		$.ajax({
			type: "POST",
			url: "dbconfig-backup.action",
			data: datajson,
			success: function(data){
				if(data=="ok"){
					location.href="dbconfig.action";
				}
		  	}
	    });
	});
}