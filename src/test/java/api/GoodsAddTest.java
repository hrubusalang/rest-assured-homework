package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsAddTest {

    @Test
    @Tag("api")
    void addProduct() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                        {
                          "name": "Product 4",
                          "price": 100
                        }
                        """)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("api")
    void addProductBadRequest() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                    {
                      "name": "Product 4",
                      "price": 100
                    }
                    """)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(400);
    }
}