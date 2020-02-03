package com;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

public class UsersTest {

    private final UserApiService userApiService = new UserApiService();
    private Faker faker;

    @BeforeMethod
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        faker = new Faker(new Locale(config.locale()));
        RestAssured.baseURI = config.baseUrl();
    }

    @Test
    public void testCanRegisterNewUser() {
        //given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        //expect
        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
//                .asPojo(UserRegistrationResponse.class);
                .shouldHave(bodyField("id", not((emptyString()))));
    }

    @Test
    public void testCannotRegisterNewUserTwice() {
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not((emptyString()))));

        userApiService.registerUser(user)
                .shouldHave(statusCode(500));
    }
}
