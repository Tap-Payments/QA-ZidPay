package com.tappayments.automation.qazidpay.base;

import com.tappayments.automation.qazidpay.config.ConfigManager;
import com.tappayments.automation.qazidpay.utils.AppConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = ConfigManager.getPropertyValue(AppConstants.BASE_URI_VALUE);
    }
}