package com.socks.ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import sun.security.util.ByteArrayTagOrder;

import static com.codeborne.selenide.Selenide.$;

public class LoggedUserPage {
    public SelenideElement logoutBtn() {
        return $(By.linkText("Sign Out"));
    }

    public SelenideElement userName() {
        return $("#howdy > a");
    }

}
