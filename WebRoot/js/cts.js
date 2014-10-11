//change cts server
function changeServer(obj,url)
{
	var ctsVal = obj.options[obj.selectedIndex].text;
	$.ajax({
		async: false,
		type: "POST",
		data: {cts: ctsVal},
		url: "saveCTS.action",
		success: function() {
			if(url=="acdmonitor")
			{
				location.href="acdmonitor-list.action";
			}
			else if(url=="subtelmonitor")
			{
				location.href="subtelmonitor-list.action";
			}
			else if(url=="acd")
			{
				location.href="acd-list.action";
			}
			else if(url=="subtel")
			{
				location.href="subtel-list.action";
			}
		}
	});
}

/**
 * 上线之后delete
 */
function checkCTS(fromClientCts)
{
	$.ajax({
		cache: false,
		async: false,
		type: "POST",
		data: {cts: fromClientCts},
		url: "saveCTS.action",
		success: function() {
			
		}
	});	
}

