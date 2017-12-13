package com.zyp.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zyp.bean.Users;
import com.zyp.service.PersonService;

@Controller
public class RegisterAction {
	@Autowired
	private PersonService personService;
	/*
	 * @Description 前台用户注册
	 * @Author zyp
	 */
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public String register(Users user,Model model) throws ParseException {
		Date date = new Date();
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowTime = sf.format(date);
		user.setPosttime(nowTime);
		String username = user.getUsername();
		if(!username.equals("")) {
			boolean result2 = personService.checkUserIn(username);
			//boolean result = personService.addUser(user);
			if(/*result&&*/result2) { 
				boolean result = personService.addUser(user);
				model.addAttribute("user",user);
				return "registSuccess";
			}else {
				model.addAttribute("errormsg","用户名已存在");
				return "registFail";
			}
		}else {
			model.addAttribute("errormsg","用户名为空");
			return "registFail";
		}
	}
}
