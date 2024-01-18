package com.hotel.Hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.Hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
