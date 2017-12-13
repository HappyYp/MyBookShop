package com.zyp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyp.bean.Admin;
/*
 * @Description π‹¿Ì‘±µ«¬º
 * @Author zyp
 */
@Repository
public class AdminDao {
	@Autowired
	private SessionFactory sessionFactory;
	public boolean login(@RequestParam String username,@RequestParam String password) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Admin as a where a.username = ? and a.password = ?";
		Query query = session.createQuery(hql).setString(0, username).setString(1, password);	
		List<Admin> alist = query.list();
		boolean in = alist.isEmpty();
		
		if (!in) {
			return true;
		}else {
			return false;
		}	
	}
}
