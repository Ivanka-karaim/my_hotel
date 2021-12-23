package com.example.my_hotel.repository;

import com.example.my_hotel.model.Employees;
import com.example.my_hotel.model.Occupations;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OccupationsRepository extends CrudRepository<Occupations, Integer> {
    //List<Occupations> findById(int id);
    Optional<Occupations> findById(int id);
}
