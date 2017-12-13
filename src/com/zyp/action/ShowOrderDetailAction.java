package com.zyp.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyp.bean.OrderDetail;
import com.zyp.service.OrdersService;

@Controller
public class ShowOrderDetailAction {
	@Autowired
	private OrdersService ordersService;
	/*
	 * @Description 后台显示订单详情
	 * @Author zyp
	 */
	@RequestMapping("/ShowOrderDetail")
	public String ShowOrderDetail(Model model,HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		String orderTime = ordersService.orderTime(orderId);
		OrderDetail orderDetail = ordersService.selectOrderDetail(Integer.parseInt(orderId));
		Double totalMoney = ordersService.totalMoney(orderId);
		
		model.addAttribute("orderDetail",orderDetail);
		model.addAttribute("userId",userId);
		model.addAttribute("orderId",orderId);
		model.addAttribute("orderTime",orderTime);
		model.addAttribute("totalMoney",totalMoney);
		return "admin/orderdetail";
	}
	/*
	 * @Description 后台搜索订单
	 * @Author zyp
	 */
	@RequestMapping("/searchOrder")
	public String SearchOrder(Model model,HttpServletRequest request) {
		String orderId = request.getParameter("oId");
		
		String orderTime = ordersService.orderTime(orderId);
		OrderDetail orderDetail = ordersService.selectOrderDetail(Integer.parseInt(orderId));
		Double totalMoney = ordersService.totalMoney(orderId);
		
		model.addAttribute("orderDetail",orderDetail);
		
		model.addAttribute("orderId",orderId);
		model.addAttribute("orderTime",orderTime);
		model.addAttribute("totalMoney",totalMoney);
		return "admin/searchorderdetail";
	}
}
