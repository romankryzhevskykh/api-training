package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import io.restassured.http.Cookies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CartApiService extends ApiService {

    private Map<String, String> getCookie(String sid) {
        Map<String, String> cookie = new HashMap<>();
        cookie.put("md.sid", sid);
        return cookie;
    }

    public AssertableResponse addItemToCart(List restAssuredCookies, String csrfToken) {
        return new AssertableResponse(
                setup().cookies((new Cookies(restAssuredCookies)))
                        .param("CSRFToken",csrfToken)
                        .param("productCodePost", "3880500")
                        .param("qty", "1")
                        .post("/cart/add"));
    }

    public AssertableResponse choosePaymentType(List restAssuredCookies, String csrfToken) {
        return new AssertableResponse(
                setup().cookies((new Cookies(restAssuredCookies)))
                        .param("CSRFToken",csrfToken)
                        .param("costCenterId", "Pronto Services")
                        .param("paymentType", "ACCOUNT")
                        .post("/checkout/multi/payment-type/choose"));
    }

    public AssertableResponse placeOrder(List restAssuredCookies, String csrfToken) {
        return new AssertableResponse(
                setup().cookies((new Cookies(restAssuredCookies)))
                        .param("CSRFToken",csrfToken)
                        .param("_nDaysOfWeek", "on")
                        .param("_termsCheck", "on")
                        .param("nDays", "14")
                        .param("nDaysOfWeek", "MONDAY")
                        .param("nWeeks", "1")
                        .param("nthDayOfMonth", "1")
                        .param("replenishmentOrder", "false")
                        .param("termsCheck", "true")
                        .post("/checkout/multi/summary/placeOrder"));
    }

    public AssertableResponse getCart(List restAssuredCookies) {
        return new AssertableResponse(setup()
                .when()
                .cookies(new Cookies(restAssuredCookies))
                .get("/cart"));
    }

    public AssertableResponse setDeliveryMethod(List restAssuredCookies) {
        return new AssertableResponse(setup()
                .when()
                .cookies(new Cookies(restAssuredCookies))
                .get("checkout/multi/delivery-method/select?delivery_method=standard-net"));
    }
}
