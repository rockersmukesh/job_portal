package com.code.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecruiterDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recruiterDetailsId;

	@OneToOne
	@JoinColumn(name = "recruiter_details_id")
	@MapsId
	private Recruiter recruiterId;

	private String city;

	private String state;

	private String country;

	private String company;

	@Column(nullable = true, length = 64)
	private String profilePhoto;
}
