package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Custom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;

    @OneToOne
    @JoinColumn(name = "IPN")
    private Client IPN;

    @ManyToOne
    @JoinColumn(name="id_booking")
    private Booking id_booking;

    @Min(0)
    private float cost_additional_services;

    @NotNull
    @Min(0)
    private float total_cost;

    @NotNull
    private boolean paid;

}