package com.socks.ui.test;


import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class TestLogin extends BaseUITest {

    private final UserApiService userApiService = new UserApiService();

    @Test
    public void userCanLoginWithValidCredentials() {
        //given
        UserPayload user = new UserPayload()
                .username(randomAlphanumeric(6))
                .email("test@mail.com")
                .password("123456");

        userApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(200));

        MainPage.open()
                .loginAs(user.username(), user.password());

        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);
        loggedUserPage.logoutBtn().shouldHave(text("Logout"));
        loggedUserPage.userName().shouldHave(text("Logged in as"));

    }
}


