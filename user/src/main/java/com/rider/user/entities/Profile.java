package com.rider.user.entities;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Entity
@Table
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Date date_of_birth;

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getDate_of_birth() {
        return this.date_of_birth;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(Date date_of_birth) {
        this.date_of_birth =  date_of_birth;
    }

}
