<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body align="center">
	<h1>订单详情信息</h1>
	<table border="1" align="center">
		<tr><th width="150px" align="center">用户ID</th><th width="150px" align="center">订单ID</th><th width="150px" align="center">金额</th><th width="150px" align="center">订单详情ID</th><th width="150px" align="center">图书数量</th><th width="150px" align="center">图书名称</th><th width="150px" align="center">下单时间</th></tr>
		<tr>
			<td width="100px" align="center">${userId}</td>
			<td width="100px" align="center">${orderId}</td>
			<td width="100px" align="center">￥${totalMoney}</td>
			<td width="100px" align="center">${orderDetail.detailId}</td>
			<td width="100px" align="center">${orderDetail.count}</td>
			<td width="100px" align="center">
				<c:forEach items="${orderDetail.bookSet}" var="b">
				《${b.bookname}》<br />
				</c:forEach>
			</td>
			<td width="100px" align="center">${orderTime}</td>
		</tr>
		
	</table>
</body>
</html>