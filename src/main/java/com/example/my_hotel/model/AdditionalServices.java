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

public class AdditionalServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_service;

    @NotNull
    @Size(max = 50)
    private String type_service;

    @NotNull
    @Min(0)
    private Date date_departure;

}