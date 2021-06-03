package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@Controller
@SessionAttributes("admin")
public class BooksController {

	@Autowired
	BookService bookService;
	
	@ModelAttribute("admin")
	public Admin setUpSessionAttributeAdmin() {
		return new Admin();
	}
	
	@GetMapping("/admin/manage_books")
	public String viewManageBooksPage(@SessionAttribute("admin") Admin admin, Model model) {
		
		model.addAttribute("book", new Book());
		return "AdminSidePages/admin_books_options";
	}
	
	@PostMapping("/admin/process_adding_book")
	public String processAdditionOfBook(@ModelAttribute("book") Book book, @SessionAttribute("admin") Admin admin, RedirectAttributes redirect) {
		
		book.setAdminMailId(admin.getAdminMailId());
		bookService.addBook(book);
		
		String message="Book: "+book.getBookName()+" | Author: "+book.getAuthorName()+" added to Library !!";
		redirect.addFlashAttribute("book_added", message);
		return "redirect:/admin/manage_books";
	}
	
	@PostMapping("/admin/process_searching_book_by_name")
	public String searchBooksByName(@RequestParam("searchBookName") String bookName, @SessionAttribute("admin") Admin admin, RedirectAttributes redirect) {
		ArrayList<String> bookList=bookService.searchBooksByName(bookName);
		
		return "redirect:/admin/manage_books";
	}
}
