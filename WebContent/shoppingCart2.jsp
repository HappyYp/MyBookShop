<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的购物车</title>

<link rel="stylesheet" type="text/css" href="admin/css/reset.css" />
<link rel="stylesheet" type="text/css" href="admin/css/public.css" />
<script type="text/javascript" src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="spinner/jquery.spinner.js"></script>
<style type="text/css">
	.demo{width:70px; margin:20px auto 0 auto; }
	.demo h4{padding:10px 2px; font-size:14px; font-weight:bold}
	.demo p{line-height:32px}
	@media screen and (max-width: 360px) {.demo {width:340px}}
</style>
</head>
<body>
<form action="addOrderDetail.do?" method="get">
<div class="public-ifame-content">
<table align="center">
	<tr>
		<td width="80px">操作</td>
		<td width="80px">商品</td>
		<td width="80px">书名</td>
		<td width="80px">单价</td>
		<td width="80px">数量</td>
		
	</tr>
	
	<c:forEach items="${shoppingBook}" var="book">
	<tr>
		<td><a href="userDeleteBook.do?bookId=${book.id}">删除此商品</a></td>
		<td><img src="${book.imgurl}" alt="封面"/></td>	
		<td>${book.bookname}</td>
		<td>${book.price}</td>	
		<td><input type="text" name="bookCount"></td>
		
	</tr>
	</c:forEach>
</table>
</div>
<input type="submit" value="提交订单">
</form>
<form action="cancel.do" method="get">
<input type="submit" value="取消订单">
</form>
<script type="text/javascript">
$('.spinner').spinner();
</script>
</body>
</html>
