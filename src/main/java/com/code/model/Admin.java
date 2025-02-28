package com.code.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId; 
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true, nullable = false)
	private String emailId;
	
	private String password;
	
	private String mobileNo;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
}
