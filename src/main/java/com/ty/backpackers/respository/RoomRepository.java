package com.ty.backpackers.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.backpackers.dto.Hotel;
import com.ty.backpackers.dto.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	@Query(value = "SELECT r FROM Room r WHERE PRICE BETWEEN ?1 AND ?2")
	List<Room> findByPriceRange(double lowPrice, double highPrice);

	List<Room> findByHotel(Hotel hotel);

}
