package com.example.my_hotel.repository;
import com.example.my_hotel.model.Client;
import com.example.my_hotel.model.Custom;
import com.example.my_hotel.model.Response;
import com.example.my_hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> findAll();
//    @Query ("SELECT * FROM response INNER JOIN client using(id_response)");
//    List<Client> findIdResponse(@Param(id_response))

//    @Query("SELECT Response FROM Response WHERE Response.id = (SELECT id_response FROM Client WHERE id=:number)")
//    List<Response> findResponseById(@Param("number") int number);
}