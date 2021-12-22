package com.example.my_hotel.dto;

//import com.example.my_hotel.model.Client;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdditionalServicesDTO{
    private int id_service;
    private String type_service;
    private float price;
}
