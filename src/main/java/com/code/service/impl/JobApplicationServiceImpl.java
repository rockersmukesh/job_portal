package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.model.JobApplication;
import com.code.model.JobSeeker;
import com.code.repo.JobApplicationRepo;
import com.code.service.JobApplicationService;

@Service
public class JobApplicationServiceImpl implements JobApplicationService{
    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    @Override
    public void save(JobApplication application) {
        jobApplicationRepo.save(application);
    }

    @Override
    public List<JobApplication> findByJobSeeker(JobSeeker jobSeeker) {
        return jobApplicationRepo.findByJobSeeker(jobSeeker);
    }
}
