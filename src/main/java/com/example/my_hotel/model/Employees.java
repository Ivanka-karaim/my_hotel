package com.example.my_hotel.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_employee;
    @NotNull
    @Size(max = 50)
    private String surname;    
    @NotNull
    @Size(max = 50)
    private String fullname;    
    @NotNull
    @Size(max = 50)
    private String patron;
    @NotNull
    private int ipn;
}
