package com.rider.payment.repositories;

import com.rider.payment.entities.paymentMethods.PaymentMethod;
import com.rider.payment.entities.paymentMethods.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {

    PaymentMethod findByPaymentType(PaymentType paymentType);

    boolean existsByName(String name);

    List<PaymentMethod> findAllByPaymentType(PaymentType paymentType);

    long countByPaymentType(PaymentType paymentType);

}

