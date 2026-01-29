package com.code.service;

import java.util.List;
import java.util.Optional;

import com.code.model.JobSeeker;

public interface JobSeekerService {

    void saveOrUpdate(JobSeeker jobSeeker);
    
    JobSeeker findById(int id);

    JobSeeker save(JobSeeker jobSeeker);
    
    List<JobSeeker> findAllJobSeekers();

    long countJobSeekers();
    
    boolean deleteJobSeeker(int jobSeekerId);

    // Object getCurrentUserProfile();
    
    Optional<JobSeeker> findByEmail(String email);

    boolean resetPassword(String token, String password);

    String generateOtp(String email);

    boolean verifyOtp(String email, String otp);

    String getPasswordByEmail(String email);
}
