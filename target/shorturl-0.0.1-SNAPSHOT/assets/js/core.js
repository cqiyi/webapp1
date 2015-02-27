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
		type : "POST",
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
