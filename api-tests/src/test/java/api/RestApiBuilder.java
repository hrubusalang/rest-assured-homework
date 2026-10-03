package api;

import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {

    public static RequestSpecification request() {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(TestConfig.getApiUrl());
    }

    public static RequestSpecification adminRequest() {
        return request()
                .auth()
                .basic(
                        TestConfig.getAdminLogin(),
                        TestConfig.getAdminPassword()
                );
    }
}