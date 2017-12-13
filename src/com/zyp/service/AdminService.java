package com.zyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyp.dao.AdminDao;

@Service
@Transactional
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	public boolean login(String username,String password) {
		boolean in = adminDao.login(username, password);
		return in;
	}
}
