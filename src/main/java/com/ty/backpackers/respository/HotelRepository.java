package com.ty.backpackers.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.backpackers.dto.City;
import com.ty.backpackers.dto.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	List<Hotel> findByCity(City city);
}
