package com.ty.backpackers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.backpackers.dao.AdminDao;
import com.ty.backpackers.dto.Admin;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.exception.InvalidCredentialsException;
import com.ty.backpackers.exception.InvalidUserException;

import com.ty.backpackers.exception.NoIdFoundException;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> saveService(Admin admin) {

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin2 = adminDao.saveAdmin(admin);
		System.out.println(admin2);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("SUCCESSFULLY CREATED");
		responseStructure.setData(admin2);
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> getAllAdmins() {
		ResponseStructure<List<Admin>> responseStructure = new ResponseStructure<List<Admin>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(adminDao.getAllAdmin());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		Admin admin1 = adminDao.findAdminById(admin.getId());
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();

		if (admin1 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(adminDao.saveAdmin(admin));
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);

		} else {
			throw new NoIdFoundException("ID " + admin.getId() + ", NOT FOUND");
		}

	}

	public ResponseEntity<ResponseStructure<Admin>> findAdminById(int id) {

		ResponseEntity<ResponseStructure<Admin>> responseEntity;

		Admin admin = adminDao.findAdminById(id);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		if (admin != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(admin);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);

		} else {
			throw new NoIdFoundException("ID " + id + ", NOT FOUND");
		}

	}

	public ResponseEntity<ResponseStructure<Admin>> deleteAdminById(int id) {

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		if (adminDao.deleteAdminById(id)) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);

		} else {
			throw new NoIdFoundException("ID " + id + ", NOT FOUND");
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> findAdminByEmail(String email) {

		ResponseEntity<ResponseStructure<Admin>> responseEntity;

		Admin admin = adminDao.findAdminByEmail(email);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		if (admin != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(admin);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);

		} else {
			throw new InvalidUserException("EMAIL : " + email + ", NOT FOUND");
		}

	}

	public ResponseEntity<ResponseStructure<Admin>> findAdminByPhone(long phone) {

		ResponseEntity<ResponseStructure<Admin>> responseEntity;

		Admin admin = adminDao.findAdminByPhone(phone);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		if (admin != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(admin);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);

		} else {
			throw new InvalidUserException("PHONE NO: " + phone + " FOUND ");
		}

	}

	public ResponseEntity<ResponseStructure<Admin>> adminValidateByEmail(String email, String password) {

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();

		Admin admin = adminDao.findAdminByEmail(email);

		if (admin != null) {

			if (admin.getPassword().equals(password)) {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("SUCCESS");
				responseStructure.setData(admin);
				return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidCredentialsException("INVALID PASSWORD");
			}
		} else {
			throw new InvalidUserException("INVALID ADMIN");
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> adminValidateByPhone(long phone, String password) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin = adminDao.findAdminByPhone(phone);
		if (admin != null) {
			if (password.equals(admin.getPassword())) {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("SUCCESS");
				responseStructure.setData(admin);
				return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidCredentialsException("INVALID PASSWORD");

			}
		} else {
			throw new InvalidUserException("INVALID ADMIN");
		}
	}
}
