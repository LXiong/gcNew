function saveBlackNumber(t,bid,tnum,ninfo)
{
	var tit;
	if(t=="add")
	{
		tit="添加黑名单";
	}
	else
	{
		tit="修改黑名单";
	}
	$(".asterisk")[0].innerHTML="";
	$("#bidx").val(bid);
	$("#telnumx").val(tnum);
	$("#noteinfox").val(ninfo);
    //如果已经请求过，则直接读取缓存节点
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
	$("#telnumx").blur(function(){
		checkTelNumber(this);
	}).focus(function(){
		$(".asterisk")[0].innerHTML="";
	});
});


function checkTelNumber(o)
{
	var tn=o.value;
	var regexp=/^([0-9]|[-])+$/g;
	if(!tn)
	{
		$(".asterisk")[0].innerHTML="电话号码不能为空";
		return false;
	}
	else if(!regexp.exec(tn))
	{
		$(".asterisk")[0].innerHTML="请输入合理的电话号码";
		return false;
	}
	else
	{
		$(".asterisk")[0].innerHTML="";
		return true;
	}
}

//提交按钮 
function submitSaveNumberBtn()
{
	if(!checkTelNumber(document.all.telnumx)) return;
	document.form2.submit();
}

//clear
//empty
function emptyBlack()
{
	layer.confirm("确定要清空黑名单中的所有号码吗？",function(){
		$("#form3").ajaxSubmit({ 
			success:function(data){ //提交成功的回调函数
				location.href="black-list.action";
	        }  
		}); 
	    return false;	//not refresh page
	});
}

//delete
function deleteBlack(bid)
{
	$("#del_bid").val(bid);
	layer.confirm("确定要删除吗？",function(){
		$("#form4").ajaxSubmit({ 
			success:function(data){ //提交成功的回调函数
				location.href="black-list.action?pageflag=update";
	        }  
		}); 
	    return false;	//not refresh page
	});
}

//import black telnum
//show select file dialog
function showSelFile()
{
	$.layer({
		type: 1,
        title: '导入 数据',
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['400px','150px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popImportDiv'}
	});
}
//submit form
function validateuploadInforFile(form)
{
	if(!validateExcelUpLoadFile(form)) return false;
	var f = $("#uploadExcel").val()
	//lastIndexOf("查找的字符串","字符串长度")
	var index = f.lastIndexOf("\\",f.length);
	if(parseInt(index)>0)
		f = f.substring(index+1, f.length);
	layer.closeAll();
	layer.load("正在导入【"+f+"】...",0);
	$("#form5").ajaxSubmit({ 
        success:function(data){ //提交成功的回调函数
			document.form1.submit();
        }  
	}); 
    return false;	//not refresh page
}

//导入Excel文件时进行文件格式校验 
function validateExcelUpLoadFile(form)
{
	var fileName = form5.uploadExcel.value;
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
function exportBlackData()
{
	document.form6.submit();
}