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
            jobSeekerDetails.setJobSeeker(jobSeeker); // Associate with the logged-in job seeker
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
                                @RequestParam("resumeFile") MultipartFile resumeFile,
                                HttpSession session, Model model) {
        JobSeeker jobSeeker = (JobSeeker) session.getAttribute("jobSeeker");

        if (jobSeeker == null) {
            return "redirect:/login";
        }

        try {
            JobSeekerDetails existingDetails = jobSeekerDetailsService.findByJobSeekerId(jobSeeker.getJobSeekerId());
            if (existingDetails == null) {
                existingDetails = new JobSeekerDetails();
                existingDetails.setJobSeeker(jobSeeker);
            }

            // Profile photo upload
            if (!profilePhotoFile.isEmpty()) {
                String photoFileName = jobSeeker.getJobSeekerId() + "_" + profilePhotoFile.getOriginalFilename();
                Path photoPath = Paths.get(UPLOAD_DIR + photoFileName);
                Files.createDirectories(photoPath.getParent());
                Files.write(photoPath, profilePhotoFile.getBytes());
                updatedDetails.setProfilePhoto(photoFileName);
            } else {
                updatedDetails.setProfilePhoto(existingDetails.getProfilePhoto());
            }

            // Resume upload
            if (!resumeFile.isEmpty()) {
                String resumeFileName = jobSeeker.getJobSeekerId() + "_" + resumeFile.getOriginalFilename();
                Path resumePath = Paths.get(UPLOAD_DIR + resumeFileName);
                Files.createDirectories(resumePath.getParent());
                Files.write(resumePath, resumeFile.getBytes());
                updatedDetails.setResume(resumeFileName);
            } else {
                updatedDetails.setResume(existingDetails.getResume());
            }

            updatedDetails.setJobSeeker(jobSeeker);
            jobSeekerDetailsService.save(updatedDetails);

            model.addAttribute("success", "Profile updated successfully!");
        } catch (IOException e) {
            model.addAttribute("error", "File upload failed: " + e.getMessage());
        }

        model.addAttribute("jobSeekerDetails", updatedDetails);
        return "jobseeker/profile";
    }
}
