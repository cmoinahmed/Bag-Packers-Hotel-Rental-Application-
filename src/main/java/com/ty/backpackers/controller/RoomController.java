package com.ty.backpackers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.backpackers.dto.Hotel;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.dto.Room;
import com.ty.backpackers.service.RoomService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@ApiOperation(value = "Booking room",notes="Takes input as Room object and returns Room object with id")
	@ApiResponses(value = {@ApiResponse(code = 201,message = "successfully Booked")})
	@PostMapping( consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Room>> saveRoom(@RequestBody Room room){
		
		return roomService.saveRoom(room);
	}
	
	@ApiOperation(value = "Retrieves room based on id",notes="Takes input as Id and returns Room object ")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfully found"),
			@ApiResponse(code = 404,message = "Room not Found")})
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Room>> getRoomById(@PathVariable int id){
		return roomService.getRoomById(id);
	}
	
	@ApiOperation(value = "Retrieves all the rooms",notes=" returns List of Rooms ")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfully found")})
	@GetMapping(value = "/getallrooms" ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Room>>> getAllRooms(){
		
		return roomService.getAllRooms();
	}
	
	@ApiOperation(value = "Retrieves room based on Hotel",notes="Takes input as Hotel object and returns List of Room  ")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfully found")})
	@PostMapping(value = "/getroomsbyhotel", consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Room>>> getAllRoomsByHotel(@RequestBody Hotel hotel){
		 
		return roomService.getAllRoomsByHotel(hotel);
	}
	
	
	@ApiOperation(value = "Deletes room based on id",notes="Takes input as Id and Deletes room ")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfully found"),
			@ApiResponse(code = 404,message = "Room not Found")})
	@DeleteMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
	public ResponseEntity<ResponseStructure<Room>> deleteRoomById(@PathVariable int id){
		return roomService.deleteRoomById(id);
	}
	
	@ApiOperation(value = "retrieves rooms based on Price range",notes="Takes input as lowprice and highprice and returns rooms ")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfully found")})
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Room>>> getRoomsByPriceRange(@RequestParam double lowPrice,@RequestParam double highPrice){
		
		return roomService.getRoomsByPriceRange(lowPrice, highPrice);
	}
}
