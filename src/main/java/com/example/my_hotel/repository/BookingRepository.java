package com.example.my_hotel.repository;

import com.example.my_hotel.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findAll();
}
