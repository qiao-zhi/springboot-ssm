/**
 * login.html对应的JS
 */
$(function  () {
    layui.use('form', function(){
      var form = layui.form;
      // layer.msg('玩命卖萌中', function(){
      //   //关闭后的操作
      //   });
      //监听提交
      form.on('submit(login)', function(data){
    	  //打印一下填写的值然后区后台进行登陆
        $.post("doLogin.html",data.field,function(result){
        	if(result!=null && result.success == true){
        		window.location = "index.html";
        	}else{
        		layer.msg(result.msg);
        	}
        },'json');
        return false;
      });
    });
})