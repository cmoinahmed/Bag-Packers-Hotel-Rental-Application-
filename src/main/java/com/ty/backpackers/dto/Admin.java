package com.ty.backpackers.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@NotBlank(message = "please enter the name")
	private String name;

	@Column(unique = true)
	@NotBlank(message = "Please Enter the email")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Enter a Valid Email")
	private String email;

	@Column(unique = true)
	@NotNull(message = "please Enter the phone")
	private long phone;

	@NotBlank
	private String password;
}
