package com.code.controller;

import com.code.model.JobSeeker;
import com.code.service.JobSeekerService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private JobSeeker jobSeeker;
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@GetMapping("/dashboard")
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		modelAndView.addObject("jobSeeker", jobSeeker);
		return modelAndView;
	}
	
	
	@GetMapping("/jobseeker-list")
	public ModelAndView jobseekerList() {
		
		List<JobSeeker> jobSeekerList = jobSeekerService.findAllJobSeekers();
		ModelAndView modelAndView = new ModelAndView("admin/jobseeker");
		modelAndView.addObject("jobSeekers", jobSeekerList);
		return modelAndView;
	}
	
	@GetMapping("/delete/{jobSeekerId}")
	public String deleteJobseeker(@PathVariable int jobSeekerId,HttpSession session) {
		boolean result = jobSeekerService.deleteJobSeeker(jobSeekerId);
		
		if(result) {
			session.setAttribute("msg", "Student with rollno "+jobSeekerId+" deleted successfully");
		}	
		else {
			session.setAttribute("msg", "Student with rollno "+jobSeekerId+" not found");
		}
		return "redirect:/admin/jobseeker-list";
	}
	

}
