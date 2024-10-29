package com.tappayments.automation.qazidpay.cases;

import com.tappayments.automation.qazidpay.App;
import com.tappayments.automation.qazidpay.base.BaseTest;
import com.tappayments.automation.qazidpay.model.PaymentRefund;
import com.tappayments.automation.qazidpay.model.PaymentRequest;
import com.tappayments.automation.qazidpay.requests.ZidPaySystemRequest;
import com.tappayments.automation.qazidpay.utils.AppConstants;
import com.tappayments.automation.qazidpay.utils.AppUtils;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Map;

public class RefundZidPayPayment extends BaseTest {

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is less than the original payment amount. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 1
    )
    public void getLessRefundAmountTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getLessRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setAmount(100);
        paymentRefund.setPaymentId(zidPayPaymentId);
        AppUtils.executeZidPayPaymentRefundFlow(paymentRequest, paymentRefund, zidPayPaymentId);
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is higher than the original payment amount. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 2
    )
    public void getHighRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getHighRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setAmount(paymentRequest.getAmount() + 1000);
        paymentRefund.setPaymentId(zidPayPaymentId);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is equal to the original payment amount. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 3
    )
    public void getFullRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getFullRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setAmount(paymentRequest.getAmount());
        paymentRefund.setPaymentId(zidPayPaymentId);
        AppUtils.executeZidPayPaymentRefundFlow(paymentRequest, paymentRefund, zidPayPaymentId);
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is equal to 0. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 4
    )
    public void getZeroRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getZeroRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setAmount(0);
        paymentRefund.setPaymentId(zidPayPaymentId);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.PROCESSING_ERROR_VALUE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is negative. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 5
    )
    public void getNegativeRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getNegativeRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setAmount(-100);
        paymentRefund.setPaymentId(zidPayPaymentId);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_AMOUNT_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is missing. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 6
    )
    public void getMissingRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getMissingRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setPaymentId(zidPayPaymentId);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, "DELETE", AppConstants.AMOUNT, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(updatedRequestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_AMOUNT_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is invalid. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 7
    )
    public void getInvalidRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getInvalidRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setPaymentId(zidPayPaymentId);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, "MODIFY", AppConstants.AMOUNT, "abc");
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(updatedRequestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_AMOUNT_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount is null. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 8
    )
    public void getNullRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getNullRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setAmount(null);
        paymentRefund.setPaymentId(zidPayPaymentId);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_AMOUNT_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Amount Validation",
                    "SC:Refund Amount"
            },
            description = "Verifies that a refund is successfully processed when the refund amount multiple times. Executes the ZidPay payment flow and attempts to refund a partial amount validating that the refund can be completed without errors.",
            priority = 9
    )
    @SneakyThrows
    public void getMultipleRefundAmountTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getMultipleRefundAmountTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        int totalAmount = 0;

        paymentRefund.setAmount(100);
        paymentRefund.setPaymentId(zidPayPaymentId);
        totalAmount += paymentRefund.getAmount();
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        System.out.println(response.prettyPrint());
        AppUtils.validateZidPayPaymentRefund(response);

        paymentRefund.setAmount(900);
        paymentRefund.setPaymentId(zidPayPaymentId);
        totalAmount += paymentRefund.getAmount();
        requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        System.out.println(response.prettyPrint());
        AppUtils.validateZidPayPaymentRefund(response);

        Thread.sleep(61000);
        response = ZidPaySystemRequest.getZidPayPaymentStatus(zidPayPaymentId);
        System.out.println(response.prettyPrint());
        AppUtils.validateZidPayPaymentStatus(response, paymentRequest, AppConstants.PAID, totalAmount);
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Payment Id Validation",
                    "SC:Refund Payment Id"
            },
            description = "Verifies that a refund is successfully processed when the refund payment ID is missing.",
            priority = 10
    )
    public void getOmitPaymentIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getOmitPaymentIdTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        String updatedRequestBody = CommonAutomationUtils.modifyJson(requestBody, "DELETE", AppConstants.PAYMENT_ID, null);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(updatedRequestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PAYMENT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Payment Id Validation",
                    "SC:Refund Payment Id"
            },
            description = "Verifies that a refund is successfully processed when the refund payment ID is null.",
            priority = 11
    )
    public void getNUllPaymentIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getNUllPaymentIdTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setPaymentId(null);
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PAYMENT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Payment Id Validation",
                    "SC:Refund Payment Id"
            },
            description = "Verifies that a refund is successfully processed when the refund payment ID is empty.",
            priority = 12
    )
    public void getEmptyPaymentIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getEmptyPaymentIdTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setPaymentId("");
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PAYMENT_ID_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Payment Id Validation",
                    "SC:Refund Payment Id"
            },
            description = "Verifies that a refund is processed successfully when a valid payment ID is provided. ",
            priority = 13
    )
    public void getValidPaymentIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getValidPaymentIdTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setPaymentId(zidPayPaymentId);
        AppUtils.executeZidPayPaymentRefundFlow(paymentRequest, paymentRefund, zidPayPaymentId);
    }

    @Test(
            groups = {
                    "MC:Refund ZidPay Payment",
                    "Abdul Rehman",
                    "SECTION:Refund Payment Id Validation",
                    "SC:Refund Payment Id"
            },
            description = "Verifies that a refund is successfully processed when the refund payment ID is invalid provided.",
            priority = 14
    )
    public void getInvalidPaymentIdTestCase() {

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        String transactionAuthUrl = zidPayPaymentResponse.jsonPath().getString(AppConstants.TRANSACTION_URL);

        if(zidPayPaymentId == null)
            getInvalidPaymentIdTestCase();

        AppUtils.executeZidPayPaymentFlow(paymentRequest, zidPayPaymentId, transactionAuthUrl);
        PaymentRefund paymentRefund = AppUtils.zidPayPaymentRefundInstance();
        paymentRefund.setPaymentId("invalid_payment_id");
        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        CommonAutomationUtils.verifyCommonResponseFailedValidation(response, HttpStatus.SC_BAD_REQUEST, Map.of(AppConstants.MESSAGE, AppConstants.INVALID_PAYMENT_NOT_FOUND_ERROR_MSG));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY, AppConstants.ERRORS_ZERO_VALUE));
    }
}