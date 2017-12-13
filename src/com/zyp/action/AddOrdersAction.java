package com.zyp.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyp.bean.OrderDetail;
import com.zyp.bean.Orders;
import com.zyp.bean.Users;
import com.zyp.service.BookService;
import com.zyp.service.OrdersService;

@Controller
public class AddOrdersAction {
	@Autowired
	private BookService bookService;
	@Autowired
	private OrdersService ordersService;
	/*
	 * @Description Ìí¼Ó¶©µ¥
	 * @Author zyp
	 */
	@RequestMapping("/addOrders")
	public String addOrders(Users users,HttpSession session,Model model,Orders orders,OrderDetail orderDetail) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowTime = sf.format(date);
		orders.setTime(nowTime);
		int userID = (int) session.getAttribute("userID");
		users.setUserId(userID);
		orders.setUsers(users);
		int detailID = (int) session.getAttribute("detailId");
		boolean result = ordersService.addOrders(orders, ordersService.selectOrderDetail1(detailID));
		users.getOrdersSet().add(orders);
		
		return "addOrders";
		
	}
}
