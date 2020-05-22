package com.socks.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.socks.api.ProjectConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;

public class BaseUITest {

    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = config.baseUrl();
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = "com.socks.ui.SelenoidDriverProvider";
    }

    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }
}
