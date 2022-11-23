package com.ty.backpackers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.backpackers.dao.RoomDao;
import com.ty.backpackers.dto.Hotel;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.dto.Room;
import com.ty.backpackers.exception.NoIdFoundException;

@Service
public class RoomService {

	@Autowired
	RoomDao roomDao;
	
	public ResponseEntity<ResponseStructure<Room>> saveRoom(Room room){
		
		Room room1=roomDao.saveRoom(room);
		
		ResponseStructure<Room> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("successful");
		rs.setData(room1);
		
		return new ResponseEntity<ResponseStructure<Room>>(rs,HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<Room>> getRoomById(int id){
		
		Room room=roomDao.getRoomById(id);
		ResponseStructure<Room> rs=new ResponseStructure<>();
		if(room!=null) {
		
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("successful");
			rs.setData(room);
			
			return new ResponseEntity<ResponseStructure<Room>>(rs,HttpStatus.OK);
		}
		else {
			
			throw new NoIdFoundException("Given id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Room>>> getAllRooms(){
		
		List<Room> rooms=roomDao.getAllRooms();
		
		ResponseStructure<List<Room>> rs=new ResponseStructure<>();

		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("successful");
		rs.setData(rooms);
		
		return new ResponseEntity<ResponseStructure<List<Room>>>(rs,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Room>>> getAllRoomsByHotel(Hotel hotel){
		
		List<Room> rooms=roomDao.getAllRoomsByHotel(hotel);
		
		ResponseStructure<List<Room>> rs=new ResponseStructure<>();

		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Successful");
		rs.setData(rooms);
		
		return new ResponseEntity<ResponseStructure<List<Room>>>(rs,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Room>> deleteRoomById(int id){
		
		ResponseStructure<Room> rs=new ResponseStructure<>();

		if(roomDao.deleteRoomById(id)) {
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("Deleted successfully");
			rs.setData(null);
			
			return new ResponseEntity<ResponseStructure<Room>>(rs,HttpStatus.OK);
		}
		else {
			throw new NoIdFoundException("Given room of id :"+id+" not present");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Room>>> getRoomsByPriceRange(double lowPrice,double highPrice){
		
		List<Room> rooms=roomDao.getRoomsByPriceRange(lowPrice, highPrice);
		
		ResponseStructure<List<Room>> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Rooms Found");
		rs.setData(rooms);
		return new ResponseEntity<ResponseStructure<List<Room>>>(rs,HttpStatus.OK);
	}
}
