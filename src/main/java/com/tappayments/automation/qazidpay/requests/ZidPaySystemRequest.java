package com.tappayments.automation.qazidpay.requests;

import com.tappayments.automation.qazidpay.base.RestAssuredClient;
import com.tappayments.automation.qazidpay.config.ConfigManager;
import com.tappayments.automation.qazidpay.utils.AppConstants;
import com.tappayments.automation.qazidpay.utils.AppUtils;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.restassured.response.Response;

import java.util.Map;

public class ZidPaySystemRequest extends RestAssuredClient {

    public static Response postZidPayTokenResponse() {

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        return postZidPayTokenResponse(requestBody);
    }

    public static Response postZidPayTokenResponse(String requestBody) {

        return postRequest(requestBody, "/" + AppConstants.TOKENS);
    }

    public static Response postZidPayPaymentResponse(String requestBody) {

        Map<String, String> headers = Map.of(
                AppConstants.CONTENT_TYPE, ConfigManager.getPropertyValue(AppConstants.CONTENT_TYPE_VALUE),
                AppConstants.AUTHORIZATION, AppConstants.BASIC + AppConstants.AUTHORIZATION_TOKEN_VALUE
        );

        return postRequest(requestBody, "/" + AppConstants.PAYMENTS, headers);
    }

    public static Response getZidPayPaymentStatus(String paymentId) {

        Map<String, String> headers = Map.of(
                AppConstants.CONTENT_TYPE, ConfigManager.getPropertyValue(AppConstants.CONTENT_TYPE_VALUE),
                AppConstants.AUTHORIZATION, AppConstants.BASIC + AppConstants.AUTHORIZATION_TOKEN_VALUE
        );

        return getRequest("/" + AppConstants.PAYMENTS + "/" + paymentId, headers);
    }

    public static Response postZidPayPaymentRefund(String requestBody){

        Map<String, String> headers = Map.of(
                AppConstants.CONTENT_TYPE, ConfigManager.getPropertyValue(AppConstants.CONTENT_TYPE_VALUE),
                AppConstants.AUTHORIZATION, AppConstants.BASIC + AppConstants.AUTHORIZATION_TOKEN_VALUE
        );

        return postRequest(requestBody, "/" + AppConstants.REFUND, headers);
    }
}
