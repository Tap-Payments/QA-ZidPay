package com.tappayments.automation.qazidpay.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tappayments.automation.qazidpay.utils.AppConstants;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PaymentRequest {

    private Integer amount;
    private String currency;
    @JsonProperty(value = AppConstants.CALLBACK_URL)
    private String callbackUrl;
    private String errorUrl;
    private String providerMerchantId;
    private Source source;
    private Map<String, String> metadata;

    @Data
    @Builder
    public static class Source {

        private String type;
        private String token;
    }
}
