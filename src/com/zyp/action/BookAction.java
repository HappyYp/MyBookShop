
package com.zyp.action;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyp.bean.Book;
import com.zyp.bean.BookType;
import com.zyp.bean.Page;
import com.zyp.service.BookService;
@Controller
public class BookAction {
	
	@Autowired
	private BookService bookService;
	
	/*
	 * @Description 前台分页显示所有图书
	 * @Author zyp
	 */
	@RequestMapping("/booklist")
	public String userlist(Model model,HttpServletRequest request) {
		
		String pageNo = request.getParameter("pageNo");
		if(pageNo == null) {
			pageNo = "1";
		}
		Page page = bookService.queryForPage(Integer.valueOf(pageNo), 6);
		
		request.setAttribute("page", page);
		List<Book> list = page.getBlist();
		
		List<Book> bestbook = bookService.findBestSell();
		model.addAttribute("bestbook",bestbook);
		model.addAttribute("booklist",list);
		return "index";
	}
	/*
	 * @Description 后台添加新的书籍
	 * @Author zyp
	 */
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String register(Book book,BookType bookType,Model model,@RequestParam String booktypename,HttpServletRequest request) {
		boolean result = bookService.add(bookType,book,booktypename);
		
		if(result) {
			
			String pageNo = request.getParameter("pageNo");
			if(pageNo == null) {
				pageNo = "1";
			}
			Page page = bookService.queryForPage(Integer.valueOf(pageNo), 10);
			
			request.setAttribute("page", page);
			List<Book> listt = page.getBlist();
			model.addAttribute("booklist",listt);
			return "admin/index";
		}else {
			model.addAttribute("errormsg", "插入失败");
			return "insert";
		}
		
	}
	/*
	 * @Description 前台图书列表页
	 * @Author zyp
	 */
	@RequestMapping("/booklistt")
	public String booklist(Model model,HttpServletRequest request) {
		
		String pageNo = request.getParameter("pageNo");
		if(pageNo == null) {
			pageNo = "1";
		}
		Page page = bookService.queryForPage(Integer.valueOf(pageNo), 6);
		request.setAttribute("page", page);
		List<Book> list = page.getBlist();
		model.addAttribute("booklist",list);
		return "booklistt";
	}
	/*
	 * @Description 后台删除单本书籍
	 * @Author zyp
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(int id,Model model,HttpServletRequest request) {
		boolean result = bookService.delete(id);
		
		if(result) {
			
			String pageNo = request.getParameter("pageNo");
			if(pageNo == null) {
				pageNo = "1";
			}
			Page page = bookService.queryForPage(Integer.valueOf(pageNo), 10);
			
			request.setAttribute("page", page);
			List<Book> list = page.getBlist();
			model.addAttribute("booklist",list);
			return "admin/index";
		}else {
			model.addAttribute("errormsg", "删除失败");
			return "error";
		}
		
	}
	/*
	 * @Description 后台批量删除书籍
	 * @Author zyp
	 */
	@RequestMapping("/batchDelete")
	public String batchDelete(Model model,HttpServletRequest request) {
		
		
		String[] ids = request.getParameterValues("bookid");
		List<Integer> list = new ArrayList<Integer>();
		for (int i=0;i<ids.length;i++){
			int a = new Integer(ids[i]);
			list.add(a);
		}
		boolean result = bookService.batchRemove(list);
		if(result) {
			
			String pageNo = request.getParameter("pageNo");
			if(pageNo == null) {
				pageNo = "1";
			}
			Page page = bookService.queryForPage(Integer.valueOf(pageNo), 10);
			
			request.setAttribute("page", page);
			List<Book> listt = page.getBlist();
			model.addAttribute("booklist",listt);
			return "admin/index";
		}
		else {
			model.addAttribute("errorMsg", "请选择ID");
			return "redirect:userlist.do?errormsg=error";
		}
	}
	/*
	 * @Description 前台用户批量删除购物车中的书籍
	 * @Author zyp
	 */
	@RequestMapping("/userBatchDelete")
	public String userBatchDelete(Model model,HttpServletRequest request,HttpSession session) {
		
		Set<Book> shoppingBook = (Set<Book>) session.getAttribute("shoppingBook");		
		String[] ids = request.getParameterValues("bookid");
		
		List<Integer> list = new ArrayList<Integer>();
		Book book = null;
		
		for (int i=0;i<ids.length;i++){
			book = bookService.searchBookById(Integer.parseInt(ids[i]));	
			shoppingBook.remove(book);
		}
		
		List<Integer> bookIds = (List<Integer>) session.getAttribute("bookIds");
		Iterator iterator = bookIds.iterator();
		int i = 0;
		
		while(iterator.hasNext()) {
			i = (int) iterator.next();
			for(int j = 0;j < ids.length;j++) {
				if(i==Integer.parseInt(ids[j])) {
					iterator.remove();
				}
			}
			
		}	
		
		session.setAttribute("shoppingBook", shoppingBook);
		session.setAttribute("bookIds", bookIds);
		model.addAttribute("shoppingBook",shoppingBook);
		return "shoppingCart";	
	}
	/*
	 * @Description 前台用户单本删除购物车中的书籍
	 * @Author zyp
	 */
	@RequestMapping("/userDeleteBook")
	public String userDeleteBook(Model model,HttpServletRequest request,HttpSession session) {
		String id = request.getParameter("bookId");
		Set<Book> shoppingBook = (Set<Book>) session.getAttribute("shoppingBook");		
		Book book = bookService.searchBookById(Integer.parseInt(id));	
		boolean shoppingBook2 = shoppingBook.remove(book);
		List<Integer> bookIds = (List<Integer>) session.getAttribute("bookIds");
		Iterator iterator = bookIds.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			i = (int) iterator.next();
			if(i==Integer.parseInt(id)) {
				iterator.remove();
			}
		}	
		session.setAttribute("shoppingBook", shoppingBook);
		session.setAttribute("bookIds", bookIds);
		model.addAttribute("shoppingBook",shoppingBook);
		return "shoppingCart";	
	}
	/*
	 * @Description 后台修改书籍
	 * @Author zyp
	 */
	@RequestMapping("/update")
	public String updatesubmit(Model model,Book book,BookType bookType,HttpServletRequest request) {
		
		boolean result =bookService.update(book,bookType);
		if(result) {
			
			String pageNo = request.getParameter("pageNo");
			if(pageNo == null) {
				pageNo = "1";
			}
			Page page = bookService.queryForPage(Integer.valueOf(pageNo), 10);
			
			request.setAttribute("page", page);
			List<Book> listt = page.getBlist();
			model.addAttribute("booklist",listt);
			return "admin/index";
		}	
		else {
			model.addAttribute("book", book);
			model.addAttribute("errorMsg", "修改用户信息失败");
			return "update";
		}
	}
	/*
	 * @Description 后台搜索书籍
	 * @Author zyp
	 */
	@RequestMapping("/search")
	public String searchBook(Model model,@RequestParam String bookname) {
		List<Book> list = bookService.searchBook(bookname);
		model.addAttribute("booklist",list);
		return "admin/index";	
	}
	/*
	 * @Description 后台添加图书类别
	 * @Author zyp
	 */
	@RequestMapping("/insertBookType")
	public String insertBookType(Model model,BookType bookType,@RequestParam String name) {
		System.out.println("aasdfsfasdfsafsadfasffs");
		bookService.insertBookType(bookType, name);
		List<Book> listt = bookService.findAll();
		model.addAttribute("booklist",listt);
		return "admin/index";
	}	
}
