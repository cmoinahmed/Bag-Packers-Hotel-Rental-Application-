package com.ty.backpackers.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String name;

	@NotBlank(message = "please enter the address of hotel")
	private String address;

	@NotNull(message = "please enter a phone number of hotel")
	private long phone;

	@Column(unique = true)
	@NotBlank(message = "Please Enter the email")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Enter a Valid Email")
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<Room> rooms;

	@ManyToOne
	@JoinColumn
	private City city;
	

}
