package com.zyp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zyp.bean.Book;
import com.zyp.bean.OrderDetail;
import com.zyp.bean.Orders;
import com.zyp.bean.Users;

@Repository
public class OrdersDao {
	@Autowired
	private SessionFactory sessionFactory;
	/*
	 * @Description 添加订单详情
	 * @Author zyp
	 */
	public boolean addOrderDetail(OrderDetail orderDetail,Set<Book> bookSet,int bookCount,Double totalMoney) {
		Session session = sessionFactory.getCurrentSession();
		orderDetail.setBookSet(bookSet);
		orderDetail.setCount(bookCount);	
		orderDetail.setTotalMoney(totalMoney);
		session.save(orderDetail);
		
		return true;
		
	}
	/*
	 * @Description 添加订单
	 * @Author zyp
	 */
	public boolean addOrders(Orders orders,OrderDetail orderDetail) {
		Session session = sessionFactory.getCurrentSession();
		orders.setOrderDetail(orderDetail);
		orders.setState("已支付");
		session.save(orders);
		return true;
		
	}
	/*
	 * @Description 根据详情ID选择订单详情
	 * @Author zyp
	 */
	public OrderDetail selectOrderDetail1(int detailID) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from OrderDetail where detailId=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, detailID);
		OrderDetail orderDetail = (OrderDetail) query.uniqueResult();
		return orderDetail;
	}
	/*
	 * @Description 链接查询用户列表
	 * @Author zyp
	 */
	public List<Users> usersList(){
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Users u left outer join fetch u.ordersSet o";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		
		
		return list;
	}
	/*
	 * @Description 链接查询订单列表
	 * @Author zyp
	 */
	public List<Orders> ordersList(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Orders o left outer join fetch o.users";
		Query query = session.createQuery(hql);
		List<Orders> list = query.list();
		return list;	
	}
	/*
	 * @Description 根据用户ID选择订单
	 * @Author zyp
	 */
	public List<List> oList1(List<Integer> userId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		List<List> oList = new ArrayList<>();
		for(int i = 0;i < userId.size();i++) {
			hql = "USERID=" + userId.get(i);
			Query q= session.createQuery("from Orders where "+hql);
			List<Orders> list = q.list();
			oList.add(list);
		}
		return oList;
	}
	/*
	 * @Description 根据订单ID选择订单详情
	 * @Author zyp
	 */
	public OrderDetail selectOrderDetail(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from OrderDetail where detailId=?";
		String hql2 = "from Orders where orderId=?";
		Query query = session.createQuery(hql2);
		query.setInteger(0, orderId);
		Orders orders = (Orders) query.uniqueResult();
		int odId = orders.getOrderDetail().getDetailId();
		Query query2 = session.createQuery(hql);
		query2.setInteger(0, odId);
		OrderDetail oDetail = (OrderDetail) query2.uniqueResult();
		return oDetail;
	}
	/*
	 * @Description 下单时间
	 * @Author zyp
	 */
	public String orderTime(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Orders where orderId=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, Integer.parseInt(orderId));
		Orders orders = (Orders) query.uniqueResult();
		String orderTime = orders.getTime();
		return orderTime;
	}
}
