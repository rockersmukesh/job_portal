package com.code.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.model.Job;
import com.code.model.JobSeeker;
import com.code.model.JobSeekerDetails;
import com.code.service.JobSeekerDetailsService;
import com.code.service.JobSeekerService;
import com.code.service.JobService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/jobseeker")
public class JobSeekerController {
   
    // @Autowired
    // private JobSeekerService jobSeekerService;

    @Autowired
    private JobSeekerDetailsService jobSeekerDetailsService;

    // /**
    //  * Show login form
    //  */
    // @GetMapping("/login")
    // public String showLoginForm() {
    //     return "login";
    // }

    // /**
    //  * Handle login
    //  */
    // @PostMapping("/login")
    // public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
    //     JobSeeker jobSeeker = jobSeekerService.findByEmail(email).orElse(null);

    //     if (jobSeeker == null || !jobSeeker.getPassword().equals(password)) {
    //         model.addAttribute("error", "Invalid email or password");
    //         return "login";
    //     }

    //     // Store job seeker in session
    //     session.setAttribute("jobSeeker", jobSeeker);
    //     return "redirect:/jobseeker/dashboard";
    // }

    // /**
    //  * Show dashboard
    //  */
    // @GetMapping("/dashboard")
    // public String dashboard(HttpSession session, Model model) {
    //     JobSeeker jobSeeker = (JobSeeker) session.getAttribute("jobSeeker");

    //     if (jobSeeker == null) {
    //         return "redirect:/jobseeker/login";
    //     }

    //     model.addAttribute("jobSeeker", jobSeeker);
    //     return "jobseeker/dashboard";
    // }

    // /**
    //  * Logout
    //  */
    // @GetMapping("/logout")
    // public String logout(HttpSession session) {
    //     session.invalidate();
    //     return "redirect:/jobseeker/login";
    // }
   
   @GetMapping("/dashboard")
    public String getDashboard(HttpSession session, Model model) {
        JobSeeker jobSeeker = (JobSeeker) session.getAttribute("jobSeeker");

        if (jobSeeker == null) {
            return "redirect:/login";
        }

        // Fetch JobSeekerDetails
        JobSeekerDetails jobSeekerDetails = jobSeekerDetailsService.findByJobSeekerId(jobSeeker.getJobSeekerId());

        // Pass the profile photo to the model
        if (jobSeekerDetails != null) {
            model.addAttribute("profilePhoto", jobSeekerDetails.getProfilePhoto());
        } else {
            model.addAttribute("profilePhoto", "default-avatar.png"); // Use a default avatar if no profile photo exists
        }

        return "jobseeker/dashboard";
    }

    private JobService jobService;

    @GetMapping("/job/{id}")
    public String viewJobDetails(@PathVariable int id, Model model) {
        Job job = jobService.findJobById(id);
        if (job == null) {
            return "redirect:/jobseeker/dashboard"; // Redirect if job not found
        }
        model.addAttribute("job", job);
        return "jobseeker/job-details";
    }
   
   
   
   
   
   
   
   
    // @GetMapping("/dashboard")
    // public String getDashboard(Model model) {
    //     List<Job> jobPosts = new ArrayList<>();
    //     // Add some dummy job posts for demonstration
    //     // jobPosts.add(new Job("Software Engineer", "New York", "Google"));
    //     // jobPosts.add(new Job("Data Scientist", "San Francisco", "Facebook"));

    //     model.addAttribute("jobPosts", jobPosts);
    //     return "jobseeker/dashboard";
    // }
    // public String dashboard() {
    //     return "jobseeker/dashboard";
    // }

}
