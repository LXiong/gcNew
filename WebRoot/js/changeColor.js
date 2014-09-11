/* 当鼠标移到表格上是，当前一行背景变色 */

$(document).ready(function(){
	$(".data_list tbody tr td").mouseover(function(){
		$(this).parent().find("td").css("background-color","#d5f4fe");
    });
});

/* 当鼠标在表格上移动时，离开的那一行背景恢复 */
$(document).ready(function(){ 
	
	$(".data_list tbody tr td").mouseout(function(){
		var bgc = $(this).parent().attr("bg");
		$(this).parent().find("td").css("background-color",bgc);
    });
});
$(document).ready(function(){ 
	var color="#E5F2F8";
	$(".data_list tbody tr:odd td").css("background-color",color);  //改变偶数行背景色
	$(".data_list tbody tr:even td").css("background-color","#fff");  //改变偶数行背景色
	/* 把背景色保存到属性中 */
	$(".data_list tbody tr:odd").attr("bg",color);
	$(".data_list tbody tr:even").attr("bg","#fff");
});