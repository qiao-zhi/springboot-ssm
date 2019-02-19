$.ajax({
    	url: "/MySpringboot/err/getAjaxerror",
    	type: "POST",
    	async: false,
    	success: function(data) {
            if(data.success) {
            	alert("success");
            } else {
            	alert("发生异常：" + data.msg);
            }
    	},
        error: function (response, ajaxOptions, thrownError) {
        	alert("error");       
        }
    });