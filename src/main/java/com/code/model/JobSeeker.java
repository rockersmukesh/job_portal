package com.code.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name="job_seeker")
@NoArgsConstructor
public class JobSeeker {

    @Id
//    @Column(name="Job_Seeker_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobSeekerId;

    @Column(name="First_name" , nullable = false)
    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @Column(name="Last_name" , nullable = false)
    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @Column(name="password" , nullable = false)
    @NotBlank(message = "Password cannot be empty")
    private String password;
    
    @Column(name="email_id" , nullable = false)
    @Email(message = "Please enter a valid email id")
    @NotBlank(message = "Email Id cannot be empty")
    private String emailId;

    @Column(name="mobile_no" , nullable = false)
    @NotBlank(message = "Mobile number cannot be empty")
    @Size(min=10,max =  10, message = "Please enter valid phone number")
	@Pattern(regexp = "^\\d{10}$",message = "Please enter valid phone number")
    private String mobileNo;




}
