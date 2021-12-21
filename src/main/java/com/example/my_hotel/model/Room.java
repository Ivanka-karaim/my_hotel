package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_room;

    @NotNull
    private boolean employment;

    @Min(1)
    private int floor;

    @Lob
    private byte[] image;

    private String depiction;

    @ManyToOne
    @JoinColumn(name="id_employee")
    private Employees employee;

    @ManyToOne
    @JoinColumn(name = "id_classification")
    private Classificationroom classificationroom;

}
