package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ProductApi {

    @Step("Создать товар через API: {name}, цена: {price}")
    public static int createProduct(String name, int price) {
        return RestApiBuilder.adminRequest()
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
        return RestApiBuilder.adminRequest()
                .when()
                .get("/goods/" + id);
    }

    @Step("Получить список товаров через API")
    public static Response getProducts() {
        return RestApiBuilder.request()
                .when()
                .get("/goods/list");
    }

    @Step("Создать товар через API и вернуть полный ответ: {name}, цена: {price}")
    public static Response addProduct(String name, int price) {
        return RestApiBuilder.adminRequest()
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
        return RestApiBuilder.adminRequest()
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
        return RestApiBuilder.adminRequest()
                .when()
                .delete("/goods/" + id);
    }
}