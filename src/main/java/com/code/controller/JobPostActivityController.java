package com.code.controller;

import com.code.model.Job;
import com.code.model.Recruiter;
import com.code.service.JobSeekerService;
import com.code.service.JobService;
import com.code.service.RecruiterService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recruiter")
public class JobPostActivityController {
    // private JobSeekerService jobSeekerService;

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private JobService jobService;

    // @Autowired
    // public JobPostActivityController(JobSeekerService jobSeekerService) {
    //     this.jobSeekerService = jobSeekerService;
    // }

    @GetMapping("/job/new")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "recruiter/job-form";
    }

    @PostMapping("/job/save")
    public String saveJob(@Valid @ModelAttribute("job") Job job, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recruiter/job-form";
        }

        Recruiter recruiter = recruiterService.getCurrentRecruiter();
        if (recruiter == null) {
            return "redirect:/recruiter/login"; // Redirect to login if recruiter is not found
        }
        job.setRecruiter(recruiter);
        jobService.saveJob(job);
        return "redirect:/recruiter/dashboard";
    }

    @GetMapping("/job/edit/{id}")
    public String showEditJobForm(@PathVariable("id") int id, Model model) {
        Job job = jobService.findJobById(id);
        model.addAttribute("job", job);
        return "recruiter/job-form";
    }

    @GetMapping("/job/delete/{id}")
    public String deleteJob(@PathVariable("id") int id) {
        jobService.deleteJob(id);
        return "redirect:/recruiter/dashboard";
    }


}
