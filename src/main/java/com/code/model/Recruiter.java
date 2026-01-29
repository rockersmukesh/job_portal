package com.code.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recruiterId;
	
	@Column(nullable = false)
	@NotBlank(message = "First Name cannot be empty")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message = "Last Name cannot be empty")
	private String lastName;
	
	@Column(unique = true, nullable = false)
	@Email(message = "Please enter a valid email address")
	@NotBlank(message = "Email cannot be empty")
	private String emailId;
	
	@Column(nullable = false)
	@NotBlank(message = "Password cannot be empty")
	private String password;
	
	@Column(nullable = false)
	@NotBlank(message = "Mobile Number cannot be empty")
	private String mobileNo;
	
	@Column(nullable = false)
	@NotBlank(message = "Company Name cannot be empty")
	private String companyName;
	
	@Column(nullable = false)
	@NotBlank(message = "Company Location cannot be empty")
	private String companyLocation;
	
	@OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private List<Job> jobs;

	@Column(nullable = false)
    private boolean approvalStatus;

	// @Override
	// public String toString() {
	// 	return "Recruiter{" +
	// 			"recruiterId=" + recruiterId +
	// 			", firstName='" + firstName + '\'' +
	// 			", lastName='" + lastName + '\'' +
	// 			", emailId='" + emailId + '\'' +
	// 			", password='" + password + '\'' +
	// 			", mobileNo='" + mobileNo + '\'' +
	// 			", companyName='" + companyName + '\'' +
	// 			", companyLocation='" + companyLocation + '\'' +
	// 			'}';
	// }
}
