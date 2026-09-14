package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GoodsPatchTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void patchProduct() {

        int productId = createProduct("Груша", 120);

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                    {
                      "name": "Персик",
                      "price": 180
                    }
                    """)
                .when()
                .patch("/goods/" + productId)
                .then()
                .statusCode(200)
                .body("id", org.hamcrest.Matchers.equalTo(productId))
                .body("name", org.hamcrest.Matchers.equalTo("Персик"))
                .body("price", org.hamcrest.Matchers.equalTo(180.0f));
    }

    @Test
    @Tag("api")
    void patchProductNotFound() {

        int productId = createProduct("Апельсин", 130);

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
                .contentType("application/json")
                .body("""
                        {
                          "name": "Мандарин",
                          "price": 140
                        }
                        """)
                .when()
                .patch("/goods/" + productId)
                .then()
                .statusCode(404);
    }

    @Test
    @Tag("api")
    void patchProductBadRequest() {

        int productId = createProduct("Киви", 90);

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                        {
                          "name": "Киви",
                          "price": -100
                        }
                        """)
                .when()
                .patch("/goods/" + productId)
                .then()
                .statusCode(400);
    }
}