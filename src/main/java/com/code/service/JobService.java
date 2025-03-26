package com.code.service;

import java.util.List;

import com.code.model.Job;
import com.code.model.Recruiter;

public interface JobService {
    void saveJob(Job job);
    List<Job> findJobsByRecruiter(Recruiter recruiter);
    Job findJobById(int jobId);
    void deleteJob(int jobId);
}
