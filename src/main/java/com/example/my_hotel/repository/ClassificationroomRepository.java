package com.example.my_hotel.repository;


import com.example.my_hotel.model.Classificationroom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassificationroomRepository extends CrudRepository<Classificationroom, Integer> {
    List<Classificationroom> findAll();


}
