package com.example.my_hotel.service;

import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Custom;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.CustomRepository;
import com.example.my_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomService {
    @Autowired
    private CustomRepository customRepository;

    public Custom getById(int id) {
        Optional<Custom> custom_op = customRepository.findById(id);
        return custom_op.orElseGet(Custom::new);
    }

    public List<CustomDTO> getAllCustoms() {
        List<Custom> customList = customRepository.findAll();
        return parsingCustomInCustomDTO(customList);
    }

    private List<CustomDTO> parsingCustomInCustomDTO(List<Custom> list) {
        List<CustomDTO> customDTOS = new ArrayList<>();

        for (Custom custom : list) {
            customDTOS.add(CustomDTO.builder()
                    .id_order(custom.getId_order())
                    //.IPN(custom.getIPN())
                    .id_room(custom.getId_room())
                    .date_settle(custom.getDate_settle())
                    .date_departure(custom.getDate_departure())
                    .number_inhabitants(custom.getNumber_inhabitants())
                    .cost_additional_services(custom.getCost_additional_services())
                    .total_cost(custom.getTotal_cost())
                    .build());
        }

        return customDTOS;
    }
}
