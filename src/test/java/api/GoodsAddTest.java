package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsAddTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void addProduct() {

        int productId = createProduct("Хлеб", 50);

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
    void addDuplicateProductShouldReturnBadRequest() {

        createProduct("Слива", 150);

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                        {
                          "name": "Слива",
                          "price": 150
                        }
                        """)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(400);
    }
}