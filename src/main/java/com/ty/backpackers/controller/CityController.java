package com.ty.backpackers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.backpackers.dto.City;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.service.CityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "save city", notes = "input city obj and output city obj with id")
	@ApiResponses(value= { @ApiResponse(code = 201, message = "City Object Saved")})
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<City>> saveCity(@RequestBody @Valid City city) {
		return cityService.saveCity(city);
	}

	@ApiOperation(value = "get city By Name", notes = "input String cityName and output city obj ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "fetched the City Object"),
			@ApiResponse(code = 404, message = "City Not Found") })
	@GetMapping(value = "/{name}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<City>> findCityByName(@PathVariable String name) {
		return cityService.findCityByName(name);
	}

	@ApiOperation(value = "get city By Id", notes = "input int cityid and output city obj ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "fetched the City Object"),
			@ApiResponse(code = 404, message = "City Not Found") })
	@GetMapping( produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<City>> getCityById(@RequestParam int id) {
		return cityService.getCityById(id);
	}

	@ApiOperation(value = "get all cities", notes = "it will fetch all cities")
	@ApiResponses(value= {@ApiResponse(code = 200, message = "Fetch all the cities")})
	@GetMapping(value="/getall",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<List<City>>> getAllCities() {
		return cityService.getAllCities();
	}

}
