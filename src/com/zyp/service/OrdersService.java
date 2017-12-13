package com.zyp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyp.bean.Book;
import com.zyp.bean.OrderDetail;
import com.zyp.bean.Orders;
import com.zyp.bean.Users;
import com.zyp.dao.BookDao;
import com.zyp.dao.OrdersDao;

@Service
@Transactional
public class OrdersService {
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private BookDao bookDao;
	public boolean addOrderDetail(OrderDetail orderDetail,Set<Book> bSet,int bookCount,Double totalMoney) {
		return ordersDao.addOrderDetail(orderDetail, bSet, bookCount,totalMoney);
	}
	public boolean addOrders(Orders orders,OrderDetail orderDetail) {
		return ordersDao.addOrders(orders, orderDetail);
	}
	public OrderDetail selectOrderDetail1(int detailID) {
		return ordersDao.selectOrderDetail1(detailID);
	}
	public List<Users> userslist(){
		return ordersDao.usersList();
	}
	public List<Orders> orderslist(){
		return ordersDao.ordersList();
	}
	/*public List<Orders> oList(List<Integer> userId){
		return ordersDao.oList(userId);
	}*/
	public List<List> oList1(List<Integer> userId){
		return ordersDao.oList1(userId);
	}
	public OrderDetail selectOrderDetail(int orderId) {
		return ordersDao.selectOrderDetail(orderId);
	}
	public String orderTime(String orderId) {
		return ordersDao.orderTime(orderId);
	}
	public Double totalMoney(String orderId) {
		OrderDetail oDetail = ordersDao.selectOrderDetail(Integer.parseInt(orderId));
		double totalMoney = oDetail.getTotalMoney();
		return totalMoney;
		
	}
}

