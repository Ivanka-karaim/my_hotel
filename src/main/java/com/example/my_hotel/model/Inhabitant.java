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
public class Inhabitant {
    @Id
    @Size(max = 13)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String phone_number;

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
    private LocalDate birthday;
}
