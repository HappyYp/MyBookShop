package com.zyp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zyp.bean.Book;
import com.zyp.bean.Page;
import com.zyp.service.AdminService;
import com.zyp.service.BookService;

@Controller
public class AdminAction {
	@Autowired
	private AdminService adminService;
	@Autowired
	private BookService bookService;
	/*
	 * @Description 管理员登录
	 * @Author zyp
	 */
	@RequestMapping("/login")
	public String login(HttpSession session,Model model,@RequestParam("username") String name,@RequestParam("password")String password,HttpServletRequest request) {
		boolean login = adminService.login(name, password);
		model.addAttribute("username",name);
		
		if(login)
		{
			session.setAttribute("user", name);
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
			return "admin/adminfail";
		}
	}
	/*
	 * @Description 后台显示所有图书
	 * @Author zyp
	 */
	@RequestMapping("/showBook")
	public String booklist(Model model,HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		if(pageNo == null) {
			pageNo = "1";
		}
		Page page = bookService.queryForPage(Integer.valueOf(pageNo), 10);
		
		request.setAttribute("page", page);
		List<Book> list = page.getBlist();
		
		model.addAttribute("booklist",list);
		return "admin/index";
	}
	/*
	 * @Description 管理员退出
	 * @Author zyp
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session,Model model) {
		if (session.getAttribute("user") != null) {
			session.invalidate();
			return "logoutSuccess";
		}else {
			return "admin/index";
		}
	}
}
