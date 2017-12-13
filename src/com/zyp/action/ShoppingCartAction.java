package com.zyp.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.glass.ui.Application;
import com.sun.swing.internal.plaf.basic.resources.basic;
import com.zyp.bean.Book;
import com.zyp.bean.Users;
import com.zyp.service.BookService;

@Controller
public class ShoppingCartAction {
	@Autowired
	private BookService bookService;
	/*
	 * @Description 前台用户添加进购物车
	 * @Author zyp
	 */
	@RequestMapping("/userBuy")
	public String userBuy(Model model,HttpServletRequest request,HttpSession session) {
		String bookId = request.getParameter("bookId");
		List<Integer> bookIds = new ArrayList<>();
		Set<Book> shoppingBook = new HashSet<Book>();
		if(session.getAttribute("shoppingBook") == null) {
			session.setAttribute("shoppingBook", shoppingBook);
			
		}else {
			
			shoppingBook = (Set<Book>) session.getAttribute("shoppingBook");
		}
		Book book = bookService.searchBookById(Integer.parseInt(bookId));
		shoppingBook.add(book);
		Iterator iterator =shoppingBook.iterator();
		Book book2 = null;
		while(iterator.hasNext()) {
			book2 = (Book) iterator.next();
			bookIds.add(book2.getId());
		}
		session.removeAttribute("shoppingBook");
		session.setAttribute("shoppingBook", shoppingBook);
		session.setAttribute("bookIds", bookIds);
		session.setAttribute("book2", book2);
		model.addAttribute("shoppingBook",shoppingBook);
		return "shoppingCart";
	}
}
