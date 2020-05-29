package com.socks.ui.test;


import com.codeborne.selenide.WebDriverRunner;
import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.CartApiService;
import com.socks.api.services.UserApiService;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.LoginPage;
import com.socks.ui.MainPage;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static com.socks.ui.LoginPage.open;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.testng.Assert.assertEquals;

public class TestLogin extends BaseUITest {

    private CartApiService cartApiService = new CartApiService();

//    private final UserApiService userApiService = new UserApiService();
//
//    @Test
//    public void userCanLoginWithValidCredentials() {
//        //given
//        UserPayload user = new UserPayload()
//                .username(randomAlphanumeric(6))
//                .email("test@mail.com")
//                .password("123456");
//
//        userApiService.registerUser(user)
//                .shouldHave(Conditions.statusCode(200));
//
//        MainPage.open()
//                .loginAs(user.username(), user.password());
//
//        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);
//        loggedUserPage.logoutBtn().shouldHave(text("Logout"));
//        loggedUserPage.userName().shouldHave(text("Logged in as"));
//
//    }

    @Test
    public void userCanLoginWithValidCredentials() {
        LoginPage.open().loginAs("william.hunter@pronto-hw.com", "12341234");
        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);
        loggedUserPage.logoutBtn().isDisplayed();

        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        List restAssuredCookies = new ArrayList();

        for (org.openqa.selenium.Cookie cookie : cookies) {
            restAssuredCookies.add(new io.restassured.http.Cookie.Builder(cookie.getName(), cookie.getValue()).build());
        }

            String csrfToken = cartApiService.getCart(restAssuredCookies).shouldHave(statusCode(200)).getHtmlValue();
            cartApiService.addItemToCart(restAssuredCookies, csrfToken);
            cartApiService.choosePaymentType(restAssuredCookies, csrfToken);
            cartApiService.setDeliveryMethod(restAssuredCookies);


            cartApiService.placeOrder(restAssuredCookies, csrfToken);
        }

    }

