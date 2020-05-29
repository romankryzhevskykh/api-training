package com.socks.ui;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();
    }

}
