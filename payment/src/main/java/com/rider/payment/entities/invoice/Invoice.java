package com.rider.payment.entities.invoice;
import com.rider.payment.entities.payment.PaymentStatus;
import com.rider.payment.entities.paymentMethods.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "INVOICE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private InvoiceType invoiceType;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private Date rideDate;
}
