package com.zyp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyp.bean.Users;
import com.zyp.dao.PersonDao;
@Repository
public class PersonService {
	@Autowired
	private PersonDao personDao;
	public boolean addUser(Users user) {
		return personDao.addUser(user);
	}
	public boolean findUser(@RequestParam String username,@RequestParam String password) {
		return personDao.checkUser(username,password);
	}
	public boolean checkUserIn(@RequestParam String username) {
		return personDao.checkUserIn(username);
	}
	public int selectIdByUsername(@RequestParam String username) {
		return personDao.selectIdByUsername(username);
	}
	public List<Users> userlist(){
		return personDao.userlist();
	}
	public List<Integer> userIdlist(){
		return personDao.userIdlist();
	}
}
