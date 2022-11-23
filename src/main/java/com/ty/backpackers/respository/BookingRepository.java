package com.ty.backpackers.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.backpackers.dto.Booking;
import com.ty.backpackers.dto.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	List<Booking> findByUser(User user);

}
