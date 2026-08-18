package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsPatchTest {

    @Test
    @Tag("api")
    void patchProduct() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                        {
                          "name": "Product Patch Updated",
                          "price": 200
                        }
                        """)
                .when()
                .patch("/goods/8")
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("api")
    void patchProductNotFound() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                    {
                      "name": "Product Not Found",
                      "price": 200
                    }
                    """)
                .when()
                .patch("/goods/7")
                .then()
                .statusCode(404);
    }

    @Test
    @Tag("api")
    void patchProductBadRequest() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                    {
                      "name": "Product Patch Bad",
                      "price": -100
                    }
                    """)
                .when()
                .patch("/goods/8")
                .then()
                .statusCode(400);
    }
}