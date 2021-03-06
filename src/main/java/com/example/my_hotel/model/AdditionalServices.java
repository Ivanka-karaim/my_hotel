package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AdditionalServices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_service;

    @NotNull
    @Size(max = 50)
    private String type_service;

    @NotNull
    @Min(0)
    private float price;

    public AdditionalServices(String type_service, float price){
        this.type_service=type_service;
        this.price=price;
    }

}