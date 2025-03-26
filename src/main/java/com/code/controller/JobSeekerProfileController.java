package com.code.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.code.model.JobSeeker;
import com.code.model.JobSeekerDetails;
import com.code.service.JobSeekerDetailsService;
import com.code.service.JobSeekerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/jobseeker")
public class JobSeekerProfileController {

    @Autowired
    private JobSeekerDetailsService jobSeekerDetailsService;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        JobSeeker jobSeeker = (JobSeeker) session.getAttribute("jobSeeker");

        if (jobSeeker == null) {
            return "redirect:/login";
        }

        JobSeekerDetails jobSeekerDetails = jobSeekerDetailsService.findByJobSeekerId(jobSeeker.getJobSeekerId());
        if (jobSeekerDetails == null) {
            jobSeekerDetails = new JobSeekerDetails();
            jobSeekerDetails.setJobSeekerId(jobSeeker); // Associate with the logged-in job seeker
        }

        model.addAttribute("jobSeekerDetails", jobSeekerDetails);
        return "jobseeker/profile";
    }

    /**
     * Update the job seeker's profile
     */
    // @PostMapping("/profile/update")
    // public String updateProfile(@ModelAttribute("jobSeeker") JobSeeker
    // updatedJobSeeker, HttpSession session, Model model) {
    // JobSeeker currentJobSeeker = (JobSeeker) session.getAttribute("jobSeeker");

    // if (currentJobSeeker == null) {
    // return "redirect:/login";
    // }

    // // Update the job seeker's profile
    // updatedJobSeeker.setJobSeekerId(currentJobSeeker.getJobSeekerId());
    // jobSeekerService.save(updatedJobSeeker);

    // // Update the session with the new job seeker details
    // session.setAttribute("jobSeeker", updatedJobSeeker);

    // model.addAttribute("success", "Profile updated successfully!");
    // return "jobseeker/profile";
    // }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("jobSeekerDetails") JobSeekerDetails updatedDetails,
            @RequestParam("profilePhotoFile") MultipartFile profilePhotoFile,
            HttpSession session, Model model) throws IOException {
        JobSeeker jobSeeker = (JobSeeker) session.getAttribute("jobSeeker");

        if (jobSeeker == null) {
            return "redirect:/login";
        }

        // Fetch the JobSeeker object from the database to ensure it is managed
        JobSeeker managedJobSeeker = jobSeekerDetailsService.findJobSeekerById(jobSeeker.getJobSeekerId());
        if (managedJobSeeker == null) {
            model.addAttribute("error", "Job Seeker not found.");
            return "jobseeker/profile";
        }

        // Handle profile picture upload
        if (!profilePhotoFile.isEmpty()) {
            String fileName = managedJobSeeker.getJobSeekerId() + "_" + profilePhotoFile.getOriginalFilename();
            Path filePath = Paths.get("src/main/resources/static/uploads/" + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, profilePhotoFile.getBytes());
            updatedDetails.setProfilePhoto(fileName); // Set the file name in the profilePhoto field
        }

        updatedDetails.setJobSeekerId(managedJobSeeker); // Associate the managed JobSeeker with the details
        jobSeekerDetailsService.save(updatedDetails); // Save the updated details

        model.addAttribute("success", "Profile updated successfully!");
        return "redirect:/jobseeker/profile";
    }

}
