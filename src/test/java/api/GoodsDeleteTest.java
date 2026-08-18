package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsDeleteTest {

    @Test
    @Tag("api")
    void deleteProduct() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/7")
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("api")
    void deleteProductNotFound() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/7")
                .then()
                .statusCode(404);
    }
}