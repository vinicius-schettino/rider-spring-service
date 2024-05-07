// MOCK QUE SERÁ SUBSTITUIDO PELO CODIGO DO GRUPO PREFERIA GASTRONOMIA

package com.rider.driver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class MockRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(nullable = false)
    private String destination;
}
