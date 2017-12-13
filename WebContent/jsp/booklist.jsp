<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>hello</h1>
<table border="1">
<tr><th>图书ID</th>
<th>图书名</th>
<th>图作作者</th>
<th>出版社</th>
<th>价格 </th>
<th>描述</th>
<th>封面</th>
<th>分类</th>
<th>操作</th></tr>
<c:forEach items="${booklist}" var="book">
<tr>
	<td>${book.id}</td>
	<td>${book.bookname}</td>
	<td>${book.author}</td>
	<td>${book.publisher}</td>
	<td>${book.price}</td>
	<td>${book.description}</td>
	<td>${book.imgurl}</td>
	<td>${book.bookType.name}</td>
	
	<td><a href="insert.jsp">插入</a>||<a href="update.do?id=${book.id}">修改</a>||
	<a href="delete.do?id=${book.id}">删除</a></td>
</tr>
</c:forEach>

</table>
</body>
</html>