package com.ty.backpackers.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.backpackers.dto.Booking;
import com.ty.backpackers.dto.User;
import com.ty.backpackers.respository.BookingRepository;

@Repository
public class BookingDao {

	@Autowired
	BookingRepository bookingRepository;
	
	public Booking registerBooking(Booking booking) {
		
		return bookingRepository.save(booking);
	}
	
	public Booking getBookingById(int id) {
		
		Optional<Booking> optional=bookingRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
		
	}
	public boolean cancelBooking(int id) {
		
		Optional<Booking> optional=bookingRepository.findById(id);
		
		if(optional.isPresent()) {
			bookingRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Booking> getAllBookingsByUser(User user){
		
		return bookingRepository.findByUser(user);
	}

	
}
