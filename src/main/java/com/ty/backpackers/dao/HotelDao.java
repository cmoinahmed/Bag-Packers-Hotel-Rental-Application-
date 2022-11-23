package com.ty.backpackers.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.backpackers.dto.City;
import com.ty.backpackers.dto.Hotel;
import com.ty.backpackers.respository.HotelRepository;

@Repository
public class HotelDao {

	@Autowired
	private HotelRepository hotelRepository;

	public Hotel saveHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public List<Hotel> getHotelByCity(City city) {
		return hotelRepository.findByCity(city);
	}

	public Hotel getHotelById(int id) {
		Optional<Hotel> optional = hotelRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public boolean deleteHotelById(int id) {
		Optional<Hotel> optional = hotelRepository.findById(id);
		if (optional.isPresent()) {
			hotelRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

}
