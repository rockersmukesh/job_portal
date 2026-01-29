package com.code.repo;

import com.code.model.JobSeeker;
import com.code.model.JobSeekerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerDetailsRepo extends JpaRepository<JobSeekerDetails,Integer> {
    JobSeekerDetails findByJobSeeker(JobSeeker jobSeeker);
}
