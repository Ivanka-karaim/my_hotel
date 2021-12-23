package com.example.my_hotel.repository;

import com.example.my_hotel.model.Employees;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeesRepository extends CrudRepository<Employees, Integer> {
    List <Employees> findAll();
}
