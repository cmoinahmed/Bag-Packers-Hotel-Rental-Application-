package com.ty.backpackers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.backpackers.dto.City;
import com.ty.backpackers.dto.Hotel;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.service.HotelService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@ApiOperation(value = "save Hotel", notes = "input hotel obj and return hotel obj with id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Hotel Object Saved") })
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Hotel>> saveHotel(@RequestBody @Valid Hotel hotel) {
		return hotelService.saveHotel(hotel);
	}

	@ApiOperation(value = "Get Hotels By City", notes = "input city obj and return list of hotel objects")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "fetched hotels by city") })
	@PostMapping(value = "/getallhotels", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<List<Hotel>>> getHotelByCity(@RequestBody City city) {
		return hotelService.getHotelByCity(city);
	}

	@ApiOperation(value = "get all Hotels", notes = "It will fetch all hotels")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch all the hotels") })
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<List<Hotel>>> getAllHotels() {
		return hotelService.getAllHotels();
	}

	@ApiOperation(value = "get hotel By Id", notes = "input int hotel id and output hotel obj ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "fetch the Hotel Object"),
			@ApiResponse(code = 404, message = "Hotel Not Found") })
	@GetMapping(value = "/id", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Hotel>> getHotelById(@RequestParam int id) {
		return hotelService.getHotelById(id);
	}

	@ApiOperation(value = "delete hotel By Id", notes = "input int hotel id and output String Hotel deleted ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deleted the Hotel Object"),
			@ApiResponse(code = 404, message = "Hotel Not Found") })
	@DeleteMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<String>> deleteHotelById(@RequestParam int id) {
		return hotelService.deleteHotelById(id);
	}

}
