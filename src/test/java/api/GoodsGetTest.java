package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsGetTest {

    @Test
    @Tag("api")
    void getProduct() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/7")
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("api")
    void getProductNotFound() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/1")
                .then()
                .statusCode(404);
    }
}