seajs.config({
	base: app.jslib + '/assets/js/',
	alias:{
		'jquery':  + 'jquery-1.11.2.min.js'
	}
});

seajs.use('jquery', function($){
    //重写form默认的提交，采用rest方式提交，提交时禁用form所有控件
    $.fn.rest = function(callback){
    	var tags = 'input, textarea, select, button';
    	this.each(function(){
    		var $this = $(this);
    		$this.append('<input type="hidden" name="apikey" value="' + apikey + '" />');
    		$this.on('submit', function(){
    			setTimeout(function(){$this.find(tags).attr('disabled', true);}, 100);
    			$.ajax({
    				type: "POST",
    				url: $this.attr('action'), 
    				data: $this.serialize(), 
    				complete: function(data, status){
    					console.log(data);
            			setTimeout(function(){$this.find(tags).removeAttr('disabled');}, 100);
    					callback.apply($this, [data, status]);
        			}
    			});
    			return false;
    		});
    	});
    }
});