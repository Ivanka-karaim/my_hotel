package com.example.my_hotel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_employee;
    private String surname;
}
