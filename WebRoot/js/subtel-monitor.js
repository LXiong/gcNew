function listen(telnum)
{
	$.ajax({
		type: "POST",
		dataType: "json",
		data: {telnum:telnum},
		url: "subtelmonitor-listen.action",
		success: function(data) {
		}
	});
}