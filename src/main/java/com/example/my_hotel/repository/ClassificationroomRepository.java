package com.example.my_hotel.repository;


import com.example.my_hotel.model.Classificationroom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassificationroomRepository extends CrudRepository<Classificationroom, Integer> {
    List<Classificationroom> findAll();

    @Query("SELECT c FROM Classificationroom c WHERE c.id_classification=(SELECT r.classificationroom FROM Room r "+
            "WHERE r.id_room=(SELECT b.room FROM Booking b WHERE b.id_booking=(SELECT c.id_booking FROM Custom c WHERE c.id_order=:curr_id_order))) ")
    Optional<Classificationroom> getPriceRoomByIdCustom(@Param("curr_id_order") int id_order);
}
