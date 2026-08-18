package api;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;

class GoodsListTest {

    @Tag("api")
    @Test
    void getGoodsList() {
        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods", empty());
    }

    @Tag("api")
    @Test
    void getGoodsListReqSpec() {

        RequestSpecification requestSpecification = given()
                .baseUri("http://localhost:8080");

        given(requestSpecification)
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods", empty());
    }

    @Tag("api")
    @Test
    void addProduct() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                    {
                      "name": "Test product 2",
                      "price": 100
                    }
                    """)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200);

        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods.name", hasItem("Test product 2"));
    }

    @Tag("api")
    @Test
    void addProductAssertJ() {

        given()
                .baseUri("http://localhost:8080")
                .auth()
                .basic("admin", "secret123")
                .contentType("application/json")
                .body("""
                    {
                      "name": "Test product 3",
                      "price": 100
                    }
                    """)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200);

        Response response = given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String productName = response.jsonPath().getString("goods[1].name");

        assertThat(productName).isEqualTo("Test product 3");
    }
}
