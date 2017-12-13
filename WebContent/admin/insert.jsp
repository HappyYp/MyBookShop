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
		<div class="logo">添加书籍</div>
        <div class="login_form">
	<form id="Login" action="../insert.do" method="post" enctype=\"multipart/form-data\">
		<li class="login-item">
			<span>图书名称</span>
			<input type="text" name="bookname" class="login_input"/> <br />
		</li>
		<li class="login-item">
			<span>作者</span>
			<input type="text" name="author" class="login_input" /> <br />
		</li>
		<li class="login-item">
			<span>出版社</span>
			<input type="text" name="publisher" class="login_input"/> <br />
		</li>
		<li class="login-item">
			<span>图书价格</span>
			<input type="text" name="price" class="login_input"/> <br />
		</li>
		<li class="login-item">
			<span>描述</span>
			<input type="text" name="description" class="login_input"/> <br />
		</li>
		<li class="login-item">
			<span>封面</span>
			<input type="file" name="imgurl" class="login_input"/> <br />
		</li>
		<li class="login-item">
			<span>类型名称</span>
			<input type="text" name="booktypename" class="login_input"/> <br />
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