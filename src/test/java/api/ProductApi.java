package api;

import config.TestConfig;

import static io.restassured.RestAssured.given;

public class ProductApi {

    private static final String BASE_URL = TestConfig.getApiUrl();
    private static final String ADMIN_LOGIN = TestConfig.getAdminLogin();
    private static final String ADMIN_PASSWORD = TestConfig.getAdminPassword();

    public static int createProduct(String name, int price) {
        return given()
                .baseUri(BASE_URL)
                .auth()
                .basic(ADMIN_LOGIN, ADMIN_PASSWORD)
                .contentType("application/json")
                .body("""
                        {
                          "name": "%s",
                          "price": %d
                        }
                        """.formatted(name, price))
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("data.id");
    }

    public static void updateProduct(int id, String name, int price) {
        given()
                .baseUri(BASE_URL)
                .auth()
                .basic(ADMIN_LOGIN, ADMIN_PASSWORD)
                .contentType("application/json")
                .body("""
                        {
                          "name": "%s",
                          "price": %d
                        }
                        """.formatted(name, price))
                .when()
                .patch("/goods/" + id)
                .then()
                .statusCode(200);
    }

    public static void deleteProduct(int id) {
        given()
                .baseUri(BASE_URL)
                .auth()
                .basic(ADMIN_LOGIN, ADMIN_PASSWORD)
                .when()
                .delete("/goods/" + id)
                .then()
                .statusCode(200);
    }
}