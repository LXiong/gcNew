//清空日志 
function emptylog(){
	if(confirm("您确定要清空数据库日志吗?")){
		var url = "databaseConfigAction_empty.action";
		var datajson = {};
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: datajson,
			success: function(){
				alert("清空日志成功 ！");
				location.href="databaseConfigAction_home.action";
		  	},
		  	error: function () {
				alert("清空日志成功 ！");
				location.href="databaseConfigAction_home.action";
		  	}
	    });
	}
}
//备份 
function backup(defbackupfilename){
	if(confirm("您确定要备份数据库吗?")){
		var url = "databaseConfigAction_backup.action";
		var datajson = {"defbackupfilename":defbackupfilename};
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: datajson,
			success: function(){
				alert("备份成功 ！");
				location.href="databaseConfigAction_home.action";
		  	},
		  	error: function () {
				alert("备份成功 ！");
				location.href="databaseConfigAction_home.action";
		  	}
	    });
	}
}
//索引管理
function indexManage(){
	var url = "databaseConfigAction_dbIndex.action";
	var dbindex=0;
	var dbindexs = document.getElementsByName("dbindex");
	var opname;
    for(var i=0; i<dbindexs.length; i++){
        if(dbindexs[i].checked){
        	dbindex = i;
            break;
        }
    }
    if(dbindex==0){
    	opname="清空";
    }else if(dbindex==1){
    	opname="创建";
    }else{
    	opname="重建";
    }
    if(confirm("您确定要执行 "+opname+"索引 操作吗?")){
		var datajson = {"dbindex":dbindex};
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: datajson,
			success: function(){
				alert("成功 ！");
				//location.href="databaseConfigAction_home.action";
		  	},
		  	error: function () {
		  		alert("失败 ！");
		  	}
	    });
	}
}