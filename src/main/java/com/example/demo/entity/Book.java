package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
//	create table books(
//			book_id int auto_increment primary key,
//            book_name varchar(60),
//            author_name varchar(60),
//            category varchar(60),
//            admin_mail_id varchar(30),
//            total_books int,
//            available_books int default (total_books),
//            foreign key (admin_mail_id) references admins(admin_mail_id)
//            );
	
	@Id
	@Column(name="book_id")
	private int bookId;
	
	@Column(name="book_name", length=60)
	private String bookName;
	
	@Column(name="author_name", length=60)
	private String authorName;
	
	@Column(name="category", length=60)
	private String category;
	
	@Column(name="admin_mail_id", length=30)
	private String adminMailId;
	
	@Column(name="total_books")
	private int totalBooks;
	
	@Column(name="available_books")
	private int availableBooks;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAdminMailId() {
		return adminMailId;
	}

	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}

	public int getTotalBooks() {
		return totalBooks;
	}

	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}

	public int getAvailableBooks() {
		return availableBooks;
	}

	public void setAvailableBooks(int availableBooks) {
		this.availableBooks = availableBooks;
	}
	
	
	
	
}
