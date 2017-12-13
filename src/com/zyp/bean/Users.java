package com.zyp.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Users {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String tel;
	private String address;
	private String posttime;
	private Set<Orders> ordersSet = new HashSet<Orders>();	//与Orders是一对多关系
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosttime() {
		return posttime;
	}
	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}
	public Set<Orders> getOrdersSet() {
		return ordersSet;
	}
	public void setOrdersSet(Set<Orders> ordersSet) {
		this.ordersSet = ordersSet;
	}
	
}
