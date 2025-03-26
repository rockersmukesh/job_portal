package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.service.EmailService;
import com.code.service.JobSeekerService;

@Controller
@RequestMapping("/jobseeker")
public class ForgotPasswordController {
    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgotpassword")
    public String showForgotPasswordForm() {
        return "jobseeker/forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        String otp = jobSeekerService.generateOtp(email);
        if (otp == null) {
            model.addAttribute("error", "Email address not found.");
            return "jobseeker/forgotpassword";
        }

        emailService.sendOtpEmail(email, otp);
        model.addAttribute("email", email);
        return "jobseeker/verifyotp";
    }

    @GetMapping("/verifyotp")
    public String showVerifyOtpForm(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "jobseeker/verifyotp";
    }

    @PostMapping("/verifyotp")
    public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        boolean isValid = jobSeekerService.verifyOtp(email, otp);
        if (!isValid) {
            model.addAttribute("error", "Invalid OTP.");
            model.addAttribute("email", email);
            return "jobseeker/verifyotp";
        }

        String password = jobSeekerService.getPasswordByEmail(email);
        emailService.sendPasswordRetrievalEmail(email, password);
        model.addAttribute("message", "Your current password has been sent to your email.");
        return "jobseeker/forgotpassword";
    }
}