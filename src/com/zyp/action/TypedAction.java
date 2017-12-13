package com.zyp.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyp.bean.Book;
import com.zyp.bean.BookType;
import com.zyp.service.BookService;

@Controller
public class TypedAction {
	@Autowired
	private BookService bookService;
	/*
	 * @Description 前台显示分类后的书籍
	 * @Author zyp
	 */
	@RequestMapping("/typedBook")
	public String typedList(Model model,@RequestParam String type) {
		List<Book> list = bookService.searchByType(Integer.parseInt(type));
		model.addAttribute("booklist",list);
		return "typedlist";
	}
	/*
	 * @Description 前台图书分类页
	 * @Author zyp
	 */
	@RequestMapping("/typelist")
	public String typelist(Model model) {
		List<BookType> typelist = bookService.searchType();
		model.addAttribute("typelist",typelist);
		return "typelist";
	}
}
