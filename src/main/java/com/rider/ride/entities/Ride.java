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

    @Column(nullable = false)
    private Float boardingLocation_X;

    @Column(nullable = false)
    private Float boardingLocation_Y;

    @Column(nullable = false)
    private Float destinationLocation_X;

    @Column(nullable = false)
    private Float destinationLocation_Y;

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

    public Float getBoardingLocation_X(){
        return this.boardingLocation_X;
    }

    public Float getBoardingLocation_Y(){
        return this.boardingLocation_Y;
    }

    public Float getDestinationLocation_X(){
        return this.destinationLocation_X;
    }

    public Float getDestinationLocation_Y(){
        return this.destinationLocation_Y;
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

    public void setBoardingLocation_X(){
        this.boardingLocation_X = boardingLocation_X;
    }

    public void setBoardingLocation_Y(){
        this.boardingLocation_Y = boardingLocation_Y;
    }

    public void setDestinationLocation_X(){
        this.destinationLocation_X = destinationLocation_X;
    }

    public void setDestinationLocation_Y(){
        this.destinationLocation_Y = destinationLocation_Y;
    }
}
