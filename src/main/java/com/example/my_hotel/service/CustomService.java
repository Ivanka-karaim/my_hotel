package com.example.my_hotel.service;

import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.*;
import com.example.my_hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomService {
    @Autowired
    private CustomRepository customRepository;

    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    @Autowired
    private ClassificationroomRepository classificationroomRepository;

    @Autowired
    private  Order_ServicesService order_servicesService;

    @Autowired
    private  AdditionalServicesService additionalServicesService;


    public Custom getById(int id) {
        Optional<Custom> custom_op = customRepository.findById(id);
        return custom_op.orElseGet(Custom::new);
    }

    public List<CustomDTO> getAllCustoms() {
        List<Custom> customList = customRepository.findAll();
        return parsingCustomInCustomDTO(customList);
    }

    public boolean addCustom(Client IPN, Booking booking_id, List<Integer> services) {
        float cost = order_servicesService.getCost(services);
        cost += booking_id.getPrice();
        List<Custom> customs = customRepository.findAll();
        int max = 0;
        Custom custom = Custom.
                builder()
                .IPN(IPN)
                .id_booking(booking_id)
                .cost_additional_services(order_servicesService.getCost(services))
                .total_cost(cost)
                .paid(false)
                .build();
        System.out.println(max);
        customRepository.save(custom);

        for(Custom custom1: customs) {
            System.out.println(custom1.getId_order());
            if(max<custom1.getId_order()) {
                max = custom1.getId_order();
            }
        }
//        order_servicesService.addOrderService(this.getById(max),services
//                .stream()
//                .map(additionalServicesService::getById)
//                .collect(Collectors.toList()));
        //add additional services
      return true;
    }

    public float getCostServices(int id_order){
        float cost = 0f;
        Custom d = customRepository.findById(id_order).orElseThrow();

        List<AdditionalServices> priceService = additionalServicesRepository.getPriceServicesByIdCustom(d);
        for(AdditionalServices service: priceService) {
            cost +=service.getPrice();
        }
        cost += d.getId_booking().getPrice();

        return cost;
    }

    public float getCost(int id_order){
        Optional<Classificationroom> priceRoom = classificationroomRepository.getPriceRoomByIdCustom(id_order);
        float price = priceRoom.get().getCost();
        System.out.println(price);
        price+=getCostServices(id_order);
        System.out.println(price);
        return price;
    }

    private List<CustomDTO> parsingCustomInCustomDTO(List<Custom> list) {
        List<CustomDTO> customDTOS = new ArrayList<>();

        for (Custom custom : list) {
            customDTOS.add(CustomDTO.builder()
                    .id_order(custom.getId_order())
                    .IPN(custom.getIPN())
                    .id_booking(custom.getId_booking())
                    .cost_additional_services(custom.getCost_additional_services())
                    .total_cost(custom.getTotal_cost())
                    .paid(custom.isPaid())
                    .build());
        }

        return customDTOS;
    }
}
