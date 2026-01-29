package com.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.model.JobSeeker;
import com.code.model.JobSeekerDetails;
import com.code.repo.JobSeekerDetailsRepo;
import com.code.repo.JobSeekerRepo;
import com.code.service.JobSeekerDetailsService;

@Service
public class JobSeekerDetailsServiceImpl implements JobSeekerDetailsService{
     
    @Autowired
    private JobSeekerRepo jobSeekerRepo;

    @Autowired
    private JobSeekerDetailsRepo jobSeekerDetailsRepo;

    @Override
    public JobSeekerDetails save(JobSeekerDetails jobSeekerDetails) {
        JobSeeker managedJobSeeker = jobSeekerRepo.findById(jobSeekerDetails.getJobSeeker().getJobSeekerId())
                .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));

        jobSeekerDetails.setJobSeeker(managedJobSeeker); // Attach the managed entity
        return jobSeekerDetailsRepo.save(jobSeekerDetails);
    }

    @Override
    public JobSeekerDetails findByJobSeekerId(int jobSeekerId) {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(jobSeekerId); // Create a JobSeeker object with the given ID
        return jobSeekerDetailsRepo.findByJobSeeker(jobSeeker);
    }

    @Override
    public JobSeeker findJobSeekerById(int jobSeekerId) {
        return jobSeekerRepo.findById(jobSeekerId).orElse(null); // Fetch the JobSeeker object
    }

}
