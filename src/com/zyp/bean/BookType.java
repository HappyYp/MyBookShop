package com.zyp.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
@Entity
public class BookType {
	private int id;
	private String name;
	private Set<Book> bookSet = new HashSet<Book>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Book> getBookSet() {
		return bookSet;
	}
	public void setBookSet(Set<Book> bookSet) {
		this.bookSet = bookSet;
	}
	
}
