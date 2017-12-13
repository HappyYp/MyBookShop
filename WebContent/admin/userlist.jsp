<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/reset.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/public.css" />
</head>
<body>
<div class="public-header-warrp">
	<div class="public-header">
		<div class="content">
			<div class="public-header-logo"><a href=""><i>用户</i><h3>用户信息</h3></a></div>
</div></div></div>
<div class="clearfix"></div>
<div class="public-ifame mt20">
	<div class="content">
	
<div class="public-ifame-leftnav">
	<div class="public-title-warrp">
		<div class="public-ifame-title ">
</div></div></div>
	<div class="public-ifame-content">
	<table>
		<tr><th width="150px">用户名</th><th width="150px">密码</th><th width="150px">邮箱</th><th width="150px">电话</th><th width="150px">地址</th><th width="150px">注册时间</th></tr>
		<c:forEach items="${userlist}" var="u">
			<tr>
				<td height="30px" align="center">${u.username}</td>
				<td height="30px" align="center">${u.password}</td>
				<td height="30px" align="center">${u.email}</td>
				<td height="30px" align="center">${u.tel}</td>
				<td height="30px" align="center">${u.address}</td>
				<td height="30px" align="center">${u.posttime}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	</div>
</body>
</html>