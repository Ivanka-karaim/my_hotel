package com.example.my_hotel.dto;

//import com.example.my_hotel.model.Client;
import com.example.my_hotel.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order_ServicesDTO {
    private int id_order_services;
    private Custom id_order;
    private AdditionalServices id_service;
}
