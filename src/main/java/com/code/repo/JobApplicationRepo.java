package com.code.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.model.JobApplication;
import com.code.model.JobSeeker;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication, Integer>  {
    List<JobApplication> findByJobSeeker(JobSeeker jobSeeker);
}
