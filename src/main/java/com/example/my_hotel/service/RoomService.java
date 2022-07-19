package com.example.my_hotel.service;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        return parsingRoomDTO(bookList);
    }

    public List<RoomDTO> getFreeRooms(Date date1, Date date2, int count) {
        List<Room> room2=roomRepository.findByIdRoom(date1, date2, count);
        return parsingRoomDTO(room2);
    }
    public boolean addRoom() {
        return true;
    }

    public RoomDTO getId(int id) {
        try {
            Room room_op = roomRepository.findById(id).orElseThrow();
            return pars(room_op);
        }catch (Exception e ) {
            return new RoomDTO();
        }

    }

    public void deleteRoom(int id ) {
        roomRepository.deleteById(id);
    }

    private RoomDTO pars(Room room) {
        return RoomDTO.builder()
                .id_room(room.getId_room())
                .floor(room.getFloor())
                .image(room.getImage())
                .depiction(room.getDepiction())
                .id_classification(room.getClassificationroom().getId_classification())
                .classification_room(room.getClassificationroom().getClassification_room())
                .cost(room.getClassificationroom().getCost())
                .number_beds((room.getClassificationroom().getNumber_beds()))
                .build();

    }
    private List<RoomDTO> parsingRoomDTO(List<Room> list) {
        List<RoomDTO> roomDTOs = new ArrayList<>();

        for (Room room : list) {
            roomDTOs.add(RoomDTO.builder()
                    .id_room(room.getId_room())
                    .floor(room.getFloor())
                    .image(room.getImage())
                    .depiction(room.getDepiction())
                    .id_classification(room.getClassificationroom().getId_classification())
                    .classification_room(room.getClassificationroom().getClassification_room())
                    .cost(room.getClassificationroom().getCost())
                    .number_beds((room.getClassificationroom().getNumber_beds()))
                    .build());
        }

        return roomDTOs;
    }
}
