package com.example.my_hotel.service;

import com.example.my_hotel.model.Booking;
import com.example.my_hotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private BookingRepository bookingRepository;

