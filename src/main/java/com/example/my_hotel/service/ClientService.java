package com.example.my_hotel.service;

import com.example.my_hotel.dto.AdditionalServicesDTO;
import com.example.my_hotel.dto.ClientDTO;
import com.example.my_hotel.model.AdditionalServices;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Client;
import com.example.my_hotel.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    public List<ClientDTO> getAllClient;
    @Autowired
    private ClientRepository clientRepository;

    public Client getById(String id) {
        Optional<Client> client_op =clientRepository.findById(id);
        return client_op.orElseGet(Client::new);
    }

    public List<ClientDTO> getAllClient() {
        List<Client> clientList = clientRepository.findAll();
        return parsingClientInClientDTO(clientList);
    }


    public void deleteClient(String id ) {
        clientRepository.deleteById(id);
    }

    private List<ClientDTO> parsingClientInClientDTO(List<Client> list) {
        List<ClientDTO> clientDTOS = new ArrayList<>();

        for (Client client : list) {
            clientDTOS.add(ClientDTO.builder()
                    .IPN(client.getIPN())
                    .surname(client.getSurname())
                    .name(client.getName())
                    .patronymic(client.getPatronymic())
                    .phone_number(client.getPhone_number())
                    .email(client.getEmail())
                    .bonus_card(client.getBonus_card())
                    .build());
        }
        return clientDTOS;
    }

//    public boolean addClient(Client client) {
//        System.out.println(client.getDate_arrival());
//        Client client = clientRepository.
//                .room(booking.getRoom())
//                .date_arrival(booking.getDate_arrival())
//                .date_departure(booking.getDate_departure())
//                .name(booking.getName())
//                .phone_number(booking.getPhone_number())
//                .count_people(booking.getCount_people())
//                .build();
//
//        bookingRepository.save(book);
//        return true;

//    }
}

