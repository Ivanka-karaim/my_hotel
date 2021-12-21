package com.example.my_hotel.service;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room getById(int id) {
        Optional<Room> room_op = roomRepository.findById(id);
        return room_op.orElseGet(Room::new);
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> bookList = roomRepository.findAll();
        return parsingBookInBookDTO(bookList);
    }

    private List<RoomDTO> parsingBookInBookDTO(List<Room> list) {
        List<RoomDTO> roomDTOs = new ArrayList<>();

        for (Room room : list) {
            roomDTOs.add(RoomDTO.builder()
                    .id_room(room.getId_room())
                    .floor(room.getFloor())
                    .employment(room.isEmployment())
                    .image(room.getImage())
                    .depiction(room.getDepiction())
                    .id_classification(room.getClassificationroom().getId_classification())
                    .classification_room(room.getClassificationroom().getClassification_room())
                    .cost(room.getClassificationroom().getCost())
                    .number_beds((room.getClassificationroom().getNumber_beds()))
                    .number_rooms(room.getClassificationroom().getNumber_rooms())
                    .build());
        }

        return roomDTOs;
    }
}
