package com.ty.backpackers.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "enter the city name")
	private String name;

	@NotBlank(message = "enter the state name")
	private String state;

	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Hotel> hotels;

}
