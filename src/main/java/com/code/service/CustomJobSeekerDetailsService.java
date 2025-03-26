//package com.code.service;
//
//import com.code.model.JobSeeker;
//import com.code.repo.JobSeekerRepo;
//import com.code.util.CustomJobSeekerDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomJobSeekerDetailsService implements UserDetailsService {
//    private final JobSeekerRepo jobSeekerRepo;
//
//    @Autowired
//    public CustomJobSeekerDetailsService(JobSeekerRepo jobSeekerRepo) {
//        this.jobSeekerRepo = jobSeekerRepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        JobSeeker jobSeeker = jobSeekerRepo.findByEmailId(username).orElseThrow(() -> new UsernameNotFoundException("Could not found user"));
//        return new CustomJobSeekerDetails(jobSeeker);
//    }
//}
