<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%String path = request.getContextPath(); %>
<%@page import = "com.zyp.bean.Book" %>
<%@page import="java.util.List"%>
<%@page import = "com.zyp.dao.BookDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book Store</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/reset.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/public.css" />
</head>
<body>
<div class="public-header-warrp">
	<div class="public-header">
		<div class="content">
			<div class="public-header-logo"><a href=""><i>图书</i><h3>图书商城管理</h3></a></div>
			<div class="public-header-admin fr">
				<p class="admin-name">${user}</p>
				<div class="public-header-fun fr">
					<a href="" class="public-header-man">管理</a>
					<a href="logout.do" class="public-header-loginout">安全退出</a>	
				</div>
			</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>
<!-- 内容展示 -->
<div class="public-ifame mt20">
	<div class="content">
	<!-- 内容模块头 -->
		<div class="public-ifame-header">
			<ul>
				<li class="ifame-item logo">
					<div class="item-warrp">
						<a href="#"><i>图书</i>
							<h3 class="logo-title">${user}</h3>
							<p class="logo-des">创建于 2017/10/22 22:22:47</p>
						</a>
					</div>
				</li>
				<li class="ifame-item"><div class="item-warrp"><span>注册时间：2016/11/21 21:14:01<br></span></div></li>
				<li class="ifame-item"><div class="item-warrp" style="border:none"><span>网站浏览量：15451</span></div></li>
				<div class="clearfix"></div>
			</ul>
		</div>
		<div class="clearfix"></div>
		<!-- 左侧导航栏 -->
		<div class="public-ifame-leftnav">
			<div class="public-title-warrp">
				<div class="public-ifame-title ">
					<a href=#>首页</a>
					
				</div>
					<table >
						<tr><h1>图书管理</h1></tr><br />
						<tr>
							<form action="showBook.do" method=post >
							<input type="submit" value="显示书籍 "/><br/>
			                </form>
						</tr>
						<tr>
							<form action="admin/insert.jsp" method=post >
							<input type="submit" value="添加书籍 "/><br/>
							</form>
						</tr>
						<tr>
							<form action="admin/insertBookType.jsp" method=post >
							<input type="submit" value="添加类别 "/><br/>
							</form>
						</tr>
						<tr>
							<form action="admin/update.jsp" method=post>
							<input type="submit" value="更新书籍"/><br/>
							</form>
						</tr>
						<tr>
							<form action="admin/delete.jsp" method=post>
							<input type="submit" value="删除书籍"/><br />
							</form>
						</tr>
						<tr>
							<form action="admin/search.jsp" method=post>
							<input type="submit" value="搜索书籍"/>
							</form>
						</tr>
						<br />
						<tr><h1>用户管理</h1></tr><br />
						<tr>
							<form action="showUsers.do" method=post >
							<input type="submit" value="显示用户 "/><br/>
			                </form>
						</tr>
						<tr><h1>订单管理</h1></tr>
						<tr>
							<form action="showOrders.do" method=post >
							<input type="submit" value="显示订单 "/><br/>
			                </form>
						</tr>
					</table>
					
			</div>
		</div>
			
		<!-- 右侧内容展示部分 -->
		<div class="public-ifame-content">
			<table >
				<tr><th width="80px">选择</th>
					<td width="80px">图书编号</td>
					<td width="80px">图书名称</td>
					<td width="80px">作者</td>
					<td width="80px">出版社</td>
					<td width="80px">图书价格</td>
					<td width="80px">描述</td>
					<td width="80px">封面</td>
					<td width="80px">类型编号</td>	
				</tr>
				<form action="batchDelete.do" method="post">
				<c:forEach items="${booklist}" var="book">
				
				<tr><td><input type="checkbox" name="bookid" value="${book.id}"/></td>
				 <td>${book.id}</td>
				<td>${book.bookname}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>￥${book.price}</td>
				<td>${book.description}</td>
				<td><img src="${book.imgurl}" /></td>
				<td>${book.bookType.id}</td>
				
				</tr>
				</c:forEach>
				<input type="submit" value="批量删除" />
				</form>
			</table>
			
		</div>
		<table style="margin-left:45%">
	            	<tr>
	            		<td colspan="6" align="center" bgcolor="gray">共${page.totalRecords}条记录 共${page.totalPages }页 当前第${page.pageNo }页<br />
	            			<a href="<%=request.getContextPath() %>/showBook.do?pageNo=${page.topPageNo}"><input type="button" name="firstPage" value="首页"></a>
							<c:choose>
								<c:when test="${page.pageNo!=1}">
									<a href="<%=request.getContextPath() %>/showBook.do?pageNo=${page.previousPageNo}"><input type="button" name="previousPage" value="上一页"></a>
								</c:when>
								<c:otherwise>
									<input type="button" disable="disabled" name="previousPage" value="上一页">
								</c:otherwise>
							</c:choose>	
							
							<c:choose>
								<c:when test="${page.pageNo!=page.totalPages}">
									<a href="<%=request.getContextPath() %>/showBook.do?pageNo=${page.nextPageNo}"><input type="button" name="nextPage" value="下一页"></a>
								</c:when>
								<c:otherwise>
									<input type="button" disable="disabled" name="nextPage" value="下一页">
								</c:otherwise>
							</c:choose>	 
							<a href="<%=request.getContextPath() %>/showBook.do?pageNo=${page.bottomPageNo}"><input type="button" name="lastPage" value="尾页"></a>               			
	            		</td>
	            	</tr>	
	            </table>
	</div>
</div>
<script src="js/jquery.min.js"></script>
<script>
$().ready(function(){
	var item = $(".public-ifame-item");

	for(var i=0; i < item.length; i++){
		$(item[i]).on('click',function(){
			$(".ifame-item-sub").hide();
			if($(this.lastElementChild).css('display') == 'block'){
				$(this.lastElementChild).hide()
				$(".ifame-item-sub li").removeClass("active");
			}else{
				$(this.lastElementChild).show();
				$(".ifame-item-sub li").on('click',function(){
					$(".ifame-item-sub li").removeClass("active");
					$(this).addClass("active");
				});
			}
		});
	}
});
</script>
</body>
</html>