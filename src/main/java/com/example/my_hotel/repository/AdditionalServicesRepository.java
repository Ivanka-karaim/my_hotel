package com.example.my_hotel.repository;
import com.example.my_hotel.model.AdditionalServices;
import com.example.my_hotel.model.Custom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdditionalServicesRepository extends CrudRepository<AdditionalServices, Integer>  {
    List<AdditionalServices> findAll();

    @Query("SELECT a FROM AdditionalServices a WHERE a.id_service=(SELECT os.id_service FROM Order_Services os WHERE os.id_order = ?1)")
    List<AdditionalServices> getPriceServicesByIdCustom(@Param("curr_id_order") Custom id_order);

//    @Modifying
//    @Query("DELETE t FROM AdditionalServices t WHERE t.id_service =:param")
//    void deleteValue(@Param("param") int id_service);
}
