package com.ty.backpackers.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.backpackers.dao.BookingDao;
import com.ty.backpackers.dto.Booking;
import com.ty.backpackers.dto.ResponseStructure;
import com.ty.backpackers.dto.Room;
import com.ty.backpackers.dto.User;
import com.ty.backpackers.exception.NoIdFoundException;
import com.ty.backpackers.util.CalculateDays;

@Service
public class BookingService {

	@Autowired
	BookingDao bookingDao;
	
	
	public ResponseEntity<ResponseStructure<Booking>> registerBooking(Booking booking){
		
		
	 	LocalDate lc1=booking.getCheckInDate();
	 	LocalDate lc2=booking.getCheckOutDate();
	 	String s=lc1.toString();
	 	String s1=lc2.toString();
	 	
	 	
	 	long days=CalculateDays.findDifference(s, s1);
	 	List<Room> rooms=booking.getRooms();
	 	
	 	double totalamt=0;
	 	if(rooms!=null) {
	 	for(Room r:rooms) {
	 		r.setStatus("Booked");
	 		totalamt=totalamt+r.getPrice()*days;
	 	}
	 	}
	 	booking.setAmount(totalamt);
	 	Booking booking1=bookingDao.registerBooking(booking);
	 	
		
		ResponseStructure<Booking> rs=new ResponseStructure<>();
		
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("successfull");
		rs.setData(booking1);
		
		return new ResponseEntity<ResponseStructure<Booking>>(rs,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(int id){
		
		Booking booking=bookingDao.getBookingById(id);
		ResponseStructure<Booking> rs=new ResponseStructure<>();
		if(booking!=null) {
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("successful");
			rs.setData(booking);
			
			return new ResponseEntity<ResponseStructure<Booking>>(rs,HttpStatus.OK);
		}
		else {
			throw new NoIdFoundException("given Booking id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> CancelBooking(int id){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		Booking booking=bookingDao.getBookingById(id);
		  
		if(booking!=null) {
			 List<Room> rooms=booking.getRooms();
			for(Room r:rooms) {
				r.setStatus("Available");
			}
		}
		if(bookingDao.cancelBooking(id)) {
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("successful");
			rs.setData("Booking cancelled successfully");
			
			return new ResponseEntity<>(rs,HttpStatus.OK);	
		}
		else {
			throw new NoIdFoundException("Given booking id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookingsByUser(User user){
		
		List<Booking> bookings=bookingDao.getAllBookingsByUser(user);
		ResponseStructure<List<Booking>> rs=new ResponseStructure<>();
		
		
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("successful");
			rs.setData(bookings);
			
			return new ResponseEntity<ResponseStructure<List<Booking>>>(rs,HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<String>> checkOut(int bookingId){
		
		Booking booking=bookingDao.getBookingById(bookingId);
		ResponseStructure<String> rs=new ResponseStructure<>();
		
		if(booking!=null) {
			
			List<Room> rooms=booking.getRooms();
			if(! rooms.isEmpty()) {
				
				for(Room r:rooms) {
					r.setStatus("Available");
				}
				booking.setRooms(rooms);
				bookingDao.registerBooking(booking);
				rs.setMessage("Successfull");
				rs.setStatus(HttpStatus.OK.value());
				rs.setData("Successfully checked out");
				return new ResponseEntity<>(rs,HttpStatus.OK);
			}
			else {
				rs.setMessage("Not found");
				rs.setStatus(HttpStatus.NOT_FOUND.value());
				rs.setData("no rooms to checked out");
				return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
			}
		}
			else {
			
			throw new NoIdFoundException("Given Booking with id "+bookingId+" not found");
		}
		}
	}

