package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsDeleteTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void deleteProduct() {

        int productId = createProduct("Яблоко", 100);

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/" + productId)
                .then()
                .statusCode(200);

        createdProductIds.remove(Integer.valueOf(productId));
    }

    @Test
    @Tag("api")
    void deleteProductNotFound() {

        int productId = createProduct("Банан", 80);

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
                .delete("/goods/" + productId)
                .then()
                .statusCode(404);
    }
}