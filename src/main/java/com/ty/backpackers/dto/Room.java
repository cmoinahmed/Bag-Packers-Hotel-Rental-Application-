package com.ty.backpackers.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Room  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "enter the type of room")
	private String type;

	@NotNull(message = "enter the price of room")
	private double price;

	@NotBlank
	private String status;

	@ManyToOne
	@JoinColumn
	private Hotel hotel;

}
