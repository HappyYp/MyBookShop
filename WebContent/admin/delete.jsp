<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/login.css" />
</head>
<body>
<div class="page">
	<div class="loginwarrp">
	<div class="logo">删除书籍</div>
        <div class="login_form">
		<form id="Login" action="../delete.do" method="get">
			<li class="login-item">
				<span>输入图书编号</span>
				<input type="text" name="id" class="login_input"/><br />
			</li>
		<div class="clearfix"></div>
		<li class="login-sub">
			<input type="submit" name="submit" value="提交" />
		</li>
		</form>
		</div>
	</div>
</div>
</body>
</html>
</html>