package com.ty.backpackers.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.backpackers.dto.City;
import com.ty.backpackers.respository.CityRepository;

@Repository
public class CityDao {

	@Autowired
	private CityRepository repository;
	
	public City saveCity(City city) {
		
		return repository.save(city);
	}
	
	public City findCityByName(String name) {
		
		Optional<City> opt=repository.findByName(name);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			return null;
		}
	}
	public City getCityById(int id) {
		 
		Optional<City> opt=repository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			return null;
		}
		
		
	}
	
	public List<City> getAllCities(){
		
		return repository.findAll();
	}
}
