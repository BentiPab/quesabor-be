package com.queempanadas.dto;

import com.queempanadas.model.NewMovement;
import com.queempanadas.model.PaymentMethod;

public class NewSaleDto extends NewMovement {
    private PaymentMethod paymentMethod;

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

}
