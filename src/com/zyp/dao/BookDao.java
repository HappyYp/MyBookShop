package com.zyp.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyp.bean.Book;
import com.zyp.bean.BookType;
import com.zyp.bean.OrderDetail;
import com.zyp.bean.Orders;
@Repository
public class BookDao {
	@Autowired
	private SessionFactory sessionFactory;
	/*
	 * @Description 倒序选择所有书籍
	 * @Author zyp
	 */
	public List<Book> select(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book order by id desc");
		List<Book> list = query.list();
		return list;
	}
	/*
	 * @Description 选择所有类型
	 * @Author zyp
	 */
	public List<BookType> selectType() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BookType");
		List<BookType> list = query.list();
		return list;
	}
	/*
	 * @Description 根据类型选择书籍
	 * @Author zyp
	 */
	public List<Book> selectByType(int type) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book b where b.bookType=? order by id desc");
		query.setInteger(0, type);
		List<Book> list = query.list();
		return list;
		
	}
	/*
	 * @Description 添加书籍
	 * @Author zyp
	 */
	public boolean insert(BookType bookType,Book book,@RequestParam String booktypename) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();	
		bookType.setName(booktypename);
		book.setBookType(bookType);
		session.save(bookType);
		session.save(book);
		return true;
	}
	/*
	 * @Description 添加已存在图书类型的书籍
	 * @Author zyp
	 */
	public boolean insertNoBookType(BookType bookType,Book book,@RequestParam String booktypename) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();	
		Query query= session.createSQLQuery("insert into book(bookname,author,publisher,price,description,imgurl,type_id) values(?,?,?,?,?,?,?)").addEntity("${book}",Book.class); 
		query.setString(0, book.getBookname());
		query.setString(1, book.getAuthor());
		query.setString(2, book.getPublisher());
		query.setDouble(3, book.getPrice());
		query.setString(4, book.getDescription());
		query.setString(5, book.getImgurl());
		query.setInteger(6, selectTypeIdByTypename(booktypename));
    	query.executeUpdate();
		return true;
	}
	/*
	 * @Description 根据类型名称选择类型ID
	 * @Author zyp
	 */
	public int selectTypeIdByTypename(String booktypename) {
		Session session = sessionFactory.getCurrentSession();	
		String hql = "from BookType where name = ?";
		Query query = session.createQuery(hql);
		query.setString(0, booktypename);
		BookType bType = (BookType) query.uniqueResult();
		int id = bType.getId();
		return id;
		
	}
	/*
	 * @Description 添加图书类型
	 * @Author zyp
	 */
	public boolean insertBookType(BookType bookType,@RequestParam String name) {
		Session session = sessionFactory.getCurrentSession();
		bookType.setName(name);
		session.save(bookType);
		return true;
		
	}
	/*
	 * @Description 单本删除书籍
	 * @Author zyp
	 */
	@SuppressWarnings("deprecation")
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Book where ID = ?");
		query.setInteger(0, id);
		int in = query.executeUpdate();
		return true;
	}
	/*
	 * @Description 批量删除书籍
	 * @Author zyp
	 */
	public boolean batchDelete(List<Integer> idList) {
		String hql = "";
		for (int i = 0; i < idList.size(); i++) {
		if(i==0) {
		hql = "id="+idList.get(i);
		} else {
		hql =hql + " or id="+idList.get(i);
		}
		Session session = sessionFactory.getCurrentSession();
		Query q= session.createQuery("delete from Book where "+hql);
		q.executeUpdate();
		}	
		return true;
		}
	/*
	 * @Description 修改书籍
	 * @Author zyp
	 */
	public boolean update(Book book,BookType bookType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Book B set B.bookname=?,B.author=?,B.publisher=?,B.price=?,B.description=?,B.imgurl=?,B.bookType=? where B.id=?";
		Query q = session.createQuery(hql);
		q.setString(0, book.getBookname());
		q.setString(1, book.getAuthor());
		q.setString(2, book.getPublisher());
		q.setDouble(3, book.getPrice());
		q.setString(4, book.getDescription());
		q.setString(5, book.getImgurl());
		q.setInteger(6, book.getBookType().getId());
		q.setInteger(7, book.getId());
		int result = q.executeUpdate();
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	/*
	 * @Description 根据图书名称选择书籍
	 * @Author zyp
	 */
	public List<Book> selectByName(@RequestParam String bookname) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book where BOOKNAME like '%"+bookname+"%'");
		List<Book> list = query.list();
		return list;
	}
	/*
	 * @Description 根据图书ID选择书籍
	 * @Author zyp
	 */
	public Book selectById(int bookId) {
		Session session = sessionFactory.getCurrentSession();	
		String hql = "from Book where id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, bookId);
		Book book = (Book) query.uniqueResult();
		return book;
	}
	/*
	 * @Description 根据图书ID选择价格
	 * @Author zyp
	 */
	public Double selectPrice(int bookId) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select price from Book where id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, bookId);
		Double pcice = (Double) query.uniqueResult();
		return pcice;
	}
	/*
	 * @Description 获取总记录数
	 * @Author zyp
	 */
	public int getAllCount() {
		Session session = sessionFactory.getCurrentSession();
		int count = ((Long)sessionFactory.openSession().createQuery("select count(*) from Book").iterate().next()).intValue();
		return count;
	}
	/*
	 * @Description 分页
	 * @Author zyp
	 */
	public List<Book> queryForPage(int offset, int length) {
		//查询所有的记录数
		  Session session = sessionFactory.getCurrentSession();
	      Query query= session.createSQLQuery("select book.* from Book order by ID desc").addEntity("${book.*}",Book.class);    
	      query.setFirstResult(offset);
	     
	      query.setMaxResults(length);   
	      List<Book> list = (List<Book>) query.list();
	      return list; 
	}
	/*
	 * @Description 选择销量最好的书籍
	 * @Author zyp
	 */
	public List<Book> selectBestSell(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "select book.*,count(o.BOOKID) as count from book left join orderdetailbook o on ID=o.BOOKID group by BOOKID order by count desc limit 3";
		Query query = session.createSQLQuery(hql).addEntity("${book.*}",Book.class);
		List<Book> list = (List<Book>) query.list();
		
		return list;
	}
	

}
