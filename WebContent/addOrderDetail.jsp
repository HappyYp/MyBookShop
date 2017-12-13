<%@page import="com.zyp.bean.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body align="center">
<table align="center">
	<tr>
		<td width="200px">商品</td>
		<td width="200px">书名</td>
		<td width="200px">单价</td>
		<td width="200px">数量</td>
		<td width="200px">小计</td>
	</tr>
<c:forEach items="${shoppingBook}" varStatus="state" var="book">
	<tr>
		<td><img src="${book.imgurl}" alt="封面"/></td>
		<td>《${book.bookname}》</td>
		<td>￥${book.price}</td>
		<td>
			<c:forEach items="${bookCount}" begin="${state.index}" end="${state.index}" var="bc">
			${bc}
			</c:forEach>
		</td>
		<td>
			<c:forEach items="${singleBookTotalMoney}" begin="${state.index}" end="${state.index}" var="sm">
			${sm}
			</c:forEach>
		</td>
	</tr>
</c:forEach>
</table>
<h1>总价：${totalMoney}</h1>
<form action="addOrders.do" method="get">
	<input style="color:red;" type="submit" value="确认付款">
</form>
</body>
</html>