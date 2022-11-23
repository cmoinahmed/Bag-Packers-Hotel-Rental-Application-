package com.ty.backpackers.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String name;

	@Column(unique = true)
	@NotBlank(message = "Please Enter the email")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Enter a Valid Email")
	private String email;

	@Column(unique = true)
	@NotNull(message = "please Enter the phone")
	private long phone;

	@NotBlank(message = "enter a password")
	private String password;

	@NotBlank
	private String address;

	@NotNull(message = "Aadhar Number Must be filled")
	private long aadharNumber;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Booking> bookings;

}
