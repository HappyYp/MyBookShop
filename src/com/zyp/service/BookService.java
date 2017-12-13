package com.zyp.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.regexp.internal.recompile;
import com.zyp.bean.Book;
import com.zyp.bean.BookType;
import com.zyp.bean.Page;
import com.zyp.dao.BookDao;
@Service
@Transactional
public class BookService {
	
		@Autowired
		private BookDao bookDao;
		
		 public List<Book> findAll(){
			 List<Book> list = bookDao.select();
			 return list;
		 }
		
		public boolean add(BookType bookType,Book book,@RequestParam String booktypename) {
			// TODO Auto-generated method stub
			String bimg = "book_images/"+book.getImgurl();
			book.setImgurl(bimg);
			List<BookType> bTypes = bookDao.selectType();
			Iterator iterator = bTypes.iterator();
			while(iterator.hasNext()) {
				BookType bookType2 = (BookType) iterator.next();
				//System.out.println(bookType2.getName()+"typename"+booktypename);
				if(bookType2.getName().equals(booktypename)) {
					//System.out.println("no booktype.........");
					return bookDao.insertNoBookType(bookType, book, booktypename);
				}
			}
			//System.out.println("booktype............");
			return bookDao.insert(bookType,book,booktypename);
			
		}
		public boolean delete(int id) {
			// TODO Auto-generated method stub
			
			return bookDao.delete(id);
		}
		public boolean batchRemove(List<Integer> idList) {
			return bookDao.batchDelete(idList);
		}
		public boolean update(Book book,BookType bookType) {
			String bimg = "book_images/"+book.getImgurl();
			book.setImgurl(bimg);
			return bookDao.update(book,bookType);
		}
		public List<Book> searchBook(@RequestParam String bookname) {
			return bookDao.selectByName(bookname);
		}
		public Book searchBookById(int bookId) {
			return bookDao.selectById(bookId);
		}
		public Set<Book> searchBookByIds(List<Integer> bookIds){
			Set<Book> bSet = new HashSet<>();
			for(int i = 0;i < bookIds.size();i++) {
				bSet.add(bookDao.selectById(bookIds.get(i)));
			}
			return bSet;
			
		}
		public List<Book> searchByType(int type) {
			return bookDao.selectByType(type);
		}
		public List<BookType> searchType() {
			return bookDao.selectType();
		}
		public Double searchPrice(int bookId) {
			return bookDao.selectPrice(bookId);
		}
		public boolean insertBookType(BookType bookType,@RequestParam String name) {
			return bookDao.insertBookType(bookType, name);
		}
		public Page queryForPage(int currentPage,int pageSize) {
			Page page = new Page();
			int allRow = bookDao.getAllCount();
			int offset = page.countOffset(currentPage, pageSize);
			List<Book> list = bookDao.queryForPage(offset, pageSize);
			
			page.setPageNo(currentPage);
			page.setPageSize(pageSize);
			page.setTotalRecords(allRow);
			page.setBlist(list);
			return page;
			
		}
		 public List<Book> findBestSell(){
			 List<Book> book = bookDao.selectBestSell();
		
			 return book;
		 }
	}
