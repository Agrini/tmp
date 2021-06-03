package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Admin;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.ExceptionController;
import com.example.demo.exception.MailIdAndPasswordMismatch;
import com.example.demo.exception.NoSuchMailIdFoundException;
import com.example.demo.service.AdminService;

@Controller
@SessionAttributes("admin")
public class AdminController {

	@Autowired
	private AdminService adminService; 
	
	
	@GetMapping("/admin")
	public String viewAdminHomePage(Model model) {
		model.addAttribute("admin",new Admin());
		model.addAttribute("mailId",new String());
		model.addAttribute("password",new String());
		return "AdminSidePages/admin_entry_page";
	}
	
	@PostMapping("/process_admin_registration")
	public String processRegistration(@ModelAttribute("admin")Admin admin, RedirectAttributes redirect) throws EmailAlreadyExistsException {
		Boolean result=adminService.checkAndAddAdmin(admin);
		if(result) {
			redirect.addFlashAttribute("registration_successful", "Successfully Registered. Now Log In !!");
			return "redirect:/admin";
		}
		return "error";
	}
	
	@PostMapping("/process_admin_login")
	public String processLogin(@RequestParam(value="mailId",required=true) String mailId,@RequestParam(value="password",required=true) String password, Model model) throws NoSuchMailIdFoundException, MailIdAndPasswordMismatch {
		
		model.addAttribute("admin", adminService.checkAndGetAdmin(mailId, password));
		return "AdminSidePages/admin_dashboard";
	}

	
	@ExceptionHandler(value=EmailAlreadyExistsException.class)
	public String handleEmailAlreadyExistsException(HttpServletRequest request,EmailAlreadyExistsException ex, RedirectAttributes redirect) {
		
		String message="This Email-Id is already registered!! Try Logging In.";
		redirect.addFlashAttribute("registration_error", message);
		return "redirect:/admin";
	}
	
	@ExceptionHandler(value=NoSuchMailIdFoundException.class)
	public String handleNoSuchMailIdFoundException(HttpServletRequest request,NoSuchMailIdFoundException ex, RedirectAttributes redirect) {
	
		String message="Entered Email-Id not found. Try Registering.";
		redirect.addFlashAttribute("login_error", message);
		return "redirect:/admin";
	}
	
	@ExceptionHandler(value=MailIdAndPasswordMismatch.class)
	public String handleMailIdAndPasswordMismatch(HttpServletRequest request,MailIdAndPasswordMismatch ex, RedirectAttributes redirect) {
		
		String message="Email-Id and Password didn't match. Try Logging in again.";
		redirect.addFlashAttribute("login_error", message);
		return "redirect:/admin";
	}
}
