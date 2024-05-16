package com.rider.driver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int model_Year;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String Type;

    @Column(nullable = false)
    private String Color;

    @Column(nullable = false, unique = true)
    private String document;

    @JsonBackReference
    @OneToOne
    @JoinColumn(unique = true)
    private Driver driver;

}