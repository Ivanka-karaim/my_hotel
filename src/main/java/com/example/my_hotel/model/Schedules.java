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
@Table(name="work_schedule")
public class Schedules {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateBegin;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateEnd;
}
