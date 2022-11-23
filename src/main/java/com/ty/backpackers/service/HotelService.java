package com.ty.backpackers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.backpackers.dao.HotelDao;
import com.ty.backpackers.dto.City;
import com.ty.backpackers.dto.Hotel;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.exception.NoIdFoundException;
import com.ty.backpackers.respository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelDao hotelDao;

	public ResponseEntity<ResponseStructure<Hotel>> saveHotel(Hotel hotel) {
		ResponseStructure<Hotel> responseStructure = new ResponseStructure<>();

		Hotel hotel1 = hotelDao.saveHotel(hotel);

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Hotel Created");
		responseStructure.setData(hotel1);

		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Hotel>>> getHotelByCity(City city) {

		ResponseStructure<List<Hotel>> responseStructure = new ResponseStructure<>();

		List<Hotel> hotels = hotelDao.getHotelByCity(city);

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(hotels);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<List<Hotel>>> getAllHotels() {

		ResponseStructure<List<Hotel>> responseStructure = new ResponseStructure<>();

		List<Hotel> hotels = hotelDao.getAllHotels();

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(hotels);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Hotel>> getHotelById(int id) {
		ResponseStructure<Hotel> responseStructure = new ResponseStructure<>();

		Hotel hotel = hotelDao.getHotelById(id);
		if (hotel != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(hotel);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoIdFoundException("Hotel Id : " + id + " is Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteHotelById(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		if (hotelDao.deleteHotelById(id)) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData("Hotel Deleted Successfully");
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoIdFoundException("Hotel Id : " + id + " is Not Found");
		}
	}

}
