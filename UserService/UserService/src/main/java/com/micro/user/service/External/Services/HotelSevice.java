package com.micro.user.service.External.Services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.user.service.entities.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelSevice {
	@GetMapping("/hotel/{hotelId}")
	Hotel getHotel(@PathVariable String hotelId);
}
