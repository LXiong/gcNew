//movies - table id
$(function(){
	$("#movies tr:odd").css("background-color", "#D9EDF7"); 
	$("#movies tr:even").css("background-color","#fff");  
	$("#movies tr:odd").addClass("odd") 
	$("#movies tr:even").addClass("even")
	//mouse hover
	$("#movies tr").hover(function(){$(this).children("td").addClass("tab_hover")},function(){$(this).children("td").removeClass("tab_hover")})
});