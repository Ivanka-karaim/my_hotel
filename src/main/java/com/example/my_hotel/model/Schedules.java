package com.example.my_hotel.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="schedules")
public class Schedules {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date datebegin;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateend;
//    @NotNull
//    private int id_schedule;
//
//    @NotNull
//    private int id_week = 1;
//
//    @NotNull
//    boolean sun = false;
//
//    @NotNull
//    boolean mon = false;
//
//    @NotNull
//    boolean tue = false;
//
//    @NotNull
//    boolean wed = false;
//
//    @NotNull
//    boolean thu = false;
//
//    @NotNull
//    boolean fri = false;
//
//    @NotNull
//    boolean sat = false;
}
