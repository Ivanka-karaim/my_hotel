package com.example.my_hotel.service;

import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.Order_ServicesDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.*;
import com.example.my_hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Order_ServicesService {
    @Autowired
    private Order_ServicesRepository order_ServicesRepository;

    @Autowired
    private  AdditionalServicesService additionalServicesService;

    public boolean addOrderService(Custom custom, List<AdditionalServices> services) {
        System.out.println(custom);
        for(AdditionalServices additionalServices:services) {
            Order_Services order_services = Order_Services.builder()
                    .id_order(custom)
                    .id_service(additionalServices)
                    .build();
            order_ServicesRepository.save(order_services);
        }
        return true;
    }

    public float getCost(List<Integer> services) {
        float cost = 0;
        for(Integer service: services) {
            cost += additionalServicesService.getById(service).getPrice();
        }

        return  cost;
    }

    public Order_Services getById(int id) {
        Optional<Order_Services> custom_op = order_ServicesRepository.findById(id);
        return custom_op.orElseGet(Order_Services::new);
    }

    public List<Order_ServicesDTO> getAllOrder_Services() {
        List<Order_Services> customList = order_ServicesRepository.findAll();
        return parsingCustomInCustomDTO(customList);
    }


    private List<Order_ServicesDTO> parsingCustomInCustomDTO(List<Order_Services> list) {
        List<Order_ServicesDTO> customDTOS = new ArrayList<>();

        for (Order_Services order_Services : list) {
            customDTOS.add(Order_ServicesDTO.builder()
                    .id_order_services(order_Services.getId_order_services())
                    .id_order(order_Services.getId_order())
                    .id_service(order_Services.getId_service())
                    .build());
        }

        return customDTOS;
    }
}
