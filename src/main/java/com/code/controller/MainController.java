package com.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.code.model.JobSeeker;

import ch.qos.logback.core.model.Model;

@Controller
public class MainController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		JobSeeker jobSeeker = new JobSeeker();
		// model.addAttribute("jobSeeker", jobSeeker);
		return "register";
	}
}
