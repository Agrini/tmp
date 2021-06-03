package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertBook(Book book) {
		String sql="insert into books(book_name,author_name,category,admin_mail_id,total_books) values(?,?,?,?,?);";
		jdbcTemplate.update(sql, book.getBookName(),book.getAuthorName(),book.getCategory(),book.getAdminMailId(),book.getTotalBooks());		
	}

	
}
