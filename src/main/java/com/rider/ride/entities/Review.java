package com.rider.ride.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ratting", nullable = false)
    private Integer rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Ride ride;

    public UUID getId(){
        return this.id;
    }

    public Integer getRating(){ return this.rating; }

    public String getComment(){ return this.comment; }

    public Ride getRide(){ return this.ride; }

    public void setRating(int rating){ this.rating = this.rating; }

    public void setComment(String comment){
        this.comment = this.comment;
    }

    public void setRide(Ride ride){ this.ride = ride; }

}
