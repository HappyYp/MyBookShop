package com.zyp.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyp.bean.Orders;
import com.zyp.bean.Users;
import com.zyp.dao.OrdersDao;
import com.zyp.service.OrdersService;
import com.zyp.service.PersonService;

@Controller
public class ShowOrdersAction {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private PersonService personService;
	/*
	 * @Description 后台显示所有订单
	 * @Author zyp
	 */
	@RequestMapping("/showOrders")
	public String showOrders(Model model, HttpSession session) {
		
		List<Integer> userIdlist = personService.userIdlist();
		List<List> oList = ordersService.oList1(userIdlist);
		model.addAttribute("uList",userIdlist);
		model.addAttribute("oList",oList);
		return "admin/orderslist";
	}
}
