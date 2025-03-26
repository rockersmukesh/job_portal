package com.code.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.model.Job;
import com.code.model.Recruiter;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {
    // List<Job> findByJobId(int jobId);
    List<Job> findByRecruiter(Recruiter recruiter);

}
