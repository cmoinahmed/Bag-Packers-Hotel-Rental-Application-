package com.ty.backpackers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.backpackers.dto.Booking;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.dto.User;
import com.ty.backpackers.service.BookingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService service;
	
	@ApiOperation(value = "Register Booking",notes="Takes input as Booking object and returns Booking object with id")
	@ApiResponses(value = {@ApiResponse(code = 201,message = "successfully Registered")})
	@PostMapping( consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Booking>> registerBooking( @RequestBody Booking booking){
		
		return service.registerBooking(booking);
	}
	
	@ApiOperation(value = "Retrieves Booking by Id",notes="Takes input as Booking id and returns Booking object")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Found successfully"),
			@ApiResponse(code = 404,message = "booking not Found")})
	@GetMapping(value="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable int id){
		
		return service.getBookingById(id);
	}
	
	@ApiOperation(value = "Retrieves Bookings by User",notes="Takes input as user object and returns List of Booking objects")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Found successfully")})
	@PostMapping(value = "/getBooking" ,consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookingsByUser(@RequestBody User user){
		
		return service.getAllBookingsByUser(user);
	}
	
	
	@ApiOperation(value = "deletes the booking by id",notes="Takes input as Id and deletes booking objects")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "resource deleted successfully"),
			@ApiResponse(code = 404,message = "booking to be deleted not Found")})
	@DeleteMapping(value = "/deletebyid/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<String>> cancelBooking(@PathVariable int id){
		
		return service.CancelBooking(id);
	}
	
	@ApiOperation(value = "Checks out the rooms of the booking and changes the status to available",
			notes="Takes input as bookingId and changes the status to available")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfull"),
			@ApiResponse(code = 404,message = "booking to not Found")})
	@PatchMapping(value = "/checkout/{bookingId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<String>> checkOut(@PathVariable int bookingId){
		
		return service.checkOut(bookingId);
	}
}
