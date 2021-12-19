package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_booking;

    @ManyToOne
    @Column(name = "id_room")
    private Room room;

    @NotNull
    private LocalDate date_arrival;

    @NotNull
    private LocalDate date_departure;

    @NotNull
    @Size(max=13)
    private String phone_number;


}
