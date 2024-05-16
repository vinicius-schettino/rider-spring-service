package com.rider.payment.entities.invoice;

import com.rider.payment.entities.payment.PaymentStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class Invoice {
    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private InvoiceType invoiceType;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private Date rideDate;
}
