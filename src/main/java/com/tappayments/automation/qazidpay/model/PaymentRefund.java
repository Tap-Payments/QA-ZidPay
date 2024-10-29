package com.tappayments.automation.qazidpay.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRefund {

    private Integer amount;
    private String paymentId;
}
