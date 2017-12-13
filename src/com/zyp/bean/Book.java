package com.zyp.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.scenario.effect.Flood;

@Entity
public class Book {
	private int id;
	private String bookname;
	private String author;
	private String publisher;
	private Double price;
	private String description;
	private String imgurl;
	private BookType bookType;	//与BookType是一对多关系
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();	//与OrderDetail是多对多关系
	
	public Book() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	/*
	 * @Description 重写equals()方法，后面的购物车就可以remove了
	 * @Author zyp
	 */
	@Override
	public boolean equals(Object obj){  
		if(!(obj instanceof Book)){  
		            return false;  
		        } 
		        Book b =(Book)obj;    
		return this.id == b.id; 
	}
	/*
	 * @Description 重写hashCode()方法，后面的购物车就可以remove了
	 * @Author zyp
	 */
	 @Override
    public int hashCode() {
        return id;
    }
}
