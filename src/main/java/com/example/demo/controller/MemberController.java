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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.ExceptionController;
import com.example.demo.exception.MailIdAndPasswordMismatch;
import com.example.demo.exception.NoSuchMailIdFoundException;
import com.example.demo.service.MemberService;

@Controller
@SessionAttributes({"admin","member"})
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	private Log logger=LogFactory.getLog(ExceptionController.class);
	
	@ModelAttribute("admin")
	public Admin setUpSessionAttributeAdmin() {
		return new Admin();
	}
	
	@ModelAttribute("member")
	public Member setUpSessionAttributeMember() {
		return new Member();
	}
	
	@GetMapping("/admin/manage_members")
	public String showAdminSideMembersOptions(@SessionAttribute("admin") Admin admin,Model model) {

		return "AdminSidePages/admin_member_options";
	}
	
	@GetMapping("/member")
	public String displayMemberLogInPage(Model model) {
		model.addAttribute("memberMailId", new String());
		model.addAttribute("password", new String());
		return "MemberSidePages/member_entry_page";
	}
	
	@PostMapping("/process_member_login")
	public ModelAndView processMemberLogin(@RequestParam("memberMailId") String memberMailId, 
									@RequestParam("password") String password,
									RedirectAttributes redirect) throws NoSuchMailIdFoundException, MailIdAndPasswordMismatch {
		ModelAndView mv=new ModelAndView();
		
		Member member=memberService.checkAndGetMember(memberMailId, password);
		mv.addObject("member", member);
		mv.setViewName("MemberSidePages/member_dashboard");
		return mv;
	}
	
	@PostMapping("/admin/process_member_registration")
	public String processMemberRegistration(@RequestParam(name="memberMailId") String memberMailId,
											@RequestParam(name="memberName") String memberName,
											@RequestParam(name="mpassword") String mpassword,
											@RequestParam(name="phoneNumber") long phoneNumber,
											@SessionAttribute("admin") Admin admin,
											RedirectAttributes redirect) {
		Member member=new Member();
		member.setMemberMailId(memberMailId);
		member.setMemberName(memberName);
		member.setMpassword(mpassword);
		member.setPhoneNumber(phoneNumber);
		member.setAdminMailId(admin.getAdminMailId());
		
		System.out.println(member.getMemberMailId());
		
		Boolean result=memberService.checkAndAdd(member);
		
		
		String message;
		if(result) {
			message="Member Added Successfully !!";
			redirect.addFlashAttribute("success", message);
		}else {
			message= "Member Email-Id already exists !!";
			redirect.addFlashAttribute("error", message);
		}

		return "redirect:/admin/manage_members";
	}
	
	@PostMapping("/admin/process_removing_member")
	public String removingMember(@RequestParam("memberMailId") String memberMailId, 
								@SessionAttribute("admin") Admin admin,
								RedirectAttributes redirect) throws NoSuchMailIdFoundException {
		Boolean result=memberService.checkAndRemoveByMailId(memberMailId);
		if(result) {
			String removal_successful="Member with Email-Id: "+memberMailId+" removed successfully !!";
			redirect.addFlashAttribute("removal_successful",removal_successful);
		}
		return "redirect:/admin/manage_members";
	}
	
	@ExceptionHandler(value=NoSuchMailIdFoundException.class)
	public String handleNoSuchMailIdExists(HttpServletRequest request,NoSuchMailIdFoundException ex, RedirectAttributes redirect) {
		logger.error("Request :"+request.getRequestURL()+" threw an exception.",ex);
		
		String message="The entered email-Id could not be found !!";
		redirect.addFlashAttribute("mail_id_not_found", message);
		return "redirect:/member";
	}
	
	@ExceptionHandler(value=MailIdAndPasswordMismatch.class)
	public String handleMailIdAndPasswordMismatch(HttpServletRequest request,MailIdAndPasswordMismatch ex, RedirectAttributes redirect) {
		logger.error("Request :"+request.getRequestURL()+" threw an exception.",ex);
		
		String message="The entered email-Id and password do not match!!";
		redirect.addFlashAttribute("mailid_password_mismatch", message);
		return "redirect:/member";
	}
	
}
