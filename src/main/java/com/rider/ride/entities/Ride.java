package com.rider.ride.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@SequenceGenerator(name="seq_generator", sequenceName = "seq_ride_id", initialValue = 21, allocationSize=1)

public class Ride {
    @Id
    @Column(name = "id", nullable = false, precision = 3, scale = 0)
    @GeneratedValue(generator = "seq_generator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "driver_id", nullable = false)
    private Integer driver;

    @Column(name = "passenger_id", nullable = false)
    private Integer passenger;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String state;

    @Column(name = "boarding_location", nullable = false)
    private String boardingLocation;

    @Column(name = "destiny_location", nullable = false)
    private String destinyLocation;

    public Integer getId(){
        return this.id;
    }

    public Integer getDriver(){
        return this.driver;
    }

    public Integer getPassenger(){
        return this.passenger;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public String getState(){
        return this.state;
    }

    public String getBoardingLocation(){
        return this.boardingLocation;
    }

    public String getDestinyLocation(){
        return this.destinyLocation;
    }

    public void setDriver(){
        this.driver = driver;
    }

    public void setPassenger(){
        this.passenger = passenger;
    }

    public void setPrice(){
        this.price = price;
    }

    public void setState(){
        this.state = state;
    }

    public void setBoardingLocation(){
        this.boardingLocation = boardingLocation;
    }

    public void setDestinyLocation(){
        this.destinyLocation = destinyLocation;
    }
}
