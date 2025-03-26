package com.code.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.code.model.JobSeeker;
import com.code.service.JobSeekerService;

import java.util.Optional;

@Controller
public class MainController {

	
	private JobSeeker jobSeeker;

	@Autowired
	private JobSeekerService jobSeekerService;
	
//	public MainController(JobSeekerService JobSeekerService) {
//		this.JobSeekerService = JobSeekerService;
//	}

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

	 @PostMapping("/login")
    public ModelAndView loginJobSeeker(@RequestParam String email, @RequestParam String password, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<JobSeeker> jobSeeker = jobSeekerService.findByEmail(email);

        if (jobSeeker.isEmpty() || !jobSeeker.get().getPassword().equals(password)) {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "Invalid email or password");
            return modelAndView;
        }

        // Store job seeker in session
        session.setAttribute("jobSeeker", jobSeeker.get());
        modelAndView.setViewName("redirect:/jobseeker/dashboard"); // Redirect to dashboard
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
	public ModelAndView saveRegister(@Valid @ModelAttribute("jobSeeker") JobSeeker jobSeeker, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("register");
			modelAndView.addObject("jobSeeker", jobSeeker);
			System.out.println(bindingResult.getAllErrors());
			return modelAndView;
		}

		Optional<JobSeeker> existingJobSeeker = jobSeekerService.findByEmail(jobSeeker.getEmailId());
		if(existingJobSeeker.isPresent()) {
			ModelAndView modelAndView = new ModelAndView("register");
			modelAndView.addObject("error", "Email already exists");
			modelAndView.addObject("jobSeeker", jobSeeker);

			return modelAndView;
		}
		// Save the jobSeeker object to the database
		 jobSeekerService.save(jobSeeker);
		System.out.println(jobSeeker.toString());
		return new ModelAndView("redirect:/jobseeker/dashboard");
	}

	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/"; // Redirect to login page
    }
	
	// @PostMapping("/login")
	// public ModelAndView loginJobSeeker(@RequestParam String email, @RequestParam String password) {
	//     ModelAndView modelAndView = new ModelAndView();
	//     Optional<JobSeeker> jobSeeker = jobSeekerService.findByEmail(email);

	//     if (jobSeeker.isEmpty() || !jobSeeker.get().getPassword().equals(password)) {
	//         modelAndView.setViewName("login");
	//         modelAndView.addObject("error", "Invalid email or password");
	//         return modelAndView;
	//     }

	//     modelAndView.setViewName("redirect:/jobseeker/dashboard"); // Redirect to dashboard
	//     return modelAndView;
	// }

	// @GetMapping("/logout")
	// public String logout(HttpServletRequest request, HttpServletResponse response) {

	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	// 	if (authentication != null) {
	// 		new SecurityContextLogoutHandler().logout(request, response, authentication);
	// 	}

	// 	return "redirect:/";
	// }
	
	
}
