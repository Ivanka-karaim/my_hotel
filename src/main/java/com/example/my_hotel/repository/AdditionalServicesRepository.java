package com.example.my_hotel.repository;
import com.example.my_hotel.model.AdditionalServices;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdditionalServicesRepository extends CrudRepository<AdditionalServices, Integer>  {
    List<AdditionalServices> findAll();


//    @Modifying
//    @Query("DELETE t FROM AdditionalServices t WHERE t.id_service =:param")
//    void deleteValue(@Param("param") int id_service);
}
