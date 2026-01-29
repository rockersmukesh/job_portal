package com.code.controller;

import com.code.model.Recruiter;
import com.code.service.RecruiterService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.code.model.Recruiter;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {
	
	private Recruiter recruiter;

	@Autowired
	private RecruiterService recruiterService;

	@GetMapping("/register")
	public String showRegistrationForm(Model model, Recruiter recruiter) {
		model.addAttribute("recruiter", recruiter);
		return "recruiter/register";
	}
	
	@PostMapping("/register")
    public ModelAndView registerRecruiter(@Valid Recruiter recruiter, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("recruiter/register");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("recruiter", recruiter);
            return modelAndView;
        }

        if (recruiterService.emailExists(recruiter.getEmailId())) {
            modelAndView.addObject("error", "Email already exists");
            modelAndView.addObject("recruiter", recruiter);
            return modelAndView;
        }

        recruiterService.saveRecruiter(recruiter);
        return new ModelAndView("redirect:/recruiter/dashboard");
    }

	@GetMapping("/login")
    public String showLoginForm() {
        return "recruiter/login";
    }

    @PostMapping("/login")
    public ModelAndView loginRecruiter(@RequestParam String email, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView();
        Recruiter recruiter = recruiterService.findByEmail(email);

        if (recruiter == null || !recruiter.getPassword().equals(password)) {
            modelAndView.setViewName("recruiter/login");
            modelAndView.addObject("error", "Invalid email or password");
            return modelAndView;
        }

		recruiterService.setCurrentRecruiter(recruiter);
        modelAndView.setViewName("redirect:/recruiter/dashboard");
        return modelAndView;
    }

	@GetMapping("/dashboard")
	public String dashboard() {
		return "recruiter/dashboard";
	}

    // @GetMapping()
    
}
