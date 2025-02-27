package com.code.controller;

import com.code.model.JobSeeker;
import com.code.service.JobSeekerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private JobSeeker jobSeeker;
	
	private JobSeekerService jobSeekerService;
	
	@GetMapping("/dashboard")
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		modelAndView.addObject("jobseeker", jobSeeker);
		return modelAndView;
	}
	
	
	@GetMapping("/jobseeker-list")
	public ModelAndView jobseekerList() {
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		modelAndView.addObject("jobseekers", jobSeekerService.findAllJobSeekers());
		return modelAndView;
	}
	

}
