package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.model.Job;
import com.code.model.Recruiter;
import com.code.repo.JobRepo;
import com.code.service.JobService;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepo jobRepo;

    @Override
    public void saveJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public List<Job> findJobsByRecruiter(Recruiter recruiter) {
        return jobRepo.findByRecruiter(recruiter);
    }

    @Override
    public Job findJobById(int jobId) {
        return jobRepo.findById(jobId).orElse(null);
    }

    @Override
    public void deleteJob(int jobId) {
        jobRepo.deleteById(jobId);
    }
}
