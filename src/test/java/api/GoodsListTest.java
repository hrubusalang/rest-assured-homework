package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;

class GoodsListTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void getGoodsList() {

        createProduct("Хлеб", 50);

        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods.name", hasItem("Хлеб"));
    }

    @Test
    @Tag("api")
    void getGoodsListReqSpec() {

        createProduct("Слива", 150);

        RequestSpecification requestSpecification = given()
                .baseUri("http://localhost:8080");

        given(requestSpecification)
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods.name", hasItem("Слива"));
    }

    @Test
    @Tag("api")
    void addProduct() {

        createProduct("Яблоко", 100);

        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods.name", hasItem("Яблоко"));
    }

    @Test
    @Tag("api")
    void addProductAssertJ() {

        createProduct("Банан", 80);

        Response response = given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String productNames = response.jsonPath()
                .getString("goods.name");

        assertThat(productNames)
                .contains("Банан");
    }
}