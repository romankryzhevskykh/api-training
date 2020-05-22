package com.socks.ui.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.socks.api.services.CartApiService;
import com.socks.ui.CatalogPage;
import com.socks.ui.ShoppingCartPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseUITest {

    private CartApiService cartApiService = new CartApiService();

    @Test
    public void userCanAddItemToCartFromCatalog() {
        CatalogPage.open()
                .addItemByIndex(0)
                .goToCart();

        at(ShoppingCartPage.class).totalAmount().shouldHave(Condition.exactText("$104.98"));

    }

    @Test
    public void testCanDeleteItemFromCart() {
        ShoppingCartPage.open();

        String cookies = WebDriverRunner.getWebDriver().manage().getCookieNamed("md.sid").getValue();

        cartApiService.addItemToCart("03fef6ac-1896-4ce8-bd69-b798f85c6e0b", cookies);

        ShoppingCartPage.open()
                .deleteItem()
                .totalAmount().shouldHave(Condition.exactText("$0.00"));

    }

    @AfterMethod
    public void tearDown() {
        Selenide.clearBrowserCookies();
    }

}
