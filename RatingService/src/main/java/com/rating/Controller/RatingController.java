package com.rating.Controller;

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

import com.rating.Services.RatingService;
import com.rating.entities.Rating;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
//create rating
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
//Get all
	@PreAuthorize("hasAuthority('SCOPE_internal')||hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		return ResponseEntity.ok(ratingService.getRatings());
	}
	
//GetbyHotelId
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		List<Rating> ratingsByHotel = ratingService.getByHotelId(hotelId);
		return ResponseEntity.ok(ratingsByHotel);
	}
//GetBy User id
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		List<Rating> ratingByUserList = ratingService.getByUserId(userId);
		return ResponseEntity.ok(ratingByUserList);
	}
	
	
}
