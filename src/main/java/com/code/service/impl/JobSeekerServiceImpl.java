package com.code.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.model.JobSeeker;
import com.code.repo.JobSeekerRepo;
import com.code.service.JobSeekerService;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

	@Autowired
	private JobSeekerRepo jobSeekeRepo;

	
	@Override
	public JobSeeker save(JobSeeker jobSeeker) {
		
		return jobSeekeRepo.save(jobSeeker);
	}


	@Override
	public List<JobSeeker> findAllJobSeekers() {
		return jobSeekeRepo.findAll();
	}


	@Override
	public boolean deleteJobSeeker(int jobSeekerId) {
		Optional<JobSeeker> dbStudent = jobSeekeRepo.findById(jobSeekerId);
		 if(dbStudent.isPresent()) {
			 jobSeekeRepo.delete(dbStudent.get());
			 return true;
		 }
		 return false;
	}


	@Override
	public JobSeeker findByEmail(String email) {
		return jobSeekeRepo.findByEmailId(email).orElse(null);
	}
	


}
