package com.rider.payment.repositories;

import com.rider.payment.entities.payment.Payment;
import com.rider.payment.entities.payment.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment findPaymentById(UUID paymentId);

    List<Payment> findPaymentByPaymentStatus(PaymentStatus status);
}
