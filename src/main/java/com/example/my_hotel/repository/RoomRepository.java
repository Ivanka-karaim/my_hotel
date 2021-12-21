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
//    @Query("SELECT r FROM room r WHERE id_room != ALL(SELECT id_room  FROM booking WHERE date_arrival<:date1 AND date_departure>:date2) " +
//           "AND id_classification = ANY(SELECT id_classification FROM classificationroom WHERE number_beds=:number)")
//    List<Room> testQueryAnnotationParams(@Param("date1") Date date1, @Param("date2") Date date2, @Param("number") int number);

}