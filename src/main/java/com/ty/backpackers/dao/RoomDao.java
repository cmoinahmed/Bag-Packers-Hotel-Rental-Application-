package com.ty.backpackers.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.backpackers.dto.Hotel;
import com.ty.backpackers.dto.Room;
import com.ty.backpackers.respository.RoomRepository;

@Repository
public class RoomDao {

	@Autowired
	private RoomRepository roomRepository;

	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}

	public Room getRoomById(int id) {
		Optional<Room> optional = roomRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	public List<Room> getAllRoomsByHotel(Hotel hotel) {
		return roomRepository.findByHotel(hotel);
	}

	public boolean deleteRoomById(int id) {
		Optional<Room> optional = roomRepository.findById(id);
		if (optional.isPresent()) {
			roomRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}
	
	public List<Room> getRoomsByPriceRange(double lowPrice,double highPrice){
		return roomRepository.findByPriceRange(lowPrice, highPrice);
	}
 
}
