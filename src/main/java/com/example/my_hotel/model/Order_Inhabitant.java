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
    @ManyToOne
    @Size(max = 13)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String phone_number;

    @ManyToOne
    @NotNull
    private int id_order;

}
