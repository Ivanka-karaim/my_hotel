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
    private int id;

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
    private String phone_number;

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


    public Employees(String surname, String fullname, String patron, int ipn, Date birth_date, String email, String phone_number, String workbook, String password_employee, Occupations id_occupation, Schedules id_schedule) {
        this.surname = surname;
        this.fullname = fullname;
        this.patron = patron;
        this.ipn = ipn;
        this.birth_date = birth_date;
        this.email = email;
        this.phone_number = phone_number;
        this.workbook = workbook;
        this.password_employee = password_employee;
        this.id_occupation = id_occupation;
        this.id_schedule = id_schedule;
    }
}
