//change cts server
function changeServer(obj)
{
	var ctsVal = obj.options[obj.selectedIndex].text;
	var mframe =window.parent.document.getElementById("mainFrame");
	var fs = mframe.src;
	$.ajax({
		cache: false,
		async: false,
		type: "POST",
		data: {cts: ctsVal},
		url: "saveCTS.action",
		success: function() {
			if(fs.indexOf("acdm")>0 || fs.indexOf("acd-")>0 || fs.indexOf("subtel")>0)
			{
				mframe.src = mframe.src;
			}
		}
	});
}

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

