package com.example.my_hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Order_Inhabitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order_inhabitant;

    @ManyToOne
    @JoinColumn(name ="phone_number")
    private Inhabitant phone_number;

    @OneToOne
    @JoinColumn(name ="id_order")
    private Custom id_order;

}