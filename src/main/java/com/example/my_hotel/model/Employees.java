package com.example.my_hotel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
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