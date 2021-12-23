package com.example.my_hotel.repository;


import com.example.my_hotel.model.Classificationroom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassificationroomRepository extends CrudRepository<Classificationroom, Integer> {
    List<Classificationroom> findAll();
}
