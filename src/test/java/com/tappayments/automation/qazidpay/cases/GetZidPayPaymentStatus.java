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

public class GetZidPayPaymentStatus extends BaseTest {

    @Test(
            groups = {
                    "MC:Get ZidPay Payment Status",
                    "Abdul Rehman",
                    "SECTION:ZidPay Payment Status Validation",
                    "SC:ZidPay Payment Status"
            },
            description = "This test case validates the correct behavior of the ZidPay Payment API when fetching the pending payment status using a valid ZidPay Payment ID.",
            priority = 1
    )
    public void getValidIdPendingPaymentStatusTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        System.out.println(zidPayPaymentResponse.prettyPrint());
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        Response response = ZidPaySystemRequest.getZidPayPaymentStatus(zidPayPaymentId);
        AppUtils.validateZidPayPaymentStatus(response, paymentRequest);
    }

    @Test(
            groups = {
                    "MC:Get ZidPay Payment Status",
                    "Abdul Rehman",
                    "SECTION:ZidPay Payment Status Validation",
                    "SC:ZidPay Payment Status"
            },
            description = "This test case validates the correct behavior of the ZidPay Payment API when fetching the paid payment status using a valid ZidPay Payment ID.",
            priority = 2
    )
    public void getValidIdPaidPaymentStatusTestCase(){

        PaymentRequest paymentRequest = AppUtils.zidPayPaymentRequestInstance();
        paymentRequest.getSource().setToken(AppUtils.getZidPayTokenValue());
        Response zidPayPaymentResponse = AppUtils.getZidPayPaymentResponse(paymentRequest);
        String zidPayPaymentId = zidPayPaymentResponse.jsonPath().getString(AppConstants.ID);
        Response response = ZidPaySystemRequest.getZidPayPaymentStatus(zidPayPaymentId);
        AppUtils.validateZidPayPaymentStatus(response, paymentRequest);
        String zidPayAuthUrl = response.jsonPath().getString(AppConstants.TRANSACTION_URL);
        AppUtils.openAuthUrlFrictionlessFlow(zidPayAuthUrl);
        response = ZidPaySystemRequest.getZidPayPaymentStatus(zidPayPaymentId);
        AppUtils.validateZidPayPaymentStatus(response, paymentRequest, AppConstants.PAID);
    }

    // Add a test case for invalid payment id
    @Test(
            groups = {
                    "MC:Get ZidPay Payment Status",
                    "Abdul Rehman",
                    "SECTION:ZidPay Payment Status Validation",
                    "SC:ZidPay Payment Status"
            },
            description = "This test case validates the correct behavior of the ZidPay Payment API when fetching the payment status using an invalid ZidPay Payment ID.",
            priority = 3
    )
    public void getInvalidIdPaymentStatusTestCase(){

        Response response = ZidPaySystemRequest.getZidPayPaymentStatus("invalidPaymentId");
        CommonAutomationUtils.verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY + "." + AppConstants.CODE, AppConstants.ERROR_INVALID_DATA_CODE, AppConstants.ERRORS_ZERO_KEY + "." + AppConstants.ERROR, AppConstants.ERROR_INVALID_DATA_VALUE));
    }

    // Add a test case for empty payment id
    @Test(
            groups = {
                    "MC:Get ZidPay Payment Status",
                    "Abdul Rehman",
                    "SECTION:ZidPay Payment Status Validation",
                    "SC:ZidPay Payment Status"
            },
            description = "This test case validates the correct behavior of the ZidPay Payment API when fetching the payment status using an empty ZidPay Payment ID.",
            priority = 4
    )
    public void getEmptyIdPaymentStatusTestCase(){

        Response response = ZidPaySystemRequest.getZidPayPaymentStatus("");
        CommonAutomationUtils.verifyStatusCode(response, HttpStatus.SC_METHOD_NOT_ALLOWED);
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.ERRORS_ZERO_KEY + "." + AppConstants.ERROR, AppConstants.ERROR_METHOD_NOT_ALLOWED_VALUE));
    }
}
