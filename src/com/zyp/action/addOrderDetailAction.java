package com.zyp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.jpa.criteria.expression.function.AggregationFunction.COUNT;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction.SUM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;
import com.zyp.bean.Book;
import com.zyp.bean.OrderDetail;
import com.zyp.bean.Orders;
import com.zyp.service.BookService;
import com.zyp.service.OrdersService;

import sun.tools.jar.resources.jar;

@Controller
public class addOrderDetailAction {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private BookService bookService;
	/*
	 * @Description ÃÌº”∂©µ•œÍ«È
	 * @Author zyp
	 */
	@RequestMapping("/addOrderDetail")
	public String addOrderDetail(HttpSession session,Model model,Book book,OrderDetail orderDetail,@RequestParam String[] bookCount,HttpServletRequest request) {
		List<Integer> bookIds = (List<Integer>) session.getAttribute("bookIds");
		Set<Book> bSet = bookService.searchBookByIds(bookIds);
		Iterator iterator = bSet.iterator();
		while(iterator.hasNext()) {
			System.out.println("bSet...........,,,,,"+iterator.next());
		}
		int count = 0;
		for(int i = 0;i < bookCount.length;i++) {
			count += Integer.parseInt(bookCount[i]);
		}
		List<Double> singleBookTotalMoney = new ArrayList<>();
		for(int j = 0;j < bookCount.length;j++) {
			singleBookTotalMoney.add((Integer.parseInt(bookCount[j]))*bookService.searchPrice(bookIds.get(j)));
		}
		Double totalMoney = 0.00;
		for(int k = 0;k < singleBookTotalMoney.size();k++) {
			totalMoney += singleBookTotalMoney.get(k);
		}
		boolean result = ordersService.addOrderDetail(orderDetail, bSet,count,totalMoney);
		int detailId = orderDetail.getDetailId();
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");  	
		model.addAttribute("bookCount",bookCount);
		model.addAttribute("totalMoney",df.format(totalMoney));
		model.addAttribute("singleBookTotalMoney",singleBookTotalMoney);
		session.setAttribute("detailId", detailId);
		return "addOrderDetail";
	}
}
