package com.hotel.Hotel.Service;

import java.util.List;

import com.hotel.Hotel.entities.Hotel;

public interface HotelService {

	
	//create
	Hotel create(Hotel hotel);
	//getall
	List<Hotel> getAll();
	//getone
	Hotel get(String id);
}
