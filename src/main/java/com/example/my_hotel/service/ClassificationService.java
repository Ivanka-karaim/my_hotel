package com.example.my_hotel.service;

import com.example.my_hotel.model.Classificationroom;

import com.example.my_hotel.repository.ClassificationroomRepository;
import com.example.my_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClassificationService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ClassificationroomRepository classificationroomRepository;
    public Classificationroom getById(int id) {
        Optional<Classificationroom> room_op = classificationroomRepository.findById(id);
        return room_op.orElseGet(Classificationroom::new);
    }

    public List<Classificationroom> getAllClass() {
        return classificationroomRepository.findAll();
    }
    public void deleteClass(int id ) {
        classificationroomRepository.deleteById(id);
    }

}
