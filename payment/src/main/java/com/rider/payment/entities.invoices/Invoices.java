package com.rider.payment.entities.invoices;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table

public class Invoices {
    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(nullable = false)
    private Double Amount;

    @Column(nullable = false)
    private String PaymentMethod;

    @Column(nullable = false)
    private String PaymentStatus;

    @Column(nullable = false)
    private String InvoiceType;

    @Column(nullable = false)
    private String UserName;

    @Column(nullable = false)
    private String DriverName;

    @Column(nullable = false)
    private Date RideDate;
}
