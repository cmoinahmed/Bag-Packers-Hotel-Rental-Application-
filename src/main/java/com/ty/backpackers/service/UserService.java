package com.ty.backpackers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.backpackers.dao.UserDao;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.dto.User;
import com.ty.backpackers.exception.InvalidCredentialsException;
import com.ty.backpackers.exception.InvalidUserException;
import com.ty.backpackers.exception.NoIdFoundException;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("okay");
		responseStructure.setData(userDao.saveUser(user));
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> findUserById(int id) {

		User user = userDao.findUserById(id);

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		if (user != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("sucess");
			responseStructure.setData(user);

			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);

		} else {
			throw new NoIdFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<User>> findUserByEmail(String email) {

		User user = userDao.getUserByEmail(email);

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		if (user != null) {

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("sucess");
			responseStructure.setData(user);

			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		} else {

			throw new InvalidUserException("Email Not Found");
		}

	}

	public ResponseEntity<ResponseStructure<User>> findUserByPhone(long phone) {

		User user = userDao.getUSerByPhone(phone);

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		if (user != null) {

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("sucess");
			responseStructure.setData(user);

			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		} else {

			throw new InvalidUserException("Phone Number not found");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteUserById(int id) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		if (userDao.deleteUserById(id)) {

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("sucess");
			responseStructure.setData("Deleted Scuessfully");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		} else {

			throw new InvalidUserException("User Id Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {

		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("okay");
		responseStructure.setData(userDao.getAllUsers());
		return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("okay");
		responseStructure.setData(userDao.saveUser(user));
		return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<User>> validateUserByEmail(String email, String password) {

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		User user = userDao.getUserByEmail(email);

		if (user != null) {

			if (user.getPassword().equals(password)) {

				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setData(user);
				responseStructure.setMessage("Sucess");
				return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidCredentialsException("invalid password");
			}

		} else {
			throw new InvalidUserException("Invalid Email");
		}

	}

	public ResponseEntity<ResponseStructure<User>> validateUserByPhone(long phone, String password) {

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		User user = userDao.getUSerByPhone(phone);

		if (user != null) {

			if (user.getPassword().equals(password)) {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setData(user);
				responseStructure.setMessage("Success");
				return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidCredentialsException("invalid password");
			}

		} else {
			throw new InvalidUserException("Invalid PhoneNumber");
		}

	}

}
