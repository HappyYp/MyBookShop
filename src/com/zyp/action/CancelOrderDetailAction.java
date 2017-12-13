package com.zyp.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyp.bean.Book;
import com.zyp.service.BookService;

@Controller
public class CancelOrderDetailAction {
	@Autowired
	private BookService bookService;
	/*
	 * @Description 前台用户取消订单
	 * @Author zyp
	 */
	@RequestMapping("/cancel")
	public String cancel(Model model,HttpSession session) {
		session.removeAttribute("shoppingBook");
		String username = (String) session.getAttribute("user");
		model.addAttribute("username",username);
		List<Book> list = bookService.findAll();
		model.addAttribute("booklist",list);
		return "main";
	}
}
