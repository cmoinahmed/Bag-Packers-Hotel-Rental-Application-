package com.ty.backpackers.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.backpackers.dto.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	Optional<City> findByName(String name);
}
