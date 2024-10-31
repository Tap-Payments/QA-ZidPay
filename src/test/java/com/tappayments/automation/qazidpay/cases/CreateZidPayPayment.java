package com.tappayments.automation.qazidpay.cases;

import com.tappayments.automation.qazidpay.base.BaseTest;
import com.tappayments.automation.qazidpay.model.PaymentRequest;
import com.tappayments.automation.qazidpay.requests.ZidPaySystemRequest;
import com.tappayments.automation.qazidpay.utils.AppConstants;
import com.tappayments.automation.qazidpay.utils.AppUtils;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.UUID;

public class CreateZidPayPayment extends BaseTest {

    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Amount Validation",
                    "SC:Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid amount is provided. It ensures that the amount field is processed correctly and a successful payment is created.",
            priority = 1
    )
    public void createValidZidPayAmountTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setAmount(10000);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for zero amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Amount Validation",
                    "SC:Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a zero amount is provided. It ensures that the amount field is processed correctly and a successful payment is created.",
            priority = 2
    )
    public void createZeroZidPayAmountTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setAmount(0);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for negative amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Amount Validation",
                    "SC:Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a negative amount is provided. It ensures that the amount field is processed correctly and a successful payment is created.",
            priority = 3
    )
    public void createNegativeZidPayAmountTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setAmount(-10000);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_AMOUNT_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for null amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Amount Validation",
                    "SC:Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null amount is provided. It ensures that the amount field is processed correctly and a successful payment is created.",
            priority = 4
    )
    public void createNullZidPayAmountTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setAmount(null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_AMOUNT_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for missing amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Amount Validation",
                    "SC:Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an amount is missing. It ensures that the amount field is processed correctly and a successful payment is created.",
            priority = 5
    )
    public void createMissingZidPayAmountTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, AppConstants.DELETE, AppConstants.AMOUNT, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(updatedRequestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_AMOUNT_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for valid currency
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Currency Validation",
                    "SC:Currency"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid currency is provided. It ensures that the currency field is processed correctly and a successful payment is created.",
            priority = 6
    )
    public void createValidZidPayCurrencyTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCurrency("SAR");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for valid currency lowercase
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Currency Validation",
                    "SC:Currency"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid currency in lowercase is provided. It ensures that the currency field is processed correctly and a successful payment is created.",
            priority = 7
    )
    public void createValidZidPayCurrencyLowerCaseTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCurrency("sar");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        paymentRequest.setCurrency("SAR");
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for any other valid currency
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Currency Validation",
                    "SC:Currency"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid currency other than SAR is provided. It ensures that the currency field is processed correctly and a successful payment is created.",
            priority = 8
    )
    public void createValidZidPayCurrencyOtherTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCurrency("OMR");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for unsupported currency
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Currency Validation",
                    "SC:Currency"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an unsupported currency is provided. It ensures that the currency field is processed correctly and a successful payment is created.",
            priority = 9
    )
    public void createUnsupportedZidPayCurrencyTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCurrency("CAD");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for null currency
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Currency Validation",
                    "SC:Currency"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null currency is provided. It ensures that the currency field is processed correctly and a successful payment is created.",
            priority = 10
    )
    public void createNullZidPayCurrencyTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCurrency(null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }
    
    // Add a test case for empty currency
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Currency Validation",
                    "SC:Currency"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty currency is provided. It ensures that the currency field is processed correctly and a successful payment is created.",
            priority = 11
    )
    public void createEmptyZidPayCurrencyTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCurrency("");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for missing currency
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Currency Validation",
                    "SC:Currency"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a currency is missing. It ensures that the currency field is processed correctly and a successful payment is created.",
            priority = 12
    )
    public void createMissingZidPayCurrencyTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, AppConstants.DELETE, AppConstants.CURRENCY, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(updatedRequestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for valid callback URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Callback URL Validation",
                    "SC:Callback URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid callback URL is provided. It ensures that the callback URL field is processed correctly and a successful payment is created.",
            priority = 13
    )
    public void createValidZidPayCallbackUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCallbackUrl("https://zid.sa/en/");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid callback URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Callback URL Validation",
                    "SC:Callback URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid callback URL is provided. It ensures that the callback URL field is processed correctly and a successful payment is created.",
            priority = 14
    )
    public void createInvalidZidPayCallbackUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCallbackUrl("invalid-url");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null callback URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Callback URL Validation",
                    "SC:Callback URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null callback URL is provided. It ensures that the callback URL field is processed correctly and a successful payment is created.",
            priority = 15
    )
    public void createNullZidPayCallbackUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCallbackUrl(null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty callback URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Callback URL Validation",
                    "SC:Callback URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty callback URL is provided. It ensures that the callback URL field is processed correctly and a successful payment is created.",
            priority = 16
    )
    public void createEmptyZidPayCallbackUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setCallbackUrl("");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing callback URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Callback URL Validation",
                    "SC:Callback URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a callback URL is missing. It ensures that the callback URL field is processed correctly and a successful payment is created.",
            priority = 17
    )
    public void createMissingZidPayCallbackUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, AppConstants.DELETE, AppConstants.CALLBACK_URL, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(updatedRequestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for valid error URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Error URL Validation",
                    "SC:Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid error URL is provided. It ensures that the error URL field is processed correctly and a successful payment is created.",
            priority = 18
    )
    public void createValidZidPayErrorUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setErrorUrl("https://dribbble.com/tags/payment-failed");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid error URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Error URL Validation",
                    "SC:Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid error URL is provided. It ensures that the error URL field is processed correctly and a successful payment is created.",
            priority = 19
    )
    public void createInvalidZidPayErrorUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setErrorUrl("invalid-url");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null error URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Error URL Validation",
                    "SC:Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null error URL is provided. It ensures that the error URL field is processed correctly and a successful payment is created.",
            priority = 20
    )
    public void createNullZidPayErrorUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setErrorUrl(null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty error URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Error URL Validation",
                    "SC:Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty error URL is provided. It ensures that the error URL field is processed correctly and a successful payment is created.",
            priority = 21
    )
    public void createEmptyZidPayErrorUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setErrorUrl("");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing error URL
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Error URL Validation",
                    "SC:Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an error URL is missing. It ensures that the error URL field is processed correctly and a successful payment is created.",
            priority = 22
    )
    public void createMissingZidPayErrorUrlTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, AppConstants.DELETE, AppConstants.ERROR_URL, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(updatedRequestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for valid provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant ID Validation",
                    "SC:Provider Merchant ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid provider merchant ID is provided. It ensures that the provider merchant ID field is processed correctly and a successful payment is created.",
            priority = 23
    )
    public void createValidZidPayProviderMerchantIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setProviderMerchantId("merchant_cWFE55241559X1Jr7Ua9c419");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant ID Validation",
                    "SC:Provider Merchant ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid provider merchant ID is provided. It ensures that the provider merchant ID field is processed correctly and a successful payment is created.",
            priority = 24
    )
    public void createInvalidZidPayProviderMerchantIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setProviderMerchantId("invalid-merchant-id");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_API_KEY_OR_PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for null provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant ID Validation",
                    "SC:Provider Merchant ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null provider merchant ID is provided. It ensures that the provider merchant ID field is processed correctly and a successful payment is created.",
            priority = 25
    )
    public void createNullZidPayProviderMerchantIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setProviderMerchantId(null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for empty provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant ID Validation",
                    "SC:Provider Merchant ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty provider merchant ID is provided. It ensures that the provider merchant ID field is processed correctly and a successful payment is created.",
            priority = 26
    )
    public void createEmptyZidPayProviderMerchantIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.setProviderMerchantId("");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for missing provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant ID Validation",
                    "SC:Provider Merchant ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a provider merchant ID is missing. It ensures that the provider merchant ID field is processed correctly and a successful payment is created.",
            priority = 27
    )
    public void createMissingZidPayProviderMerchantIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, AppConstants.DELETE, AppConstants.PROVIDER_MERCHANT_ID, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(updatedRequestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for valid source type
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Type Validation",
                    "SC:Source Type"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid source type is provided. It ensures that the source type field is processed correctly and a successful payment is created.",
            priority = 28
    )
    public void createValidZidPaySourceTypeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setType(AppConstants.TOKEN);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid source type
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Type Validation",
                    "SC:Source Type"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid source type is provided. It ensures that the source type field is processed correctly and a successful payment is created.",
            priority = 29
    )
    public void createInvalidZidPaySourceTypeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setType("invalid-source-type");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null source type
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Type Validation",
                    "SC:Source Type"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null source type is provided. It ensures that the source type field is processed correctly and a successful payment is created.",
            priority = 30
    )
    public void createNullZidPaySourceTypeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setType(null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty source type
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Type Validation",
                    "SC:Source Type"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty source type is provided. It ensures that the source type field is processed correctly and a successful payment is created.",
            priority = 31
    )
    public void createEmptyZidPaySourceTypeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setType("");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing source type
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Type Validation",
                    "SC:Source Type"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a source type is missing. It ensures that the source type field is processed correctly and a successful payment is created.",
            priority = 32
    )
    public void createMissingZidPaySourceTypeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, AppConstants.DELETE, AppConstants.SOURCE + "." + AppConstants.TYPE, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(updatedRequestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for valid source token
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Token Validation",
                    "SC:Source Token"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid source token is provided. It ensures that the source token field is processed correctly and a successful payment is created.",
            priority = 33
    )
    public void createValidZidPaySourceTokenTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid source token
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Token Validation",
                    "SC:Source Token"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid source token is provided. It ensures that the source token field is processed correctly and a successful payment is created.",
            priority = 34
    )
    public void createInvalidZidPaySourceTokenTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken("invalid-source-token");
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for null source token
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Token Validation",
                    "SC:Source Token"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null source token is provided. It ensures that the source token field is processed correctly and a successful payment is created.",
            priority = 35
    )
    public void createNullZidPaySourceTokenTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(null);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_TOKEN));
    }

    // Add a test case for empty source token
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Token Validation",
                    "SC:Source Token"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty source token is provided. It ensures that the source token field is processed correctly and a successful payment is created.",
            priority = 36
    )
    public void createEmptyZidPaySourceTokenTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken("");
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_TOKEN));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }
    
    // Add a test case for missing source token
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Source Token Validation",
                    "SC:Source Token"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a source token is missing. It ensures that the source token field is processed correctly and a successful payment is created.",
            priority = 37
    )
    public void createMissingZidPaySourceTokenTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, AppConstants.DELETE, AppConstants.SOURCE + "." + AppConstants.TOKEN, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(updatedRequestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_TOKEN));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for valid payment reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Reference ID Validation",
                    "SC:Metadata Payment Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid unique payment reference ID is provided. It ensures that the payment reference ID field is processed correctly and a successful payment is created.",
            priority = 38
    )
    public void createValidUniqueZidPayPaymentReferenceIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for duplicate payment reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Reference ID Validation",
                    "SC:Metadata Payment Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a duplicate payment reference ID is provided. It ensures that the payment reference ID field is processed correctly and a successful payment is created.",
            priority = 39
    )
    public void createDuplicateZidPayPaymentReferenceIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response1 = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        Response response2 = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response1, paymentRequest);
        AppUtils.validateZidPayPayment(response2, paymentRequest);
    }

    // Add a test case for null payment reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Reference ID Validation",
                    "SC:Metadata Payment Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null payment reference ID is provided. It ensures that the payment reference ID field is processed correctly and a successful payment is created.",
            priority = 40
    )
    public void createNullZidPayPaymentReferenceIdTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_REFERENCE_ID, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for metadata customer name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Name Validation",
                    "SC:Metadata Customer Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid customer name is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 41
    )
    public void createValidZidPayMetadataCustomerNameTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_NAME, "John Doe");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for Numeric value metadata customer name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Name Validation",
                    "SC:Metadata Customer Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a numeric value is provided in metadata customer name. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 42
    )
    public void createNumericValueZidPayMetadataCustomerNameTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_NAME, "123456");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for special characters metadata customer name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Name Validation",
                    "SC:Metadata Customer Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when special characters are provided in metadata customer name. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 43
    )
    public void createSpecialCharactersZidPayMetadataCustomerNameTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_NAME, "!@#$%^&*()");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata customer name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Name Validation",
                    "SC:Metadata Customer Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null customer name is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 44
    )
    public void createNullZidPayMetadataCustomerNameTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_NAME, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata customer name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Name Validation",
                    "SC:Metadata Customer Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty customer name is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 45
    )
    public void createEmptyZidPayMetadataCustomerNameTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_NAME, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata customer name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Name Validation",
                    "SC:Metadata Customer Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when customer name is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 46
    )
    public void createMissingZidPayMetadataCustomerNameTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.CUSTOMER_NAME);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata customer mobile number
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Number Validation",
                    "SC:Metadata Customer Mobile Number"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid customer mobile number is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 47
    )
    public void createValidZidPayMetadataCustomerMobileNumberTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE, "966500000000");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata customer mobile number
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Number Validation",
                    "SC:Metadata Customer Mobile Number"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid customer mobile number is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 48
    )
    public void createInvalidZidPayMetadataCustomerMobileNumberTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE, "5000");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata customer mobile number
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Number Validation",
                    "SC:Metadata Customer Mobile Number"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null customer mobile number is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 49
    )
    public void createNullZidPayMetadataCustomerMobileNumberTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata customer mobile number
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Number Validation",
                    "SC:Metadata Customer Mobile Number"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty customer mobile number is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 50
    )
    public void createEmptyZidPayMetadataCustomerMobileNumberTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata customer mobile number
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Number Validation",
                    "SC:Metadata Customer Mobile Number"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when customer mobile number is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 51
    )
    public void createMissingZidPayMetadataCustomerMobileNumberTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.CUSTOMER_MOBILE);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata customer mobile country code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Country Code Validation",
                    "SC:Metadata Customer Mobile Country Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid customer mobile country code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 52
    )
    public void createValidZidPayMetadataCustomerMobileCountryCodeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE_COUNTRY_CODE, "966");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata customer mobile country code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Country Code Validation",
                    "SC:Metadata Customer Mobile Country Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid customer mobile country code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 53
    )
    public void createInvalidZidPayMetadataCustomerMobileCountryCodeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE_COUNTRY_CODE, "00");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata customer mobile country code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Country Code Validation",
                    "SC:Metadata Customer Mobile Country Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null customer mobile country code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 54
    )
    public void createNullZidPayMetadataCustomerMobileCountryCodeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE_COUNTRY_CODE, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }
    
    // Add a test case for empty metadata customer mobile country code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Country Code Validation",
                    "SC:Metadata Customer Mobile Country Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty customer mobile country code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 55
    )
    public void createEmptyZidPayMetadataCustomerMobileCountryCodeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_MOBILE_COUNTRY_CODE, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata customer mobile country code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Mobile Country Code Validation",
                    "SC:Metadata Customer Mobile Country Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when customer mobile country code is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 56
    )
    public void createMissingZidPayMetadataCustomerMobileCountryCodeTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.CUSTOMER_MOBILE_COUNTRY_CODE);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata customer email address
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Email Address Validation",
                    "SC:Metadata Customer Email Address"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid customer email address is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 57
    )
    public void createValidZidPayMetadataCustomerEmailAddressTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_EMAIL, "majed.omran@zid.sa");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata customer email address
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Email Address Validation",
                    "SC:Metadata Customer Email Address"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid customer email address is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 58
    )
    public void createInvalidZidPayMetadataCustomerEmailAddressTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_EMAIL, "invalid_email");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata customer email address
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Email Address Validation",
                    "SC:Metadata Customer Email Address"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null customer email address is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 59
    )
    public void createNullZidPayMetadataCustomerEmailAddressTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_EMAIL, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata customer email address
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Email Address Validation",
                    "SC:Metadata Customer Email Address"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty customer email address is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 60
    )
    public void createEmptyZidPayMetadataCustomerEmailAddressTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_EMAIL, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata customer email address
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Email Address Validation",
                    "SC:Metadata Customer Email Address"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when customer email address is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 61
    )
    public void createMissingZidPayMetadataCustomerEmailAddressTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.CUSTOMER_EMAIL);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata card brand
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Card Brand Validation",
                    "SC:Metadata Card Brand"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid card brand is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 62
    )
    public void createValidZidPayMetadataCardBrandTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CARD_BRAND, "mada");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }
    
    // Add a test case for invalid metadata card brand
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Card Brand Validation",
                    "SC:Metadata Card Brand"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid card brand is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 63
    )
    public void createInvalidZidPayMetadataCardBrandTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CARD_BRAND, "invalid-card-brand");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata card brand
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Card Brand Validation",
                    "SC:Metadata Card Brand"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null card brand is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 64
    )
    public void createNullZidPayMetadataCardBrandTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CARD_BRAND, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata card brand
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Card Brand Validation",
                    "SC:Metadata Card Brand"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty card brand is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 65
    )
    public void createEmptyZidPayMetadataCardBrandTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CARD_BRAND, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata card brand
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Card Brand Validation",
                    "SC:Metadata Card Brand"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when card brand is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 66
    )
    public void createMissingZidPayMetadataCardBrandTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.CARD_BRAND);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata Call back url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Call Back URL Validation",
                    "SC:Metadata Call Back URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid call back URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 67
    )
    public void createValidZidPayMetadataCallBackUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CALL_BACK_URL, "https://balsam-store.com/checkout/{orderId}/zidpay/callback");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Call back url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Call Back URL Validation",
                    "SC:Metadata Call Back URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid call back URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 68
    )
    public void createInvalidZidPayMetadataCallBackUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CALL_BACK_URL, "invalid-call-back-url");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Call back url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Call Back URL Validation",
                    "SC:Metadata Call Back URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null call back URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 69
    )
    public void createNullZidPayMetadataCallBackUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CALL_BACK_URL, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Call back url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Call Back URL Validation",
                    "SC:Metadata Call Back URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty call back URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 70
    )
    public void createEmptyZidPayMetadataCallBackUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CALL_BACK_URL, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Call back url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Call Back URL Validation",
                    "SC:Metadata Call Back URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when call back URL is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 71
    )
    public void createMissingZidPayMetadataCallBackUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.CALL_BACK_URL);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata Error url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Error URL Validation",
                    "SC:Metadata Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid error URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 72
    )
    public void createValidZidPayMetadataErrorUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.ERROR_URL, "https://balsam-store.com/checkout/{orderId}/zidpay/error");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Error url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Error URL Validation",
                    "SC:Metadata Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid error URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 73
    )
    public void createInvalidZidPayMetadataErrorUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.ERROR_URL, "invalid-error-url");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Error url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Error URL Validation",
                    "SC:Metadata Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null error URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 74
    )
    public void createNullZidPayMetadataErrorUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.ERROR_URL, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Error url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Error URL Validation",
                    "SC:Metadata Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty error URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 75
    )
    public void createEmptyZidPayMetadataErrorUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.ERROR_URL, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Error url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Error URL Validation",
                    "SC:Metadata Error URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when error URL is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 76
    )
    public void createMissingZidPayMetadataErrorUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.ERROR_URL);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata webhook url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Webhook URL Validation",
                    "SC:Metadata Webhook URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid webhook URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 77
    )
    public void createValidZidPayMetadataWebhookUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.WEBHOOK_URL, "https://api.zid.sa/api/v1/payments/zidpay/{orderId}");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata webhook url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Webhook URL Validation",
                    "SC:Metadata Webhook URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid webhook URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 78
    )
    public void createInvalidZidPayMetadataWebhookUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.WEBHOOK_URL, "invalid-webhook-url");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata webhook url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Webhook URL Validation",
                    "SC:Metadata Webhook URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null webhook URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 79
    )
    public void createNullZidPayMetadataWebhookUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.WEBHOOK_URL, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata webhook url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Webhook URL Validation",
                    "SC:Metadata Webhook URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty webhook URL is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 80
    )
    public void createEmptyZidPayMetadataWebhookUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.WEBHOOK_URL, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata webhook url
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Webhook URL Validation",
                    "SC:Metadata Webhook URL"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when webhook URL is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 81
    )
    public void createMissingZidPayMetadataWebhookUrlTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.WEBHOOK_URL);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata Payment method
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Method Validation",
                    "SC:Metadata Payment Method"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid payment method is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 82
    )
    public void createValidZidPayMetadataPaymentMethodTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_METHOD, "6");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Payment method
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Method Validation",
                    "SC:Metadata Payment Method"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid payment method is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 83
    )
    public void createInvalidZidPayMetadataPaymentMethodTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_METHOD, "invalid-payment-method");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Payment method
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Method Validation",
                    "SC:Metadata Payment Method"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null payment method is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 84
    )
    public void createNullZidPayMetadataPaymentMethodTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_METHOD, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Payment method
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Method Validation",
                    "SC:Metadata Payment Method"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty payment method is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 85
    )
    public void createEmptyZidPayMetadataPaymentMethodTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_METHOD, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Payment method
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Method Validation",
                    "SC:Metadata Payment Method"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when payment method is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 86
    )
    public void createMissingZidPayMetadataPaymentMethodTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.PAYMENT_METHOD);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid metadata amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Amount Validation",
                    "SC:Metadata Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid amount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 87
    )
    public void createValidZidPayMetadataAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AMOUNT, "1000");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for negative metadata amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Amount Validation",
                    "SC:Metadata Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a negative amount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 88
    )
    public void createNegativeZidPayMetadataAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AMOUNT, "-1000");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Amount Validation",
                    "SC:Metadata Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid amount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 89
    )
    public void createInvalidZidPayMetadataAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AMOUNT, "invalid-amount");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Amount Validation",
                    "SC:Metadata Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null amount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 90
    )
    public void createNullZidPayMetadataAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AMOUNT, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Amount Validation",
                    "SC:Metadata Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty amount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 91
    )
    public void createEmptyZidPayMetadataAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AMOUNT, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata amount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Amount Validation",
                    "SC:Metadata Amount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when amount is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 92
    )
    public void createMissingZidPayMetadataAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.METADATA_AMOUNT);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid display currency iso
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Display Currency ISO Validation",
                    "SC:Metadata Display Currency ISO"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid display currency ISO is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 93
    )
    public void createValidZidPayMetadataDisplayCurrencyIsoTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISPLAY_CURRENCY_ISO, "SAR");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata display currency iso
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Display Currency ISO Validation",
                    "SC:Metadata Display Currency ISO"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid display currency ISO is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 94
    )
    public void createInvalidZidPayMetadataDisplayCurrencyIsoTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISPLAY_CURRENCY_ISO, "invalid-currency-iso");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata display currency iso
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Display Currency ISO Validation",
                    "SC:Metadata Display Currency ISO"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null display currency ISO is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 95
    )
    public void createNullZidPayMetadataDisplayCurrencyIsoTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISPLAY_CURRENCY_ISO, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata display currency iso
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Display Currency ISO Validation",
                    "SC:Metadata Display Currency ISO"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty display currency ISO is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 96
    )
    public void createEmptyZidPayMetadataDisplayCurrencyIsoTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISPLAY_CURRENCY_ISO, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata display currency iso
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Display Currency ISO Validation",
                    "SC:Metadata Display Currency ISO"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when display currency ISO is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 97
    )
    public void createMissingZidPayMetadataDisplayCurrencyIsoTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.DISPLAY_CURRENCY_ISO);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid customer reference
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Reference Validation",
                    "SC:Metadata Customer Reference"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid customer reference is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 98
    )
    public void createValidZidPayMetadataCustomerReferenceTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_REFERENCE, "3248111");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata customer reference
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Reference Validation",
                    "SC:Metadata Customer Reference"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid customer reference is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 99
    )
    public void createInvalidZidPayMetadataCustomerReferenceTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_REFERENCE, "invalid-customer-reference");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata customer reference
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Reference Validation",
                    "SC:Metadata Customer Reference"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null customer reference is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 100
    )
    public void createNullZidPayMetadataCustomerReferenceTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_REFERENCE, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata customer reference
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Reference Validation",
                    "SC:Metadata Customer Reference"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty customer reference is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 101
    )
    public void createEmptyZidPayMetadataCustomerReferenceTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.CUSTOMER_REFERENCE, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata customer reference
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Customer Reference Validation",
                    "SC:Metadata Customer Reference"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when customer reference is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 102
    )
    public void createMissingZidPayMetadataCustomerReferenceTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.CUSTOMER_REFERENCE);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid merchant reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Merchant Reference ID Validation",
                    "SC:Metadata Merchant Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid merchant reference ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 103
    )
    public void createValidZidPayMetadataMerchantReferenceIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.MERCHANT_REFERENCE_ID, "8bf55837-60e0-4d7d-b0db-ec7b5b5ad6a3");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata merchant reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Merchant Reference ID Validation",
                    "SC:Metadata Merchant Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid merchant reference ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 104
    )
    public void createInvalidZidPayMetadataMerchantReferenceIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.MERCHANT_REFERENCE_ID, "invalid-merchant-reference-id");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata merchant reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Merchant Reference ID Validation",
                    "SC:Metadata Merchant Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null merchant reference ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 105
    )
    public void createNullZidPayMetadataMerchantReferenceIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.MERCHANT_REFERENCE_ID, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata merchant reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Merchant Reference ID Validation",
                    "SC:Metadata Merchant Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty merchant reference ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 106
    )
    public void createEmptyZidPayMetadataMerchantReferenceIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.MERCHANT_REFERENCE_ID, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata merchant reference id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Merchant Reference ID Validation",
                    "SC:Metadata Merchant Reference ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when merchant reference ID is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 107
    )
    public void createMissingZidPayMetadataMerchantReferenceIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.MERCHANT_REFERENCE_ID);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid Affiliate link
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Affiliate Link Validation",
                    "SC:Metadata Affiliate Link"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid affiliate link is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 108
    )
    public void createValidZidPayMetadataAffiliateLinkTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AFFILIATE_LINK, "https://balsam-store.com/affiliate/");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Affiliate link
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Affiliate Link Validation",
                    "SC:Metadata Affiliate Link"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid affiliate link is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 109
    )
    public void createInvalidZidPayMetadataAffiliateLinkTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AFFILIATE_LINK, "invalid-affiliate-link");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Affiliate link
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Affiliate Link Validation",
                    "SC:Metadata Affiliate Link"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null affiliate link is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 110
    )
    public void createNullZidPayMetadataAffiliateLinkTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AFFILIATE_LINK, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Affiliate link
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Affiliate Link Validation",
                    "SC:Metadata Affiliate Link"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty affiliate link is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 111
    )
    public void createEmptyZidPayMetadataAffiliateLinkTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.AFFILIATE_LINK, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Affiliate link
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Affiliate Link Validation",
                    "SC:Metadata Affiliate Link"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when affiliate link is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 112
    )
    public void createMissingZidPayMetadataAffiliateLinkTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.AFFILIATE_LINK);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid Payment link id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Link ID Validation",
                    "SC:Metadata Payment Link ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid payment link ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 113
    )
    public void createValidZidPayMetadataPaymentLinkIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_LINK_ID, "8bf55837-60e0-4d7d-b0db-ec7b5b5ad6a3");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Payment link id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Link ID Validation",
                    "SC:Metadata Payment Link ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid payment link ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 114
    )
    public void createInvalidZidPayMetadataPaymentLinkIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_LINK_ID, "invalid-payment-link-id");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Payment link id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Link ID Validation",
                    "SC:Metadata Payment Link ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null payment link ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 115
    )
    public void createNullZidPayMetadataPaymentLinkIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_LINK_ID, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Payment link id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Link ID Validation",
                    "SC:Metadata Payment Link ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty payment link ID is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 116
    )
    public void createEmptyZidPayMetadataPaymentLinkIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.PAYMENT_LINK_ID, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Payment link id
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Payment Link ID Validation",
                    "SC:Metadata Payment Link ID"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when payment link ID is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 117
    )
    public void createMissingZidPayMetadataPaymentLinkIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.PAYMENT_LINK_ID);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid Apply pay quick check out
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Apply Pay Quick Check Out Validation",
                    "SC:Metadata Apply Pay Quick Check Out"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid Apply Pay Quick Check Out is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 118
    )
    public void createValidZidPayMetadataApplyPayQuickCheckOutTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.APPLE_PAY_QUICK_CHECKOUT, "true");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for false metadata Apply pay quick check out
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Apply Pay Quick Check Out Validation",
                    "SC:Metadata Apply Pay Quick Check Out"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a false Apply Pay Quick Check Out is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 119
    )
    public void createFalseZidPayMetadataApplyPayQuickCheckOutTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.APPLE_PAY_QUICK_CHECKOUT, "false");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Apply pay quick check out
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Apply Pay Quick Check Out Validation",
                    "SC:Metadata Apply Pay Quick Check Out"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid Apply Pay Quick Check Out is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 120
    )
    public void createInvalidZidPayMetadataApplyPayQuickCheckOutTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.APPLE_PAY_QUICK_CHECKOUT, "invalid-apply-pay-quick-checkout");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Apply pay quick check out
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Apply Pay Quick Check Out Validation",
                    "SC:Metadata Apply Pay Quick Check Out"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null Apply Pay Quick Check Out is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 121
    )
    public void createNullZidPayMetadataApplyPayQuickCheckOutTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.APPLE_PAY_QUICK_CHECKOUT, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Apply pay quick check out
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Apply Pay Quick Check Out Validation",
                    "SC:Metadata Apply Pay Quick Check Out"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty Apply Pay Quick Check Out is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 122
    )
    public void createEmptyZidPayMetadataApplyPayQuickCheckOutTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.APPLE_PAY_QUICK_CHECKOUT, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Apply pay quick check out
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Apply Pay Quick Check Out Validation",
                    "SC:Metadata Apply Pay Quick Check Out"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when Apply Pay Quick Check Out is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 123
    )
    public void createMissingZidPayMetadataApplyPayQuickCheckOutTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.APPLE_PAY_QUICK_CHECKOUT);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid Coupon Code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Coupon Code Validation",
                    "SC:Metadata Coupon Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid coupon code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 124
    )
    public void createValidZidPayMetadataCouponCodeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.COUPON_CODE, "ZIDPAY10");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Coupon Code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Coupon Code Validation",
                    "SC:Metadata Coupon Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid coupon code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 125
    )
    public void createInvalidZidPayMetadataCouponCodeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.COUPON_CODE, "invalid-coupon-code");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Coupon Code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Coupon Code Validation",
                    "SC:Metadata Coupon Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null coupon code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 126
    )
    public void createNullZidPayMetadataCouponCodeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.COUPON_CODE, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Coupon Code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Coupon Code Validation",
                    "SC:Metadata Coupon Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty coupon code is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 127
    )
    public void createEmptyZidPayMetadataCouponCodeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.COUPON_CODE, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Coupon Code
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Coupon Code Validation",
                    "SC:Metadata Coupon Code"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when coupon code is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 128
    )
    public void createMissingZidPayMetadataCouponCodeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.COUPON_CODE);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid Shipping fee
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Fee Validation",
                    "SC:Metadata Shipping Fee"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid shipping fee is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 129
    )
    public void createValidZidPayMetadataShippingFeeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_FEES, "10.00");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Shipping fee
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Fee Validation",
                    "SC:Metadata Shipping Fee"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid shipping fee is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 130
    )
    public void createInvalidZidPayMetadataShippingFeeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_FEES, "invalid-shipping-fee");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Shipping fee
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Fee Validation",
                    "SC:Metadata Shipping Fee"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null shipping fee is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 131
    )
    public void createNullZidPayMetadataShippingFeeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_FEES, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Shipping fee
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Fee Validation",
                    "SC:Metadata Shipping Fee"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty shipping fee is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 132
    )
    public void createEmptyZidPayMetadataShippingFeeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_FEES, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }
    
    // Add a test case for missing metadata Shipping fee
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Fee Validation",
                    "SC:Metadata Shipping Fee"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when shipping fee is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 133
    )
    public void createMissingZidPayMetadataShippingFeeTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.SHIPPING_FEES);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid Shipping method name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Method Name Validation",
                    "SC:Metadata Shipping Method Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid shipping method name is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 134
    )
    public void createValidZidPayMetadataShippingMethodNameTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_METHOD_NAME, "DHL Express");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata Shipping method name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Method Name Validation",
                    "SC:Metadata Shipping Method Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid shipping method name is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 135
    )
    public void createInvalidZidPayMetadataShippingMethodNameTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_METHOD_NAME, "invalid-shipping-method-name");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata Shipping method name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Method Name Validation",
                    "SC:Metadata Shipping Method Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null shipping method name is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 136
    )
    public void createNullZidPayMetadataShippingMethodNameTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_METHOD_NAME, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata Shipping method name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Method Name Validation",
                    "SC:Metadata Shipping Method Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty shipping method name is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 137
    )
    public void createEmptyZidPayMetadataShippingMethodNameTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.SHIPPING_METHOD_NAME, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata Shipping method name
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Shipping Method Name Validation",
                    "SC:Metadata Shipping Method Name"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when shipping method name is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 138
    )
    public void createMissingZidPayMetadataShippingMethodNameTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.SHIPPING_METHOD_NAME);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for metadata valid meta data discount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Discount Validation",
                    "SC:Metadata Discount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a valid discount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 139
    )
    public void createValidZidPayMetadataDiscountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISCOUNT, "10.00");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for invalid metadata discount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Discount Validation",
                    "SC:Metadata Discount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an invalid discount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 140
    )
    public void createInvalidZidPayMetadataDiscountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISCOUNT, "invalid-discount");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for null metadata discount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Discount Validation",
                    "SC:Metadata Discount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when a null discount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 141
    )
    public void createNullZidPayMetadataDiscountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISCOUNT, null);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for empty metadata discount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Discount Validation",
                    "SC:Metadata Discount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when an empty discount is provided in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 142
    )
    public void createEmptyZidPayMetadataDiscountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().put(AppConstants.DISCOUNT, "");
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }

    // Add a test case for missing metadata discount
    @Test(
            groups = {
                    "MC:Create ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Metadata Discount Validation",
                    "SC:Metadata Discount"
            },
            description = "This test case validates the correct behavior of ZidPay Payment API when discount is missing in metadata. It ensures that the metadata field is processed correctly and a successful payment is created.",
            priority = 143
    )
    public void createMissingZidPayMetadataDiscountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getMetadata().remove(AppConstants.DISCOUNT);
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        AppUtils.validateZidPayPayment(response, paymentRequest);
    }
}