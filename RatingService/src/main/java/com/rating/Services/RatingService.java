package com.rating.Services;

import java.util.List;

import com.rating.entities.Rating;

public interface RatingService {

	//create
	Rating create(Rating rating);
	//getall ratings
	List<Rating> getRatings();
	//getall by userid
	List<Rating> getByUserId(String userId);
	//getall by hotelid
	List<Rating> getByHotelId(String hotelId);
}
