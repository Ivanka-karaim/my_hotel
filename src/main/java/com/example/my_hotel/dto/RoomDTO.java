package com.example.my_hotel.dto;

import com.example.my_hotel.model.Classificationroom;
import com.example.my_hotel.model.Employees;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
    private int id_room;
    private int floor;
    private String image;
    private String depiction;
    private int id_classification;
    private String classification_room;
    private float cost;
    private int number_beds;
}
