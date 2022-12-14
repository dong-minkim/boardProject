package com.spring.boardapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CommonController {

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		System.out.println("접근 거부: " + auth);

		model.addAttribute("msg", "접근 거부");
	}

	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		System.out.println("error: " + error);
		System.out.println("logout: " + logout);

		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
	}

	@GetMapping("/customLogout")
	public void logoutGET() {
		System.out.println("custom logout");
	}

	@PostMapping("/customLogout")
	public String logoutPost() {
		System.out.println("post custom logout");
		return "redirect:/board/list";
	}

}
