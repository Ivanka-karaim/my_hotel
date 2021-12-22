package com.example.my_hotel.service;

import com.example.my_hotel.model.Booking;
import com.example.my_hotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public boolean addBooking(Booking booking) {
        Booking book = Booking.
                builder()
                .room(booking.getRoom())
                .date_arrival(booking.getDate_arrival())
                .date_departure(booking.getDate_departure())
                .name(booking.getName())
                .phone_number(booking.getPhone_number())
                .count_people(booking.getCount_people())
                .build();

        bookingRepository.save(book);
        return true;

    }
}
