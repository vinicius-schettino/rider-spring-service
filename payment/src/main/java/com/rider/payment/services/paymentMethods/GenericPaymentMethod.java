package com.rider.payment.services.paymentMethods;


import com.rider.payment.entities.paymentMethods.PaymentMethod;
import com.rider.payment.entities.paymentMethods.PaymentType;

public interface GenericPaymentMethod {
    PaymentType paymentType = null;

    PaymentMethod buildPaymentMethod();
}
