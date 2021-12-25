package com.example.my_hotel.repository;

import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Classificationroom;
import com.example.my_hotel.model.Client;
import com.example.my_hotel.model.Custom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findAll();

    Optional<Booking> getById(int id_booking);
}
