
//show select file dialog
function showSelFile(tid,tname,kind)
{
	$("#uploadExcel").click();
	$("#tidx").val(tid);
	$("#kindx").val(kind);
}
//show pop div
function showPopDiv()
{
	//clear
	$(".asterisk")[0].innerHTML="";
	$("#curFile")[0].innerHTML=$("#uploadExcel").val();
	if($("#uploadExcel").val().length>0)
	{
		$.layer({
			type: 1,
	        title: '导入 数据',
	        offset: [($(window).height() - 290)/2+'px', ''],
	        border : [5, 0.5, '#666'],
	        area: ['400px','150px'],
	        shadeClose: false,
			bgcolor: '#fff',
			page:{dom:'#popDiv'}
		});
	}
}
//submit form
function validateuploadInforFile(form)
{
	if(!validateExcelUpLoadFile(form)) return false;
	$("#form2").ajaxSubmit({ 
        success:function(data){ //提交成功的回调函数
			layer.closeAll(); 
			document.form1.submit();
        }  
	}); 
    return false;	//not refresh page
}

//导入Excel文件时进行文件格式校验 
function validateExcelUpLoadFile(form)
{
	var fileName = form2.uploadExcel.value;
    if (fileName != "" ) {
        var fileType = (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)).toLowerCase();
        var suppotFile = ["xls", "XLS", "xlsx", "XLSX"];
        for (var i = 0; i < suppotFile.length; i++) {
            if (suppotFile[i] == fileType) {
                return true;
            } else {
                continue;
            }
        }
        $(".asterisk")[0].innerHTML="文件格式不正确！";
        return false;
    } else {
    	$(".asterisk")[0].innerHTML="请选择你需要导入的文件";
        return false;
    }	
}

//empty tasktel
function emptyTasktel(tname)
{
	layer.confirm("确定要清空 【"+tname+"】中所有号码吗？",function(){
		document.form3.submit();
	});
}

//delete task
function deleteTaskTelPre(tid,ttid)
{
	$("#del_tid").val(tid);
	$("#del_ttid").val(ttid);
	layer.confirm("确定要删除吗？",function(){
		deleteTaskTel();
	});
}
function deleteTaskTel()
{
	document.form4.submit();
}


//recall
function recall(tid,tname)
{
	layer.confirm("确定要重呼所有号码吗？",function(){
		location.href="taskAction_recall.action?tid="+tid+"&tname="+tname;
	});
}
