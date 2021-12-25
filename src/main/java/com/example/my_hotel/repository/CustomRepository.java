package com.example.my_hotel.repository;
import com.example.my_hotel.model.AdditionalServices;
import com.example.my_hotel.model.Classificationroom;
import com.example.my_hotel.model.Custom;
import com.example.my_hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomRepository extends CrudRepository<Custom, Integer> {
    List<Custom> findAll();

    @Query("SELECT c.IPN FROM Client c")
    List<String> getExistIPN();

    @Query("SELECT c.id_booking FROM Booking c")
    List<Integer> getExistId_booking();

//    @Query("SELECT os.id_service WHERE os.id_order=:curr.id_order")
//    List<Custom> getId_serviceByIdCustom(@Param("curr_id_order") int id_order);

//    @Query("SELECT c FROM Custom c WHERE c.id_booking = (SELECT b.id_booking FROM Booking b WHERE b.date_arrival=now() AND b.phone_number=:current_number)")
//    List<Custom> findCustomByNumberDate(@Param("current_number") String current_number);




//    @Query("SELECT c, b FROM Custom c JOIN c.id_booking b")
//    List<Custom> joinCustomBooking();
}