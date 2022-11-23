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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.dto.User;
import com.ty.backpackers.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "SaveUser", notes = "Input is User Obj and returns obj with Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Obj is Created") })
	@PostMapping( consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody @Valid User user) {
		return userService.saveUser(user);
	}

	@ApiOperation(value = "Get Ueser By Id", notes = "Input is an  Integer and returns obj with Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Obj is Created"),
			@ApiResponse(code = 404, message = "User Id Not Found") })
	@GetMapping(value = "/{id}",  produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int id) {
		return userService.findUserById(id);
	}

	@ApiOperation(value = "Get User By Email", notes = "Input is an String email and returns obj with Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Obj is Created"),
			@ApiResponse(code = 404, message = "User Email Not Found") })
	@GetMapping( produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> findUserByEmail(@RequestParam String email) {
		return userService.findUserByEmail(email);
	}

	@ApiOperation(value = "Get User By PhoneNumber", notes = "Input is User Obj and returns obj with Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Obj is Created"),
			@ApiResponse(code = 404, message = "User PhoneNumber Not Found") })
	@GetMapping(value = "/phone",  produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> findUserByPhone(@RequestParam long phone) {
		return userService.findUserByPhone(phone);
	}

	@ApiOperation(value = "Delete User By Id", notes = "Input is User Obj and returns obj with Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Obj is Delected"),
			@ApiResponse(code = 404, message = "User PhoneNumber Not Found") })
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<String>> deleteUserById(@PathVariable int id) {
		return userService.deleteUserById(id);
	}

	@ApiOperation(value = "Get All Users", notes = "Input is User Obj and returns obj with Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Obj is Created") })
	@GetMapping(value = "/getall",produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		return userService.getAllUser();
	}

	@ApiOperation(value = "Update Users", notes = "Input is User Obj and returns updated Object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Obj is Created")})
	@PutMapping(value = "/updateuser",consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	@ApiOperation(value = "validates the user by emial",notes = "takes email and password as input and validates the user")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "validated successfully"),@ApiResponse(code = 404,message = "email not found"),
			@ApiResponse(code = 401,message = "invalid password")})
	@PostMapping(value = "/email",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestParam String email,@RequestParam String password){
		return userService.validateUserByEmail(email, password);
	}
	@ApiOperation(value = "validates the user by phonenumber",notes = "takes phone and password as input and validates the user")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "validated successfully"),@ApiResponse(code = 404,message = "phonenumber not found"),
			@ApiResponse(code = 401,message = "invalid password")})
	@PostMapping(value = "/phone",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestParam long phone,@RequestParam String password){
		return userService.validateUserByPhone(phone, password);
	}
	
}
