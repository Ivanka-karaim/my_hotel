package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Custom {

    @Id
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;

    @OneToOne
    @Column(name="IPN")
    private Client IPN;

    @ManyToOne
    @Column(name="id_room")
    private Room room;

    @NotNull
    private LocalDate date_settle;

    @NotNull
    private LocalDate date_departure;

    @NotNull
    private int number_inhabitants;

    @Min(0)
    private float cost_additional_services;

    @NotNull
    @Min(0)
    private float total_cost;

    @ManyToOne
    @Column(name="id_employee")
    private Employee employee;

    @ManyToOne
    @Column(name = "id_classification")
    private Classificationroom classificationroom;

}
