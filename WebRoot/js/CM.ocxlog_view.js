function viewOCXLog()
{
   	$.layer({
		type: 1,
        title: "日志信息",
        offset: [($(window).height() - 290)/2+'px', ''],
        border : [5, 0.5, '#666'],
        area: ['750px','300px'],
        shadeClose: false,
		bgcolor: '#EEF1F8',
		page:{dom:'#popOCXLogDiv'}
	});
}
