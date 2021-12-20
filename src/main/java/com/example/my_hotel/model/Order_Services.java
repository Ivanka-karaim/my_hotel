package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Order_Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order_service;

    @ManyToOne
    @JoinColumn(name="id_order")
    private Custom custom;

    @ManyToOne
    @JoinColumn(name="id_service")
    private AdditionalServices additionalServices;

}