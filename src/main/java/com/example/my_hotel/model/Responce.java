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
public class Responce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_responce;

    @NotNull
    private int mark;

    @NotNull
    @Size(max=255)
    private String comment;

}
