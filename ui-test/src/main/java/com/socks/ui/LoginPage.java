package com.socks.ui;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    public static LoginPage open() {
        Selenide.open("/login");
        return new LoginPage();
    }

    public void loginAs(String name, String password) {
        $("#j_username").setValue(name);
        $("#j_password").setValue(password);
        $("#loginForm > button").click();
    }

}
