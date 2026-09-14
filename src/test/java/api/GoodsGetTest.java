package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsGetTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void getProduct() {

        int productId = createProduct("Молоко", 80);

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/" + productId)
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("api")
    void getProductNotFound() {

        int productId = createProduct("Сыр", 200);

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/" + productId)
                .then()
                .statusCode(200);

        createdProductIds.remove(Integer.valueOf(productId));

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/" + productId)
                .then()
                .statusCode(404);
    }
}