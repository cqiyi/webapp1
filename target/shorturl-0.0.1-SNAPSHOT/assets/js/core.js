(function($){
    //重写form默认的提交，采用rest方式提交，提交时禁用form所有控件
    $.fn.rest = function(callback){
		if(typeof app === "undefined"){
			$('html').html('系统环境初始化失败。');
			return false;
		}
    	var tags = 'input, textarea, select, button';
         this.each(function(){
              var $this = $(this);
              $this.append('<input type="hidden" name="apikey" value="' + app.apikey + '" />');
              $this.submit(function(){
                   setTimeout(function(){$this.find(tags).attr('disabled', true);}, 100);
                   $.ajax({
                        type: "POST",
                        url: $this.attr('action'),
                        data: $this.serialize(),
                        complete: function(data, status){
                           setTimeout(function(){$this.find(tags).removeAttr('disabled');}, 100);
                             callback.apply($this, [JSON.parse(data.responseText), data]);
                       }
                   });
                   return false;
              });
         });
    }
})(jQuery);

