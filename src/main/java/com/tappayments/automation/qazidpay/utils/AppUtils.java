package com.tappayments.automation.qazidpay.utils;

import com.tappayments.automation.qazidpay.App;
import com.tappayments.automation.qazidpay.model.PaymentRefund;
import com.tappayments.automation.qazidpay.model.PaymentRequest;
import com.tappayments.automation.qazidpay.requests.ZidPaySystemRequest;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertTrue;

public class AppUtils {

    @SneakyThrows
    public static Map<String, String> zidPayTokenRequestBody(){

        Thread.sleep(5000);
        Map<String, String> zidPayTokenRequestBody = new HashMap<>();
        zidPayTokenRequestBody.put("publishableApiKey", AppConstants.PUBLISHABLE_API_KEY_VALUE);
        zidPayTokenRequestBody.put("providerMerchantId", AppConstants.PROVIDER_MERCHANT_ID_VALUE);
        zidPayTokenRequestBody.put("number", AppUtils.encryptCardDetails(AppConstants.REQUEST_BODY_FRICTIONLESS_CARD_BODY));
        return zidPayTokenRequestBody;
    }

    @SneakyThrows
    public static String getZidPayTokenValue(){

        Response response = ZidPaySystemRequest.postZidPayTokenResponse();
        String tokenId = response.jsonPath().getString("token");
        if (tokenId != null && !tokenId.isEmpty())
            return tokenId;

        Thread.sleep(5000);
        return getZidPayTokenValue();
    }

    public static Response getZidPayPaymentResponse(PaymentRequest paymentRequest){

        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        return ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
    }

    public static String getZidPayPaymentAuthUrl(PaymentRequest paymentRequest){

        paymentRequest.getSource().setToken(getZidPayTokenValue());
        String requestBody = CommonAutomationUtils.stringToJson(paymentRequest);
        Response response = ZidPaySystemRequest.postZidPayPaymentResponse(requestBody);
        return response.jsonPath().getString(AppConstants.TRANSACTION_URL);
    }

