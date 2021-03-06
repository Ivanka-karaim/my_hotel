package com.example.my_hotel.repository;
import com.example.my_hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface RoomRepository extends CrudRepository<Room, Integer> {
    List<Room> findAll();
    List<Room> findAllByFloor(int floor);
    Optional<Room> findById(int id);
@Query("SELECT r FROM Room r WHERE r.id_room != ALL(SELECT b.room FROM Booking b WHERE b.date_arrival<:date2 AND b.date_departure>:date1) " +
        "AND id_classification = ANY(SELECT id_classification FROM Classificationroom WHERE number_beds>=:number)")
List<Room> findByIdRoom(@Param("date1") Date date1, @Param("date2") Date date2, @Param("number") int number);
}