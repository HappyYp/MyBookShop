package com.zyp.action;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyp.bean.Book;

import com.zyp.service.BookService;
import com.zyp.service.PersonService;

@Controller
public class LoginAction {
	@Autowired
	private PersonService personService;
	@Autowired
	private BookService bookService;
	/*
	 * @Description 前台用户登录
	 * @Author zyp
	 */
	@RequestMapping("/userlogin")
	public String userLogin(HttpSession session,Model model,@RequestParam String username,@RequestParam String password) {
		boolean result = personService.findUser(username,password);
		int userID = personService.selectIdByUsername(username);
		
		if(result) {
			List<Book> list = bookService.findAll();
			model.addAttribute("booklist",list);
			model.addAttribute("username",username);
			session.setAttribute("user", username);
			session.setAttribute("userID", userID);
			return "main";
		}else {
			model.addAttribute("errormsg", "登录失败");
			return "fail";
		}
	}
	/*
	 * @Description 前台用户登出
	 * @Author zyp
	 */
	@RequestMapping("/userlogout")
	public String userLogout(HttpSession session,Model model) {
		if (session.getAttribute("user") != null) {
			session.invalidate();
			return "logoutSuccess";
		}else {
			return "main";
		}
	}
}