    public static void validateZidPayTokenValue(Response response, Map<String, String> validationResponse) {

        CommonAutomationUtils.verifyStatusCode(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.TOKEN));
        assertTrue(validationResponse.get("token_id").startsWith("tok_"), "The value does not start with 'tok_'");
    }

    @SneakyThrows
    public static PaymentRequest zidPayPaymentRequestInstance() {

        Thread.sleep(5000);
        Map<String, String> metadata = new HashMap<>();
        metadata.put(AppConstants.PAYMENT_REFERENCE_ID, UUID.randomUUID().toString());
        metadata.put(AppConstants.CUSTOMER_NAME, "Test Customer");
        metadata.put(AppConstants.CUSTOMER_MOBILE, "500000005");
        metadata.put(AppConstants.CUSTOMER_MOBILE_COUNTRY_CODE, "966");
        metadata.put(AppConstants.CUSTOMER_EMAIL, "majed.omran@zid.sa");
        metadata.put(AppConstants.CARD_BRAND, "mada");
        metadata.put(AppConstants.CALL_BACK_URL, "https://balsam-store.com/checkout/{orderId}/zidpay/callback");
        metadata.put(AppConstants.METADATA_ERROR_URL, "https://balsam-store.com/checkout/{orderId}/zidpay/error");
        metadata.put(AppConstants.WEBHOOK_URL, "https://api.zid.sa/api/v1/payments/zidpay/{orderId}");
        metadata.put(AppConstants.PAYMENT_METHOD, "6");
        metadata.put(AppConstants.METADATA_AMOUNT, "154");
        metadata.put(AppConstants.DISPLAY_CURRENCY_ISO, "KWD");
        metadata.put(AppConstants.CUSTOMER_REFERENCE, "3248111");
        metadata.put(AppConstants.MERCHANT_REFERENCE_ID, "8bf55837-60e0-4d7d-b0db-ec7b5b5ad6a3");
        metadata.put(AppConstants.AFFILIATE_LINK, "https://balsam-store.com/affiliate/");
        metadata.put(AppConstants.PAYMENT_LINK_ID, "");
        metadata.put(AppConstants.APPLE_PAY_QUICK_CHECKOUT, "false");
        metadata.put(AppConstants.COUPON_CODE, "");
        metadata.put(AppConstants.SHIPPING_FEES, "");
        metadata.put(AppConstants.SHIPPING_METHOD_NAME, "");
        metadata.put(AppConstants.DISCOUNT, "");

        return PaymentRequest.builder()
                .amount(24913)
                .currency("SAR")
                .callbackUrl("https://zid.sa/en/")
                .errorUrl("https://dribbble.com/tags/payment-failed")
                .providerMerchantId("merchant_cWFE55241559X1Jr7Ua9c419")
                .source(PaymentRequest.Source.builder()
                        .type("token")
                        .token("")
                        .build())
                .metadata(metadata)
                .build();
    }

    public static void validateZidPayPayment(Response response, PaymentRequest paymentRequest) {

        String chargeId = response.jsonPath().getString(AppConstants.ID);

        CommonAutomationUtils.verifyStatusCode(response, HttpStatus.SC_OK);
        assertTrue(chargeId.startsWith("chg_"), "The value does not start with 'chr_'");
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.STATUS, AppConstants.PENDING, AppConstants.CURRENCY, paymentRequest.getCurrency(), AppConstants.AMOUNT, paymentRequest.getAmount()));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.TRANSACTION_URL));
    }

    public static void validateZidPayPaymentStatus(Response response, PaymentRequest paymentRequest) {

        validateZidPayPaymentStatus(response, paymentRequest, AppConstants.PENDING.toUpperCase());
    }

    public static void validateZidPayPaymentStatus(Response response, PaymentRequest paymentRequest, String expectedStatus) {

        validateZidPayPaymentStatus(response, paymentRequest, expectedStatus, null);
    }

    public static void validateZidPayPaymentStatus(Response response, PaymentRequest paymentRequest, String expectedStatus, Integer refundAmount) {

        String chargeId = response.jsonPath().getString(AppConstants.ID);

        CommonAutomationUtils.verifyStatusCode(response, HttpStatus.SC_OK);
        assertTrue(chargeId.startsWith("chg_"), "The value does not start with 'chr_'");
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.STATUS, expectedStatus, AppConstants.CURRENCY, paymentRequest.getCurrency(), AppConstants.AMOUNT, paymentRequest.getAmount()));
        if(expectedStatus.equalsIgnoreCase(AppConstants.PENDING))
            CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.TRANSACTION_URL));
        if(refundAmount != null){
            CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.REFUNDED, refundAmount));
            CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.REFUNDED_AT));
        }
    }

    public static void validateZidPayPaymentRefund(Response response) {

        CommonAutomationUtils.verifyStatusCode(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.REFUND_ID, AppConstants.REFUND_REFERENCE));
        CommonAutomationUtils.verifyExactMatch(response, Map.of(AppConstants.STATUS, AppConstants.REFUNDED));

    }

    @SneakyThrows
    public static PublicKey getPublicKeyFromString() {

        byte[] keyBytes = Base64.getDecoder().decode(AppConstants.PUBLIC_KEY_VALUE);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(AppConstants.ENCRYPTION_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    @SneakyThrows
    public static String encryptCardDetails(String requestBody) {

        Cipher cipher = Cipher.getInstance(AppConstants.ENCRYPTION_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKeyFromString());
        byte[] encryptedBytes = cipher.doFinal(requestBody.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static void openAuthUrlFrictionlessFlow(String zidPayPaymentAuthUrl) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(zidPayPaymentAuthUrl);
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleContains("Processing"));
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @SneakyThrows
    public static PaymentRefund zidPayPaymentRefundInstance(){

        Thread.sleep(5000);
        return PaymentRefund.builder()
                .amount(100)
                .paymentId("chg_123456789")
                .build();
    }

    public static void executeZidPayPaymentFlow(PaymentRequest paymentRequest, String zidPayPaymentId, String transactionAuthUrl){

        AppUtils.openAuthUrlFrictionlessFlow(transactionAuthUrl);
        Response response = ZidPaySystemRequest.getZidPayPaymentStatus(zidPayPaymentId);
        AppUtils.validateZidPayPaymentStatus(response, paymentRequest, AppConstants.PAID);
    }

    @SneakyThrows
    public static void executeZidPayPaymentRefundFlow(PaymentRequest paymentRequest, PaymentRefund paymentRefund, String zidPayPaymentId){

        String requestBody = CommonAutomationUtils.stringToJson(paymentRefund);
        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(requestBody);
        AppUtils.validateZidPayPaymentRefund(response);
        response = ZidPaySystemRequest.getZidPayPaymentStatus(zidPayPaymentId);
        AppUtils.validateZidPayPaymentStatus(response, paymentRequest, AppConstants.PAID, paymentRefund.getAmount());
    }

    @SneakyThrows
    public static void executeZidPayPaymentRefundFlow(PaymentRequest paymentRequest, PaymentRefund paymentRefund, String paymentRefundRequestBody, String zidPayPaymentId){

        Response response = ZidPaySystemRequest.postZidPayPaymentRefund(paymentRefundRequestBody);
        AppUtils.validateZidPayPaymentRefund(response);
        Thread.sleep(5000);
        response = ZidPaySystemRequest.getZidPayPaymentStatus(zidPayPaymentId);
        AppUtils.validateZidPayPaymentStatus(response, paymentRequest, AppConstants.PAID, paymentRefund.getAmount());
    }

}