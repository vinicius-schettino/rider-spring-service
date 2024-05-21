package com.rider.ride.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ride ride;

    @Column(name = "comment", nullable = false)
    private String comment;

    public UUID getId(){
        return this.id;
    }

    public Ride getRide(){
        return this.ride;
    }

    public String getComment(){
        return this.comment;
    }

    public void setRide(){
        this.ride = ride;
    }

    public void setComment(){
        this.comment = comment;
    }
}
