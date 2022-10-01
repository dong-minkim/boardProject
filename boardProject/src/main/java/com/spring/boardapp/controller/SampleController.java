package com.spring.boardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sample/*")
@Controller
public class SampleController {
	
	@GetMapping("/all")
	public void doAll() {
		System.out.println("All can access");
	}
	
	@GetMapping("/member")
	public void doMember() {
		System.out.println("Member can access");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		System.out.println("Admin can access");
	}
}
