<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@page import="java.util.Set"%>
     <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body align="center">
	
	<h2>点击订单ID以查看订单详情</h2>
	<form action="searchOrder.do" method="get">
		<h3>请输入订单ID<input type="text" name="oId"><input type="submit" value="搜索"></h3>
	</form>
	
	<table border="3" align="center">
		<tr><th>用户ID</th><th>订单ID</th></tr>
		<c:forEach items="${uList}" varStatus="state" var="u">
		<tr>
			<td>${u}</td>
			<td>
				<c:forEach items="${oList}" begin="${state.index}" end="${state.index}" var="o">
					<c:forEach items="${o}" var="oo">
					<a href="ShowOrderDetail.do?orderId=${oo.orderId}&userId=${u}">${oo.orderId}(${oo.state})</a>
					</c:forEach>
				</c:forEach>
			</td>	
		</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>