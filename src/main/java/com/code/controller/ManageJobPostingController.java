package com.code.controller;

import com.code.model.Job;
import com.code.model.Recruiter;
import com.code.service.JobService;
import com.code.service.RecruiterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManageJobPostingController {

//    @Autowired
//    private RecruiterService recruiterService;
//
//    @Autowired
//    private JobService jobService;

    private final RecruiterService recruiterService;
    private final JobService jobService;

    public ManageJobPostingController(RecruiterService recruiterService, JobService jobService) {
        this.recruiterService = recruiterService;
        this.jobService = jobService;
    }

    @GetMapping("/recruiter/manage-job")
    public String manageJobPostings(Model model, HttpSession session) {
        Recruiter recruiter = (Recruiter) session.getAttribute("currentRecruiter");
        if (recruiter == null) {
            return "redirect:/recruiter/login";
        }
        List<Job> jobs = jobService.findJobsByRecruiter(recruiter);
        model.addAttribute("jobs", jobs);
        model.addAttribute("job", new Job());
        return "recruiter/manage-jobs";
    }

//    @GetMapping("recruiter/manage-job")
//    public String manageJobPostings(Model model) {
//        Recruiter recruiter = recruiterService.getCurrentRecruiter();
//        if (recruiter == null) {
//            return "redirect:/recruiter/login";
//        }
//        List<Job> jobs = jobService.findJobsByRecruiter(recruiter);
//        model.addAttribute("jobs", jobs);
//        model.addAttribute("job",new Job());
//        return "recruiter/manage-jobs";
//    }
}
