package api;

import config.TestConfig;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductApi {

    private static final String BASE_URL = TestConfig.getApiUrl();
    private static final String ADMIN_LOGIN = TestConfig.getAdminLogin();
    private static final String ADMIN_PASSWORD = TestConfig.getAdminPassword();

    @Step("Создать товар через API: {name}, цена: {price}")
    public static int createProduct(String name, int price) {
        return given()
                .filter(new AllureRestAssured())
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

    @Step("Получить товар через API: id={id}")
    public static Response getProduct(int id) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(BASE_URL)
                .auth()
                .basic(ADMIN_LOGIN, ADMIN_PASSWORD)
                .when()
                .get("/goods/" + id);
    }

    @Step("Получить список товаров через API")
    public static Response getProducts() {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(BASE_URL)
                .when()
                .get("/goods/list");
    }

    @Step("Создать товар через API и вернуть полный ответ: {name}, цена: {price}")
    public static Response addProduct(String name, int price) {
        return given()
                .filter(new AllureRestAssured())
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
                .post("/goods/add");
    }

    @Step("Обновить товар через API: id={id}, имя={name}, цена={price}")
    public static Response updateProduct(int id, String name, int price) {
        return given()
                .filter(new AllureRestAssured())
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
                .patch("/goods/" + id);
    }

    @Step("Удалить товар через API: id={id}")
    public static Response deleteProduct(int id) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(BASE_URL)
                .auth()
                .basic(ADMIN_LOGIN, ADMIN_PASSWORD)
                .when()
                .delete("/goods/" + id);
    }
}