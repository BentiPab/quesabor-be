package com.queempanadas.dto;

import com.queempanadas.model.NewMovement;
import com.queempanadas.model.PaymentMethod;

import java.util.ArrayList;

public class NewSaleDto extends NewMovement {
    private PaymentMethod paymentMethod;

    private ArrayList<Long> promotions = new ArrayList<>();

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

}
