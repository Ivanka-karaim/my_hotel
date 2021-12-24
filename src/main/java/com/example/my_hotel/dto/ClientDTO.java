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
public class ClientDTO {
    private String IPN;
    private String surname;
    private String name;
    private String patronymic;
    private String phone_number;
    private String email;
    private int id_response;
    private int bonus_card;
}
