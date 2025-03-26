package com.code.service;

import com.code.model.JobSeeker;
import com.code.model.JobSeekerDetails;

public interface JobSeekerDetailsService {
    JobSeekerDetails save(JobSeekerDetails jobSeekerDetails);
    JobSeekerDetails findByJobSeekerId(int jobSeekerId);
    JobSeeker findJobSeekerById(int jobSeekerId);
}
