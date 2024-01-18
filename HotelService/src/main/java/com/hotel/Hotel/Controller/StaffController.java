package com.hotel.Hotel.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Staffs")
public class StaffController {
	@GetMapping
public ResponseEntity<List<String>> getStaffs(){
List<String> list= Arrays.asList("Ram","Krishna","Laxman");
return new ResponseEntity<>(list,HttpStatus.OK);
}
}
