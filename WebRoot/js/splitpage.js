//分页算法
var obj,j;		
var page=0;
var nowPage=0;	//当前页
var lastPagex=0;//Last pages id
var listNum=20;	//每页显示数
var TotalNum=0;
var PagesLen;	//总页数
var topPage,priPage,showPage,nextPage,jumpPage,lastPage;
$(document).ready(function() {
	if(null!=document.getElementById("splitpage")){
		obj = document.getElementById("splitpage").getElementsByTagName("tr");
		listNum = document.getElementById("pageRows").value;
		j = obj.length;
		TotalNum = j;
		PagesLen=Math.ceil(j/listNum);
		
		nowPage = parent.document.getElementById("globalCurPage").value;
		if(parent.document.getElementById("globalCurPage").value!=null){
			upPage(nowPage);
		}else{
			upPage(1);
		}
	}
});
function upPage(p){
	
	if(p<=1){
		nowPage = 1;
	} else if(p > PagesLen){
		nowPage=PagesLen;
	}else{
		nowPage=p;
	}
	
	if(lastPagex>0)
	{
		for (var i=(lastPagex-1)*listNum; i<(lastPagex)*listNum; i++){
			if(obj[i])
				obj[i].style.display="none";
		}
	}
	//
	
	if(nowPage==0)
	{
		nowPage=1;
	}
	//
	if(PagesLen==0)
	{
		PagesLen=1;
	}
	//
	for (var i=(nowPage-1)*listNum; i<(nowPage)*listNum; i++){
		if(obj[i])
			obj[i].style.display="";
	}
	
	lastPagex = nowPage;
	topPage='<a href="javascript:upPage(1)">首页&nbsp;</a>  ';
	if(nowPage<1){
		priPage = '<a href="javascript:upPage(1)">上一页&nbsp;</a>  ';
	}else{
		priPage = '<a href="javascript:upPage('+(parseFloat(nowPage)-1)+')">上一页&nbsp;</a>  ';
	}
	jumpPage ='<input type="text" id="num" style="width:40px; height:16px; vertical-align:0px;" onfocus="this.select()"/>&nbsp;<a href="javascript:jumpPages()">跳转</a>&nbsp;&nbsp;';
	
	if(nowPage < PagesLen){
		nextPage = '<a href="javascript:upPage('+(parseFloat(nowPage)+1)+')">下一页&nbsp;</a>  ';
	} else {
		nextPage = '<a href="javascript:upPage('+nowPage+')">下一页&nbsp;</a>  ';
	}
	lastPage = '<a href="javascript:upPage('+PagesLen+')">尾页&nbsp;</a>  ';
	showPage = nowPage+'/'+'<span id="t_page">'+PagesLen+'</span>';
	
	document.getElementById("changePage").innerHTML=topPage+priPage+showPage+jumpPage+nextPage+lastPage;
	document.getElementById("num").value = nowPage; 
	parent.document.getElementById("globalCurPage").value=nowPage;
	document.getElementById("changePage").style.display='block';
}
function jumpPages(){
	var numb = $("#num").val();
	var reg = /^\d+$/;
	if(!numb.match(reg)){
		alert("请输入数字 ! ");
	}
	if(isNaN(numb)){
		$("#num").val(nowPage);
		return true;
	}
	upPage(numb);
}