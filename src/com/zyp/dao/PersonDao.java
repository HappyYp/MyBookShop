package com.zyp.dao;

import java.util.List;

import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyp.bean.Book;
import com.zyp.bean.Users;
@Repository
@Transactional
public class PersonDao {
	@Autowired
	private SessionFactory sessionFactory;
	/*
	 * @Description ����û�
	 * @Author zyp
	 */
	public boolean addUser(Users user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return true;	
	}
	/*
	 * @Description �û���¼����
	 * @Author zyp
	 */
	public boolean checkUser(@RequestParam String username,@RequestParam String password){
		Session session = sessionFactory.getCurrentSession();	
		String hql = "from Users as user where user.username = ? and user.password = ?";
		Query query = session.createQuery(hql).setString(0, username).setString(1, password);	
		List<Users> userlist = query.list();	
		boolean in = userlist.isEmpty();
		if (!in) {
			return true;
		}else {
			return false;
		}	
	}
	/*
	 * @Description �����û����Ƿ���ע��
	 * @Author zyp
	 */
	public boolean checkUserIn(@RequestParam String username){
		Session session = sessionFactory.getCurrentSession();	
		String hql = "from Users as user where user.username = ?";
		Query query = session.createQuery(hql).setString(0, username);	
		List<Users> userlist = query.list();	
		boolean in = userlist.isEmpty();
		if (in) {
			return true;
		}else {
			return false;
		}	
	}
	/*
	 * @Description �����û���ѡ���û�ID
	 * @Author zyp
	 */
	public int selectIdByUsername(@RequestParam String username) {
		Session session = sessionFactory.getCurrentSession();
		
		boolean i = username.equals("");
		
		if(!i) {
			String hql = "select userId from Users where username=?";
			Query query = session.createQuery(hql).setString(0, username);
			int userID = (int) query.uniqueResult();
			return userID;
		}else {
			return 0;
		}
		
		
	}
	/*
	 * @Description �û��б�
	 * @Author zyp
	 */
	public List<Users> userlist(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Users");
		List<Users> list = query.list();
		return list;
	}
	/*
	 * @Description �û�ID�б�
	 * @Author zyp
	 */
	public List<Integer> userIdlist(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select userId from Users");
		List<Integer> list = query.list();
		return list;
	}
}
