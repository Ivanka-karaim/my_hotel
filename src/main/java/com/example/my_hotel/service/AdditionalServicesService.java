package com.example.my_hotel.service;

import com.example.my_hotel.dto.AdditionalServicesDTO;
import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.AdditionalServices;
import com.example.my_hotel.model.Custom;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.AdditionalServicesRepository;
import com.example.my_hotel.repository.CustomRepository;
import com.example.my_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdditionalServicesService {
    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    public AdditionalServices getById(int id) {
        Optional<AdditionalServices> additionalServices_op = additionalServicesRepository.findById(id);
        return additionalServices_op.orElseGet(AdditionalServices::new);
    }

    public List<AdditionalServicesDTO> getAllAdditionalServices() {
        List<AdditionalServices> additionalServicesList = additionalServicesRepository.findAll();
        return parsingAdditionalServicesInAdditionalServicesDTO(additionalServicesList);
    }

    private List<AdditionalServicesDTO> parsingAdditionalServicesInAdditionalServicesDTO(List<AdditionalServices> list) {
        List<AdditionalServicesDTO> additionalServicesDTOS = new ArrayList<>();

        for (AdditionalServices additionalServices : list) {
            additionalServicesDTOS.add(AdditionalServicesDTO.builder()
                    .id_service(additionalServices.getId_service())
                    .type_service(additionalServices.getType_service())
                    .price(additionalServices.getPrice())
                    .build());
        }
        return additionalServicesDTOS;
    }
}
