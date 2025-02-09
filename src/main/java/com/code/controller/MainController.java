package com.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.code.model.JobSeeker;

import ch.qos.logback.core.model.Model;
import lombok.AllArgsConstructor;

@Controller
public class MainController {
	
	private JobSeeker jobSeeker;
	
	public MainController(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/contactus")
	public String contactus() {
		return "contactus";
	}
	
	@GetMapping("/job")
	public String job() {
		return "job";
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("jobseeker",jobSeeker);
		return modelAndView;
	}


	@GetMapping("/register")
	public String register(Model model) {
		JobSeeker jobSeeker = new JobSeeker();
		// model.addAttribute("jobSeeker", jobSeeker);
		return "register";
	}
	
	
}
