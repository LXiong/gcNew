function firstPage(){
	$('#curPage').val('1');
	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();
	if(startdate > enddate){
		alert("开始日期不能大于结束日期 !");
	}else{
		$("#searchForm").submit();
	}
}

function lastPage(){
	$('#curPage').val($('#totalPage').val());
	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();
	if(startdate > enddate){
		alert("开始日期不能大于结束日期 !");
	}else{
		$("#searchForm").submit();
	}
}

function prePage(){
	var nowPage=parseInt($('#curPage').val());
    if (nowPage !=1){
		nowPage=nowPage-1;
		$('#curPage').val(nowPage+'');
		var startdate = $('#startdate').val();
		var enddate = $('#enddate').val();
		if(startdate > enddate){
			alert("开始日期不能大于结束日期 !");
		}else{
			$("#searchForm").submit();
		}
    }
}

function nextPage(){
	var nowPage=parseInt($('#curPage').val());
	var allpage=parseInt($('#totalPage').val());
    if (nowPage !=allpage){
		nowPage=nowPage+1;
		$('#curPage').val(nowPage+'');
		var startdate = $('#startdate').val();
		var enddate = $('#enddate').val();
		if(startdate > enddate){
			alert("开始日期不能大于结束日期 !");
		}else{
			$("#searchForm").submit();
		}
	}
}

function jumpPage(){
	var nowPage = $('#num').val();
	if(nowPage==""){
		alert("请输入页码");
	}
	if(!isNaN(nowPage)){
		$('#curPage').val(nowPage+'');
		if(parseInt(nowPage)>parseInt($('#totalPage').val())){
			$('#num').val($('#totalPage').val());
		}
	}else{
		$('#curPage').val('1');
	}
	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();
	if(startdate > enddate){
		alert("开始日期不能大于结束日期 !");
	}else{
		$("#searchForm").submit();
	}
}