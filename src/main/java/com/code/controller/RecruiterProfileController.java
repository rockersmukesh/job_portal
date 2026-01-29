package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.code.model.Recruiter;
import com.code.service.RecruiterService;

@Controller
@RequestMapping("/recruiter")
public class RecruiterProfileController {
    
    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("/profile")
    public String showRecruiterProfile(Model model) {
        Recruiter recruiter = recruiterService.getCurrentRecruiter();
        if (recruiter == null) {
            return "redirect:/recruiter/login"; // Redirect to login if recruiter is not found
        }
        model.addAttribute("recruiter", recruiter);
        return "recruiter/profile";
    }

    




}
