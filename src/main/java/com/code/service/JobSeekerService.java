package com.code.service;

import java.util.List;

import com.code.model.JobSeeker;

public interface JobSeekerService {
    JobSeeker save(JobSeeker jobSeeker);
    
    List<JobSeeker> findAllJobSeekers();
}
