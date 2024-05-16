package com.rider.payment.entities.creditcard;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table


public class Creditcard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String holder;

    @Column(nullable = false)
    private String validationDate;

    @Column(nullable = false)
    private String code;

    // Gets & sets
    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getNumber() {return number;}

    public void setNumber(String number) {this.number = number;}

    public String getHolder() {return holder;}

    public void setHolder(String holder) {this.holder = holder;}

    public String getValidationDate() {return validationDate;}

    public void setValidationDate(String validationDate) {this.validationDate = validationDate;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

}
