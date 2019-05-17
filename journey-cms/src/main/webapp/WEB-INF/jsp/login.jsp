<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台登录 - 人在旅途</title>

<link href="css/main.css" rel="stylesheet" type="text/css" />

</head>
<body>

<div class="login">
    <div class="box png">
		<div class="logo png"></div>
		<div class="input">
			<div class="log">
				<form>
				<div class="name">
					<label>用户名</label><input type="text" class="text" id="adminname" placeholder="用户名" name="adminname" tabindex="1">
				</div>
				<div class="pwd">
					<label>密　码</label><input type="password" class="text" id="password" placeholder="密码" name="password" tabindex="2">
					<input id="login" type="button" class="submit" tabindex="3" value="登录">
					<div class="check"></div>
				</div>
				</form>
				<div class="tip"></div>
			</div>
		</div>
	</div>
    <div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/fun.base.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>

</div>
</body>

<script type="text/javascript" src="/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">

	$("#login").click(function(){
	    var adminname = $("#adminname").val();
	    var password = $("#password").val();
	    $.ajax({
			type : 'post',
			url : '/login',
			dataType : 'json',
			data:{"adminname":adminname,"password":password},
			success : function(data) {
				if (data.status) {
					window.location.href = "/";
				}else{
					layer.msg(data.msg,{icon:2,time:1000});
				}
			},
			error : function(data) {
				layer.msg('error!',{icon:2,time:1000});
			},
		});
	});

</script> 
</html>