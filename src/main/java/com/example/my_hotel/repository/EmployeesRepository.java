package com.example.my_hotel.repository;

import com.example.my_hotel.model.Employees;
import com.example.my_hotel.model.Occupations;
import com.example.my_hotel.model.Schedules;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends CrudRepository<Employees, Integer> {
    List <Employees> findAll();
    Optional<Employees> findById(int id);

    //@Query("SELECT o.occupation FROM Occupations o WHERE o.id = (SELECT e.id_occupation FROM Employees e WHERE e.id=:number)")
    //List<Occupations> findOccupationById(@Param("number") int number);


    //@Query("SELECT Schedules FROM Schedules WHERE Schedules.id = (SELECT id_schedule FROM Employees WHERE id=:number)")
    //List<Schedules> findScheduleById(@Param("number") int number);
}
