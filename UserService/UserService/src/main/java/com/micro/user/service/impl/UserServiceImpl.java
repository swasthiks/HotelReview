package com.micro.user.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.user.service.Exceptions.ResourceNotFoundException;
import com.micro.user.service.External.Services.HotelSevice;
import com.micro.user.service.entities.Hotel;
import com.micro.user.service.entities.Rating;
import com.micro.user.service.entities.User;
import com.micro.user.service.repositories.UserRepository;
import com.micro.user.service.services.UserService;
import com.netflix.discovery.converters.Auto;

import ch.qos.logback.classic.Logger;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private HotelSevice hotelSevice;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger=(Logger) LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		//Unique userID
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given ID not found on server"+userId));
		//fetch rating of above user from RatingService
		
		//http://localhost:8082/rating/users/ff18ace9-b912-4702-b2ae-51ac4421429d
		Rating[] ratingsOfUsers=restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingsOfUsers);
		List<Rating> ratings=	Arrays.stream(ratingsOfUsers).toList();
		List<Rating> ratingList=ratings.stream().map(rating->{
			//api call to get hotel service to get the hotel
			
			//restTemplate
//			ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel=forEntity.getBody();
//			logger.info("response status code:{}",forEntity.getStatusCode());
			
			//Feign
			
			Hotel hotel=hotelSevice.getHotel(rating.getHotelId());
			//set hotel to rating
			rating.setHotel(hotel);
			//return rating
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
