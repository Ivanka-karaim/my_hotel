package com.example.my_hotel.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

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

    @NotNull
    private Date birth_date;

    @Size(max = 50)
    @NotNull
    private String email;

    @Size(max = 13)
    @NotNull
    private String phone_num;

    @Size(max = 10)
    @NotNull
    private String workbook;

    @Size(max = 20)
    @NotNull
    private String password_employee;

    @ManyToOne
    @JoinColumn(name = "id_occupation")
    private Occupations id_occupation;

    @ManyToOne
    @JoinColumn(name = "id_schedule")
    private Schedules id_schedule;
}
