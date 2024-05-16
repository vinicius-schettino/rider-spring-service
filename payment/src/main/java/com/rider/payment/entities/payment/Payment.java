package com.rider.payment.entities.payment;

import com.rider.payment.entities.paymentMethods.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;

    @Column
    private Date paymentDate;

    @Column
    private PaymentStatus paymentStatus;

    public Payment(Double amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, Date date) {
    }
}
