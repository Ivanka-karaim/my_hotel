package com.example.my_hotel.repository;
import com.example.my_hotel.model.AdditionalServices;
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

public interface AdditionalServicesRepository extends CrudRepository<AdditionalServices, Integer>  {
    List<AdditionalServices> findAll();
}
