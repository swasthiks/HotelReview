package com.hotel.Hotel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.Hotel.Service.HotelService;
import com.hotel.Hotel.entities.Hotel;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService hotelService;

//create
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));

	}

//getsingle
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{hotelid}")
	public ResponseEntity<Hotel> createHotel(@PathVariable String hotelid){
	return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelid));
	}
	
	
//getall
	@PreAuthorize("hasAuthority('SCOPE_internal')||hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Hotel>> getAll(){
		return ResponseEntity.ok(hotelService.getAll());
	}
	
}
