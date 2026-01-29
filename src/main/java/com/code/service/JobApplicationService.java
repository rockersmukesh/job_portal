package com.code.service;

import java.util.List;

import com.code.model.JobApplication;
import com.code.model.JobSeeker;

public interface JobApplicationService {

    void save(JobApplication application);
    List<JobApplication> findByJobSeeker(JobSeeker jobSeeker);
}