package com.tappayments.automation.qazidpay.cases;

import com.tappayments.automation.qazidpay.base.BaseTest;
import com.tappayments.automation.qazidpay.requests.ZidPaySystemRequest;
import com.tappayments.automation.qazidpay.utils.AppConstants;
import com.tappayments.automation.qazidpay.utils.AppUtils;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CreateZidPayToken extends BaseTest {


//    @Test
//    public void test(){
//
//        System.out.println("---------------------------------------------------------------------");
//        System.out.println(AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_MASTER_CARD_BODY));
//        System.out.println(AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_VISA_CARD_BODY));
//        System.out.println(AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_MADA_CARD_BODY));
//        System.out.println(AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_FRICTIONLESS_CARD_BODY));
//        System.out.println("---------------------------------------------------------------------");
//    }
//
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Publishable Api Key Validation",
                    "SC:Publishable Api Key"
            },
            description = "This test case validates the creation of a valid ZidPay token by providing a correct publishable API key in the request.",
            priority = 1
    )
    public void createValidPublishableApiKeyTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PUBLISHABLE_API_KEY, AppConstants.PUBLISHABLE_API_KEY_VALUE);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.MASTER, AppConstants.SCHEME, AppConstants.MASTER));
    }

    // Add a test case for invalid publishable API key
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Publishable Api Key Validation",
                    "SC:Publishable Api Key"
            },
            description = "This test case validates the creation of an invalid ZidPay token by providing an incorrect publishable API key in the request.",
            priority = 2
    )
    public void createInvalidPublishableApiKeyTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PUBLISHABLE_API_KEY, AppConstants.INVALID_PUBLISHABLE_API_KEY_VALUE);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for NULL publishable API key
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Publishable Api Key Validation",
                    "SC:Publishable Api Key"
            },
            description = "This test case validates the creation of a ZidPay token by providing a NULL publishable API key in the request.",
            priority = 3
    )
    public void createNullPublishableApiKeyTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PUBLISHABLE_API_KEY, null);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PUBLISHABLE_API_KEY_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for Empty publishable API key
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Publishable Api Key Validation",
                    "SC:Publishable Api Key"
            },
            description = "This test case validates the creation of a ZidPay token by providing an empty publishable API key in the request.",
            priority = 4
    )
    public void createEmptyPublishableApiKeyTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PUBLISHABLE_API_KEY, "");
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PUBLISHABLE_API_KEY_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for removed publishable API key
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Publishable Api Key Validation",
                    "SC:Publishable Api Key"
            },
            description = "This test case validates the creation of a ZidPay token by removing the publishable API key from the request.",
            priority = 5
    )
    public void createRemovedPublishableApiKeyTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.remove(AppConstants.PUBLISHABLE_API_KEY);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PUBLISHABLE_API_KEY_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for valid provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant Id Validation",
                    "SC:Provider Merchant Id"
            },
            description = "This test case validates the creation of a ZidPay token by providing a correct provider merchant ID in the request.",
            priority = 6
    )
    public void createValidProviderMerchantIdTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PROVIDER_MERCHANT_ID, AppConstants.PROVIDER_MERCHANT_ID_VALUE);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.MASTER, AppConstants.SCHEME, AppConstants.MASTER));
    }

    // Add a test case for invalid provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant Id Validation",
                    "SC:Provider Merchant Id"
            },
            description = "This test case validates the creation of a ZidPay token by providing an incorrect provider merchant ID in the request.",
            priority = 7
    )
    public void createInvalidProviderMerchantIdTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PROVIDER_MERCHANT_ID, AppConstants.INVALID_PROVIDER_MERCHANT_ID);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for NULL provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant Id Validation",
                    "SC:Provider Merchant Id"
            },
            description = "This test case validates the creation of a ZidPay token by providing a NULL provider merchant ID in the request.",
            priority = 8
    )
    public void createNullProviderMerchantIdTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PROVIDER_MERCHANT_ID, null);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for Empty provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant Id Validation",
                    "SC:Provider Merchant Id"
            },
            description = "This test case validates the creation of a ZidPay token by providing an empty provider merchant ID in the request.",
            priority = 9
    )
    public void createEmptyProviderMerchantIdTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.PROVIDER_MERCHANT_ID, "");
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for removed provider merchant ID
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Provider Merchant Id Validation",
                    "SC:Provider Merchant Id"
            },
            description = "This test case validates the creation of a ZidPay token by removing the provider merchant ID from the request.",
            priority = 10
    )
    public void createRemovedProviderMerchantIdTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.remove(AppConstants.PROVIDER_MERCHANT_ID);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PROVIDER_MERCHANT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for valid number
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Number Validation",
                    "SC:Number"
            },
            description = "This test case validates the creation of a ZidPay token by providing a correct master card detail number in the request.",
            priority = 11
    )
    public void createValidMasterCardNumberTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_MASTER_CARD_BODY));
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.MASTER, AppConstants.SCHEME, AppConstants.MASTER));
    }

    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Number Validation",
                    "SC:Number"
            },
            description = "This test case validates the creation of a ZidPay token by providing a correct visa card detail number in the request.",
            priority = 12
    )
    public void createValidVisaCardNumberTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_VISA_CARD_BODY));
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.VISA, AppConstants.SCHEME, AppConstants.VISA));
    }

    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Number Validation",
                    "SC:Number"
            },
            description = "This test case validates the creation of a ZidPay token by providing a correct mada card detail number in the request.",
            priority = 13
    )
    public void createValidMadaCardNumberTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_MADA_CARD_BODY));
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.MASTER, AppConstants.SCHEME, AppConstants.MADA));
    }

    // Add a test case for invalid number
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Number Validation",
                    "SC:Number"
            },
            description = "This test case validates the creation of a ZidPay token by providing an incorrect number in the request.",
            priority = 14
    )
    public void createInvalidNumberTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, AppConstants.INVALID_TOKEN_NUMBER);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for NULL number
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Number Validation",
                    "SC:Number"
            },
            description = "This test case validates the creation of a ZidPay token by providing a NULL number in the request.",
            priority = 15
    )
    public void createNullNumberTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, null);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_CARD_NUMBER));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for Empty number
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Number Validation",
                    "SC:Number"
            },
            description = "This test case validates the creation of a ZidPay token by providing an empty number in the request.",
            priority = 16
    )
    public void createEmptyNumberTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, "");
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_CARD_NUMBER));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    // Add a test case for removed number
    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Number Validation",
                    "SC:Number"
            },
            description = "This test case validates the creation of a ZidPay token by removing the number from the request.",
            priority = 17
    )
    public void createRemovedNumberTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.remove(AppConstants.NUMBER);
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_CARD_NUMBER));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Scheme Validation",
                    "SC:Scheme"
            },
            description = "This test case validates the creation of a ZidPay token by providing a correct master card detail number in the request and check the scheme should be master.",
            priority = 18
    )
    public void createValidMasterCardNumberSchemeTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_MASTER_CARD_BODY));
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.MASTER, AppConstants.SCHEME, AppConstants.MASTER));
    }

    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Scheme Validation",
                    "SC:Scheme"
            },
            description = "This test case validates the creation of a ZidPay token by providing a correct visa card detail number in the request.",
            priority = 19
    )
    public void createValidVisaCardNumberSchemeTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_VISA_CARD_BODY));
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.VISA, AppConstants.SCHEME, AppConstants.VISA));
    }

    @Test(
            groups = {
                    "MC:Create ZidPay Token",
                    "Abdul Rehman",
                    "SECTION:Scheme Validation",
                    "SC:Scheme"
            },
            description = "This test case validates the creation of a ZidPay token by providing a correct mada card detail number in the request.",
            priority = 20
    )
    public void createValidMadaCardNumberSchemeTestCase(){

        Map<String, String> zidPayTokenRequestBody = AppUtils.zidPayTokenRequestBody();
        zidPayTokenRequestBody.put(AppConstants.NUMBER, AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_MADA_CARD_BODY));
        String requestBody = CommonAutomationUtils.stringToJson(zidPayTokenRequestBody);
        Response response = ZidPaySystemRequest.postZidPayTokenResponse(requestBody);
        String tokenId = response.jsonPath().getString(AppConstants.TOKEN);
        AppUtils.validateZidPayTokenValue(response, Map.of(AppConstants.TOKEN_ID, tokenId, AppConstants.BRAND, AppConstants.MASTER, AppConstants.SCHEME, AppConstants.MADA));
    }
}
