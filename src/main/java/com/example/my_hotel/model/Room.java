package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="room")
public class Room {
    public Room(int floor, String image, String depiction, Classificationroom classificationroom) {
        this.floor=floor;
        this.depiction=depiction;
        this.classificationroom=classificationroom;
        this.image=image;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_room;

    @Min(1)
    private int floor;

    @Size(max=20)
    private String image;

    private String depiction;

    @ManyToOne
    @JoinColumn(name = "id_classification")
    private Classificationroom classificationroom;

}
