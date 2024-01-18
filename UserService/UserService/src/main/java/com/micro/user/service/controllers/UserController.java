package com.micro.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.user.service.entities.User;
import com.micro.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

//create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
//single user get
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "RATINGHOTELBREAKER", fallbackMethod = "RATINGHOTELFALLBACK")
//	@Retry(name="ratinghotelservice",fallbackMethod = "RATINGHOTELFALLBACK")
	@RateLimiter(name="UserRateLimiter",fallbackMethod = "RATINGHOTELFALLBACK")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

//CREATING CALLBACK METHOD FOR CIRCUITBREAKER
	public ResponseEntity<User> RATINGHOTELFALLBACK(String userId, Exception ex) {
		ex.printStackTrace();
		User user = User.builder().email("dummy@gmail.com").name("Dummy")
				.about("This user is created dummy because some service is down").userId("141234").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

//all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> alluser = userService.getAllUser();
		return ResponseEntity.ok(alluser);
	}
}