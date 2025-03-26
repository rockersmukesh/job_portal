package com.code.repo;

import com.code.model.JobSeeker;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker,Integer> {
	    Optional<JobSeeker> findByEmailId(String email);
		Optional<JobSeeker> findByResetToken(String resetToken);
}
