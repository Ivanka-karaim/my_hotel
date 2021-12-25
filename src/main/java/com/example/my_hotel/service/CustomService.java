package com.example.my_hotel.service;

import com.example.my_hotel.dto.ClientDTO;
import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.*;
import com.example.my_hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomService {
    @Autowired
    private CustomRepository customRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    @Autowired
    private ClassificationroomRepository classificationroomRepository;

    @Autowired
    private  Order_ServicesService order_servicesService;

    @Autowired
    private  AdditionalServicesService additionalServicesService;

    @Autowired
    private ClientService clientService;

    public Custom getById(int id) {
        Optional<Custom> custom_op = customRepository.findById(id);
        return custom_op.orElseGet(Custom::new);
    }

    public List<CustomDTO> getAllCustoms() {
        List<Custom> customList = customRepository.findAll();
        return parsingCustomInCustomDTO(customList);
    }

    public Integer addCustom(Client IPN, Booking booking_id) {
        float cost = 0f;
        if (IPN == null || booking_id == null) {
            return -1;
        }

        cost += booking_id.getPrice();
        List<Custom> customs = customRepository.findAll();
        Custom custom = Custom.
                builder()
                .IPN(IPN)
                .id_booking(booking_id)
                .cost_additional_services(0)
                .total_cost(cost)
                .paid(false)
                .build();
        customRepository.save(custom);
        return custom.getId_order();

    }

//    public boolean addCustom(Client IPN, Booking booking_id, List<Integer> services) {
//        float cost = order_servicesService.getCost(services);
//        cost += booking_id.getPrice();
//        List<Custom> customs = customRepository.findAll();
//        int max = 0;
//        Custom custom = Custom.
//                builder()
//                .IPN(IPN)
//                .id_booking(booking_id)
//                .cost_additional_services(order_servicesService.getCost(services))
//                .total_cost(cost)
//                .paid(false)
//                .build();
//        System.out.println(max);
//        customRepository.save(custom);
//
//        for(Custom custom1: customs) {
//            System.out.println(custom1.getId_order());
//            if(max<custom1.getId_order()) {
//                max = custom1.getId_order();
//            }
//        }
////        order_servicesService.addOrderService(this.getById(max),services
////                .stream()
////                .map(additionalServicesService::getById)
////                .collect(Collectors.toList()));
//        //add additional services
//      return true;
//    }

    public boolean addService(int id_service, List<Integer> services){
        order_servicesService.addOrderService(this.getById(id_service),services
                .stream()
                .map(additionalServicesService::getById)
                .collect(Collectors.toList()));
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

    public List<String> getIPNList(){
        return customRepository.getExistIPN();
    }

    public List<Integer> getId_bookingList(){
        return customRepository.getExistId_booking();
    }


//    public float getCost(int id_order){
//        float cost = 0f;
//        Custom d = customRepository.findById(id_order).orElseThrow();
//        List<Booking> priceRoom = bookingRepository.getPriceRoomByIdCustom(d);
//        for(Booking service: priceRoom) {
//            cost +=service.getPrice();
//        }
//        cost+=getCostServices(id_order);
//        return cost;
//    }

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
