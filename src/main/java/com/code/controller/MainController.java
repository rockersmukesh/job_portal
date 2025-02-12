package com.code.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.code.model.JobSeeker;

import ch.qos.logback.core.model.Model;
import lombok.AllArgsConstructor;

@Controller
public class MainController {

	private JobSeeker jobSeeker;

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

//	@GetMapping("/register")
//	public String register(ModelAndView model) {
////		if(bindingResult.hasErrors()){
////			return "index";
////		}
////		JobSeeker jobSeeker = new JobSeeker();
//		ModelAndView modelAndView = new ModelAndView("register");
//		model.addObject("jobSeeker", jobSeeker);
//		return model;
//	}

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView model = new ModelAndView("register");
		JobSeeker jobSeeker = new JobSeeker();
		model.addObject("jobSeeker", jobSeeker);
		return model;
	}

	@PostMapping("/register/save")
	public ModelAndView saveRegister(@Valid JobSeeker jobSeeker, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("register");
			modelAndView.addObject("jobSeeker", jobSeeker);
			System.out.println(bindingResult.getAllErrors());
			return modelAndView;
		}
		// Save the jobSeeker object to the database
		// jobSeekerService.save(jobSeeker);
		System.out.println(jobSeeker.toString());
		return new ModelAndView("redirect:/success");
	}
	
	
}
