package com.rider.ride.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "driver_id", nullable = false)
    private Integer driver;

    @Column(name = "passenger_id", nullable = false)
    private Integer passenger;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideState state = RideState.WAITING_DRIVER;

    @Column(nullable = false)
    private Float boardingLocation_X;

    @Column(nullable = false)
    private Float boardingLocation_Y;

    @Column(nullable = false)
    private Float destinationLocation_X;

    @Column(nullable = false)
    private Float destinationLocation_Y;

    @OneToMany(mappedBy = "ride")
    private List<Review> review;

    public UUID getId(){
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

    public RideState getState(){
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

    public List<Review> getReview(){
        return this.review;
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

    public void setReview(){
        this.review = review;
    }
}
