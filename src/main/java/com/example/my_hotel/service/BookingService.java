package com.example.my_hotel.service;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public boolean addBooking(Booking booking) {
        System.out.println(booking.getDate_arrival());
        Booking book = Booking.
                builder()
                .room(booking.getRoom())
                .date_arrival(booking.getDate_arrival())
                .date_departure(booking.getDate_departure())
                .name(booking.getName())
                .phone_number(booking.getPhone_number())
                .count_people(booking.getCount_people())
                .price(booking.getPrice())
                .build();
        bookingRepository.save(book);
        return true;

    }
    public List<Booking> getAllBooks() {
        List<Booking> bookList = bookingRepository.findAll();
        return bookList;
    }
}
