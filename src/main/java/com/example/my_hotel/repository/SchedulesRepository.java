package com.example.my_hotel.repository;


import com.example.my_hotel.model.Schedules;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SchedulesRepository extends CrudRepository<Schedules, Integer> {
    //List<Occupations> findById(int id);
    Optional<Schedules> findById(int id);
}
