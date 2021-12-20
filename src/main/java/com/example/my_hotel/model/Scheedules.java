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
@Table(name="work_schedule")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateBegin;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateEnd;
}
