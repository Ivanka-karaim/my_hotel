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
//    @Query("SELECT r FROM Room r WHERE r != ALL(SELECT b.room FROM Booking  b WHERE b.date_arrival<:date1 AND b.date_departure>:date2) " +
//           "AND r.classificationroom = ANY(SELECT c FROM Classificationroom c WHERE c.number_beds=:number)")
//    List<Room> findByIdRoom(@Param("date1") Date date1, @Param("date2") Date date2, @Param("number") int number);
//    @Query("SELECT r FROM Room r " +
//        "JOIN Booking b ON b.room=r " +
//        "WHERE b.date_arrival>:date1 AND b.date_departure<:date2 AND r.classificationroom = ANY(SELECT id_classification FROM Classificationroom WHERE number_beds=:number)")
//List<Room> findByIdRoom(@Param("date1") Date date1, @Param("date2") Date date2, @Param("number") int number);
//    @Query("SELECT room FROM Booking " +
//            "WHERE date_arrival<:date2 AND date_departure>:date1")
//    List<Room> findbook(@Param("date1") Date date1, @Param("date2") Date date2);

//    @Query("SELECT r FROM Room r " +
//            "INNER JOIN Classificationroom  ON id_classification=r.classificationroom" +
//            "WHERE r.classificationroom.number_beds=:number")
//    @Query("SELECT r " +
//            "FROM Room r, Classificationroom c " +
//            "WHERE r.classificationroom=c.id_classification AND c.number_beds=:number")
//    List<Room> findclass(@Param("number") int number);
@Query("SELECT r FROM Room r WHERE r.id_room != ALL(SELECT b.room FROM Booking b WHERE b.date_arrival<:date2 AND b.date_departure>:date1) " +
        "AND id_classification = ANY(SELECT id_classification FROM Classificationroom WHERE number_beds=:number)")
List<Room> findByIdRoom(@Param("date1") Date date1, @Param("date2") Date date2, @Param("number") int number);
}