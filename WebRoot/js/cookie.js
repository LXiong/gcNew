  !function() {
    var x = function(x) { return (x + x).slice(1,3); };
    window.encrypt = function (s) {
        return function() { return this; }()
        ['\x65\x6e\x63\x6f\x64\x65\x55\x52\x49\x43\x6f\x6d\x70\x6f\x6e\x65\x6e\x74'](s).replace(/%(..)|([^%])/g, function($0, $1, $2) {
            return x($1 || ('0'+$2.charCodeAt(0).toString(16)).slice(-2));
        });
    };
    window.decrypt = function decrypt(s) {
        return function() { return this; }()
        ['\x64\x65\x63\x6f\x64\x65\x55\x52\x49\x43\x6f\x6d\x70\x6f\x6e\x65\x6e\x74'](s.replace(/../g, function($0) {
            return '%'+x($0);
        }));
    };
}();
var Cookie = {
	setCookie: function(name, value, expires, path, domain)
	{
		var date = new Date();
		value=encrypt(value);
		var ms = 30*24*3600*1000;
		date.setTime(date.getTime() + ms);
		document.cookie = name + "=" + escape(value) +"; expires="+ date.toGMTString()+";" +
			((path) ? "; path=" + path : "; path=/") +
			((domain) ? "; domain=" + domain : "");
	},

	getCookie: function(name)
	{
		var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));

		if (arr != null)
		{
			return decrypt(unescape(arr[2]));
		
		}

		return null;
	},

	clearCookie: function(name, path, domain)
	{
		if (Cookie.getCookie(name))
		{
			document.cookie = name + "=" +
				((path) ? "; path=" + path : "; path=/") +
				((domain) ? "; domain=" + domain : "") +
				";expires=Fri, 02-Jan-1970 00:00:00 GMT";
		}
	}
};

var logout=function(){
	//Cookie.clearCookie("email");
	//Cookie.clearCookie("password");
	window.location.href="logout.action";
}
	