package com.rider.payment.services.paymentMethods;

import com.rider.payment.entities.paymentMethods.PaymentMethod;
import com.rider.payment.entities.paymentMethods.PaymentType;

public class PIXPaymentMethod implements GenericPaymentMethod {
    private final PaymentType paymentType = PaymentType.PIX;

    public PaymentMethod buildPaymentMethod() {
        return new PaymentMethod(this.paymentType);
    }
}

