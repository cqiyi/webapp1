// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
Date.prototype.addDays = function(d)
{
    this.setDate(this.getDate() + d);
    return this;
};

Date.prototype.addWeeks = function(w)
{
    this.addDays(w * 7);
    return this;
};

Date.prototype.addMonths= function(m)
{
    var d = this.getDate();
    this.setMonth(this.getMonth() + m);

    if (this.getDate() < d)
        this.setDate(0);
    return this;
};

Date.prototype.addYears = function(y)
{
    var m = this.getMonth();
    this.setFullYear(this.getFullYear() + y);

    if (m < this.getMonth()) 
    {
        this.setDate(0);
    }
    return this;
};
//测试 var now = new Date(); now.addDays(1);//加减日期操作 alert(now.Format("yyyy-MM-dd"));

Date.prototype.dateDiff = function(interval,endTime)  
{      
    switch (interval)      
    {             
        case "s":   //計算秒差                        
            return parseInt((endTime-this)/1000);                                  
        case "n":   //計算分差                    
            return parseInt((endTime-this)/60000);               
        case "h":   //計算時差                        
            return parseInt((endTime-this)/3600000);                 
        case "d":   //計算日差             
            return parseInt((endTime-this)/86400000);            
        case "w":   //計算週差            
            return parseInt((endTime-this)/(86400000*7));            
        case "m":   //計算月差                 
            return (endTime.getMonth()+1)+((endTime.getFullYear()-this.getFullYear())*12)-(this.getMonth()+1);           
        case "y":   //計算年差                       
            return endTime.getFullYear()-this.getFullYear();            
        default:    //輸入有誤                      
            return undefined;       
    }    
};

(function($) {
	if (typeof app === "undefined") {
		$('html').html('系统环境初始化失败：app未定义。');
		return false;
	}

	if (typeof CryptoJS === "undefined") {
		$('html').html('系统环境初始化失败：CryptoJS 未定义。');
		return false;
	}

	$.randomString = function(len) {
		len = len || 32;
		var $chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
		var maxPos = $chars.length;
		var output = '';
		for (i = 0; i < len; i++) {
			output += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
		return output;
	}

	$.ajaxSetup({
		headers : {
			"apikey" : app.apikey
		},
		beforeSend : function(XHR) {
			var salt = $.randomString();
			XHR.setRequestHeader("apikey", app.apikey);
			XHR.setRequestHeader("salt", salt);
			//XHR.setRequestHeader("paintext", app.apikey + salt + app.secret);
			XHR.setRequestHeader("authentication-token", CryptoJS.SHA256(app.apikey + salt + app.secret));
		}

	});

	// 重写form默认的提交，采用rest方式提交，提交时禁用form所有控件
	$.fn.rest = function(callback) {
		var tags = 'input, textarea, select, button';
		this.each(function() {
			var $this = $(this);
//			$this.append('<input type="hidden" name="apikey" value="'
//					+ app.apikey + '" />');
			$this.submit(function(event) {
				if(event.isPropagationStopped()){
					//提交的事件链已被取消
					return;
				}
				setTimeout(function() {
					$this.find(tags).attr('disabled', true);
				}, 100);
				$.ajax({
					url : $this.attr('action'),
					type : "POST",
					data : $this.serialize(),
					complete : function(data, status) {
						setTimeout(function() {
							$this.find(tags).removeAttr('disabled');
						}, 100);
						callback.apply($this, [ JSON.parse(data.responseText),
								data ]);
					}
				});
				event.stopPropagation();
				return false;
			});
		});
	}
})(jQuery);
