package com.example.my_hotel.repository;
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

public interface CustomRepository extends CrudRepository<Custom, Integer>  {
    List<Custom> findAll();
//
//    @Query("SELECT * FROM Custom WHERE id_room = :Room")
//    List<Custom> findByIdRoom(@Param("Room") Integer Room);
}
