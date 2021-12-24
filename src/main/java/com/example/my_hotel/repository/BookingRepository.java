package com.example.my_hotel.repository;

import com.example.my_hotel.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findAll();

    Optional<Booking> getById(int id_booking);
}
