package com.rider.payment.services.paymentMethods;

import com.rider.payment.dto.NewPaymentRequest;
import com.rider.payment.entities.paymentMethods.PaymentType;

public class PaymentMethodFactory {
    public static GenericPaymentMethod build(NewPaymentRequest newPaymentRequest) {
        if (newPaymentRequest.getPaymentMethod() == PaymentType.PIX) return new PIXPaymentMethod();

        return  new CreditCardPaymentMethod(newPaymentRequest.getCardNumber(), newPaymentRequest.getCardHolder(), newPaymentRequest.getCardValidationDate(), newPaymentRequest.getCardCode());
    }
}

