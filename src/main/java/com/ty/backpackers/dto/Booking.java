package com.ty.backpackers.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@Entity
@ApiModel(description = "mandatory fields are checkindate and checkoutdate")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double amount;

	@NotNull(message = "enter the checkin date")
	@ApiModelProperty(required = true)
	private LocalDate checkInDate;

	@NotNull(message = "enter the checkout date")
	@ApiModelProperty(required = true)
	private LocalDate checkOutDate;

	@ManyToOne
	@JoinColumn
	private User user;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Room> rooms;

}
