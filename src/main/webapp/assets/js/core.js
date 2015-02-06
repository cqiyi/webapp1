(function($){
    //重写form默认的提交，采用rest方式提交，提交时禁用form所有控件
    $.fn.rest = function(callback){
    	var tags = 'input, textarea, select, button';
    	this.each(function(){
    		var $this = $(this);
    		$this.append('<input type="hidden" name="apikey" value="' + apikey + '" />');
    		$this.submit(function(){
    			return false;
    		});
    	});
    }
})(jQuery);