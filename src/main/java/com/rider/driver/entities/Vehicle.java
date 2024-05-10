package com.rider.driver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(name = "MODEL_YEAR", nullable = false)
    private Double modelYear;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(name = "TYPE", nullable = false)
    private String vehicleType;

    @Column(name = "COLOR", nullable = false)
    private String vehicleColor;

    @Column(nullable = false, unique = true)
    private String document;

    @OneToOne
    @JoinColumn(name = "DRIVER_ID", unique = true)
    private Driver driver;

}
