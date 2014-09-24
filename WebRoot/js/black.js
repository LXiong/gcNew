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
		document.form3.submit();
	});
}

//delete
function deleteBlackPre(bid)
{
	$("#del_bid").val(bid);
	layer.confirm("确定要删除吗？",function(){
		deleteBlack();
	});
}
function deleteBlack()
{
	document.form4.submit();
}
//import black telnum
//show select file dialog
function showSelFile()
{
	$("#uploadExcel").click();
}
function showPopDiv()
{
	//clear
	$(".asterisk")[1].innerHTML="";
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
			bgcolor: '#EEF1F8',
			page:{dom:'#popImportDiv'}
		});
	}
}
//submit form
function validateuploadInforFile(form)
{
	if(!validateExcelUpLoadFile(form)) return false;
	$("#form5").ajaxSubmit({ 
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