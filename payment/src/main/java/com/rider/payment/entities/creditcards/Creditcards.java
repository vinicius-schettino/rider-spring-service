package com.rider.payment.entities.creditcards;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Creditcards {
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

}
