package com;

import com.socks.api.conditions.Condition;
import com.socks.api.conditions.Conditions;
import com.socks.api.conditions.StatusCodeCondition;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

public class UsersTest {

    private final UserApiService userApiService = new UserApiService();

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "http://165.22.80.191/";
    }

    @Test
    public void testCanRegisterNewUser() {
        //given
        UserPayload user = new UserPayload()
                .username(RandomStringUtils.randomAlphanumeric(6))
                .email("test@mail.com")
                .password("test123");

        //expect
        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not((emptyString()))));
//                .then().log().all()
//                .assertThat()
//                .statusCode(200)
//                .body("id", not((emptyString())));
    }

//    @Test
//    public void testCannotRegisterNewUserTwice() {
//        UserPayload user = new UserPayload()
//                .username(RandomStringUtils.randomAlphanumeric(6))
//                .email("test@mail.com")
//                .password("test123");
//
//        userApiService.registerUser(user)
//                .shouldHave()
//                .then().log().all()
//                .assertThat()
//                .statusCode(200)
//                .body("id", not((emptyString())));
//
//        userApiService.registerUser(user)
//                .shouldHave()
//                .then().log().all()
//                .assertThat()
//                .statusCode(500)
//                .body("id", not((emptyString())));
//    }
}
