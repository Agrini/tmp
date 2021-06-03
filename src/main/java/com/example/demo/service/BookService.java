package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookDao;

@Service
public class BookService {

	@Autowired
	BookDao bookDao;
	
	public void addBook(Book book) {
		bookDao.insertBook(book);
	}

	public ArrayList<String> searchBooksByName(String bookName) {
		ArrayList<String> bookList=new ArrayList<>();
		return bookList;
	}

}
