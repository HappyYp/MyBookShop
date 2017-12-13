package com.zyp.action;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyp.bean.Users;
import com.zyp.service.PersonService;

@Controller
public class ShowUsersAction {
	@Autowired
	private PersonService personService;
	/*
	 * @Description ��̨��ʾ�����û�
	 * @Author zyp
	 */
	@RequestMapping("/showUsers")
	public String showUsers(Model model,HttpSession session) {
		List<Users> userlist = personService.userlist();
		model.addAttribute("userlist",userlist);
		session.setAttribute("userlist", userlist);
		return "admin/userlist";
	}
}
