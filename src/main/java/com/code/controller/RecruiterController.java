package com.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.code.model.Recruiter;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {
	private Recruiter recruiter;
	
	@GetMapping("/register")
	public ModelAndView recruiter() {
		ModelAndView modelAndView = new ModelAndView("recruiter/register");
		modelAndView.addObject("recruiter", recruiter);
		return modelAndView;
	}
	
}
