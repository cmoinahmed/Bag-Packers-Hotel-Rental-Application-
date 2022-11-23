package com.ty.backpackers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.backpackers.dao.CityDao;
import com.ty.backpackers.dto.City;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.exception.CityNotFoundException;
import com.ty.backpackers.exception.NoIdFoundException;

@Service
public class CityService {

	@Autowired
	private CityDao cityDao;

	public ResponseEntity<ResponseStructure<City>> saveCity(City city) {
		ResponseStructure<City> responseStructure = new ResponseStructure<>();

		city = cityDao.saveCity(city);

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("City Created");
		responseStructure.setData(city);

		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<City>> findCityByName(String name) {
		ResponseStructure<City> responseStructure = new ResponseStructure<>();

		City city = cityDao.findCityByName(name);

		if (city != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(city);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}else {
			throw new CityNotFoundException("City :"+name+"is Not Found");
		}
	}
	
	public ResponseEntity<ResponseStructure<City>> getCityById(int id){
		ResponseStructure<City> responseStructure = new ResponseStructure<>();
		
		City city=cityDao.getCityById(id);
		if (city != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(city);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}else {
			throw new NoIdFoundException("City ID :"+id+"is Not Found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<City>>> getAllCities() {
		ResponseStructure<List<City>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(cityDao.getAllCities());
		
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

}
