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
public class Client {
    @Id
    @Size(max=10)
    private String IPN;

    @NotNull
    @Size(max = 20)
    private String surname;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    private String patronymic;

    @NotNull
    @Size(max=13)
    private String phone_number;

    @NotNull
    @Size(max=30)
    private String email;

    private Integer  bonus_card;

    @ManyToOne
    @JoinColumn(name ="id_response")
    private Response id_response ;


    public Client(String IPN, String surname, String name, String patronymic, String phone_number, String email) {
        this.IPN=IPN;
        this.surname=surname;
        this.name=name;
        this.patronymic=patronymic;
        this.phone_number=phone_number;
        this.email=email;
    }
}