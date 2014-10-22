
//show select file dialog
function showSelFile(tid,tname,kind)
{
	$("#tidx").val(tid);
	$("#kindx").val(kind);
	$.layer({
		type: 1,
        title: '导入数据',
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['400px','150px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popDiv'}
	});
}

//submit form
function validateuploadInforFile(form)
{
	if(!validateExcelUpLoadFile(form)) return false;
	var f = $("#uploadExcel").val()
	var index = f.lastIndexOf("\\",f.length);
	if(parseInt(index)>0)
		f = f.substring(index+1, f.length);
	layer.closeAll();
	layer.load("正在导入【"+f+"】...",0);
	$("#form2").ajaxSubmit({ 
        success:function(data){ //提交成功的回调函数
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
        $(".asterisk")[1].innerHTML="文件格式不正确！";
        return false;
    } else {
    	$(".asterisk")[1].innerHTML="请选择你需要导入的文件";
        return false;
    }	
}

//empty tasktel
function emptyTasktel(tname)
{
	layer.confirm("确定要清空 【"+tname+"】中所有号码吗？",function(){
		$("#form3").ajaxSubmit({ 
			success:function(data){ //提交成功的回调函数
				document.form1.submit();		
	        }  
		}); 
	    return false;
	});
}

//delete task
function deleteTaskTel(tid,ttid)
{
	$("#del_tid").val(tid);
	$("#del_ttid").val(ttid);
	layer.confirm("确定要删除吗？",function(){
		$("#form4").ajaxSubmit({ 
			success:function(data){ //提交成功的回调函数
				$("#pageflag").val("update");
				document.form1.submit();		
	        }  
		}); 
	    return false;
	});
}


//recall
function recall()
{
	layer.confirm("确定重呼所有【呼叫失败】的号码？",function(){
		$("#form7").ajaxSubmit({ 
			success:function(data){ //提交成功的回调函数
				$("#pageflag").val("update");
				document.form1.submit();		
	        }  
		}); 
	    return false;
	});
}

//save tel
//show pop div
function saveTaskTel(t,tid,ttid,telnum)
{
	$(".asterisk")[1].innerHTML="";
	var tit;
	if(t=="add")
	{
		tit="添加电话号码";
	}
	else
	{
		tit="修改电话号码";
	}
	$("#save_tidx").val(tid);
	$("#save_ttidx").val(ttid);
	$("#save_telnumx").val(telnum);
	$.layer({
		type: 1,
        title: tit,
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['400px','150px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popSaveTaskTelDiv'}
	});
}

$(function(){
	$("#save_telnumx").bind("blur",checkTelnum);
	$("#startnumx").bind("blur",checkStartnum);
});

function checkTelnum()
{
	var telnum = $("#save_telnumx").val();
	var regexp=/^([0-9]|[-])+$/g;
	
	if(!telnum)
	{
		$(".asterisk")[1].innerHTML="电话号码不能为空";
		return false;
	}
	else if(!regexp.exec(telnum))
	{
		$(".asterisk")[1].innerHTML="电话号码不合理";
		return false;
	}
	else
	{
		$(".asterisk")[1].innerHTML="";
		return true;
	}
}


function saveTaskTelBtn()
{
	if(!checkTelnum()) return false;
	
	$("#form5").ajaxSubmit({ 
		success:function(data){ //提交成功的回调函数
			layer.closeAll();
			$("#pageflag").val("update");
			document.form1.submit();
        }  
	}); 
    return false;	//not refresh page
}

//black filter
function filterBlack()
{
	$.layer({
	    shade: [0],
	    area: ['auto','auto'],
	    dialog: {
	        msg: '确定要过滤号码吗？',
	        btns: 2,                    
	        type: 4,
	        btn: ['确定','取消'],
	        yes: function(){
				$("#form6").ajaxSubmit({ 
					success:function(data){ //提交成功的回调函数
						layer.alert("过滤黑名单数："+data,111);
			        }  
				}); 
			    return false;	//not refresh page
	        }, no: function(){
	            layer.closeAll();
	        }
	    }
	});
	
}

function checkStartnum()
{
	var start = $("#startnumx").val();
	var regexp = /^[0-9]*$/;
	if(!start)
	{
		$(".asterisk")[0].innerHTML="起始编号不能为空";
		return false;
	}
	else if(!regexp.exec(start))
	{
		$(".asterisk")[0].innerHTML="只能是数字";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}

function queryTelnum()
{
	if(!checkStartnum()) return false;
	document.form1.submit();
}