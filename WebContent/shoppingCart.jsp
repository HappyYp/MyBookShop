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

<style type="text/css">
	.demo{width:70px; margin:20px auto 0 auto; }
	.demo h4{padding:10px 2px; font-size:14px; font-weight:bold}
	.demo p{line-height:32px}
	@media screen and (max-width: 360px) {.demo {width:340px}}
</style>
</head>
<body>
<form action="addOrderDetail.do?" method="get" name="shoppingCartForm">
<div class="public-ifame-content">
<table align="center">
	<tr>
		<td width="150px" align="center">选择</td>
		<td width="150px" align="center">ID</td>
		<td width="150px" align="center">操作</td>
		<td width="150px" align="center">商品</td>
		<td width="150px" align="center">书名</td>
		<td width="150px" align="center">单价</td>
		<td width="150px" align="center">数量</td>
		
	</tr>
	<c:forEach items="${shoppingBook}" var="book">
	<tr>
		<td align="center"><input type="checkbox" name="bookid" value="${book.id}"></td>
		<td align="center">${book.id}</td>
		<td align="center"><a style="color:red;" href="userDeleteBook.do?bookId=${book.id}">删除此商品</a></td>
		<td align="center"><img src="${book.imgurl}" alt="封面"/></td>	
		<td align="center">《${book.bookname}》</td>
		<td align="center">￥${book.price}</td>	
		<td align="center"><input type="text" name="bookCount"></td>	
	</tr>
	</c:forEach>
</table>
</div>
<input type="submit" value="提交订单"><br />
<input type="button" value="批量删除" onclick="batchDelete()">
</form>
<form action="cancel.do" method="get">
<input type="submit" value="取消订单">
</form>
<form action="cancel.do" method="get">
<input type="submit" value="删除订单">
</form>
<form action="userlogout.do" method="get">
<input type="submit" value="退出登录">
</form>

<script type="text/javascript">
function batchDelete(){
    var form = document.forms['shoppingCartForm'];
  //在这里手工指定提交给哪个ACTION
  form.action = 'userBatchDelete.do';
  //执行SUBMIT
  form.submit();
 }
</script>
</body>
</html>
