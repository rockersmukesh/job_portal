// package com.code.util;

// import com.code.model.Job;
// import com.code.model.JobSeeker;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;

// public class CustomJobSeekerDetails implements UserDetails {
//     private JobSeeker jobSeeker;

//     public CustomJobSeekerDetails(JobSeeker jobSeeker) {
//         this.jobSeeker = jobSeeker;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
// //        JobSeeker jobSeekerId = jobSeeker.getJobSeekerId();
//         List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//         authorities.add(new SimpleGrantedAuthority("JobSeeker"));
//         return authorities;
//     }

//     @Override
//     public String getPassword() {
//         return jobSeeker.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return jobSeeker.getEmailId();
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }
