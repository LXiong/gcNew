//change cts server
function changeServer(obj)
{
	var ctsVal = obj.options[obj.selectedIndex].text;
	var mframe =window.parent.document.getElementById("mainFrame");
	if(mframe.src.indexOf("nofresh")>0)
	{
		mframe.src = (mframe.src).substring(0,mframe.src.length-7);
	}
	var fs = mframe.src;
	$.ajax({
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

