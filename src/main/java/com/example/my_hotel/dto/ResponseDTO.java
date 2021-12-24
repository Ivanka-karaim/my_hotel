package com.example.my_hotel.dto;

//import com.example.my_hotel.model.Client;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    private int id_response;
    private int mark;
    private String comment;

}
