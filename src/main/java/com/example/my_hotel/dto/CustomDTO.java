package com.example.my_hotel.dto;

//import com.example.my_hotel.model.Client;
import com.example.my_hotel.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    private int id_order;
//    private Client IPN;
    private Room id_room;
    private Date date_settle;
    private Date date_departure;
    private int number_inhabitants;
    private float cost_additional_services;
    private float total_cost;

}