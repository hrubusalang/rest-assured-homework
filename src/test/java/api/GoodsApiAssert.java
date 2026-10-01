package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class GoodsApiAssert {

    @Step("Проверить статус ответа: ожидается {expectedStatusCode}")
    public static void statusCodeIs(
            Response response,
            int expectedStatusCode
    ) {
        assertThat(response.statusCode())
                .isEqualTo(expectedStatusCode);
    }

    @Step("Проверить, что список товаров содержит товар: {productName}")
    public static void productListContains(
            Response response,
            String productName
    ) {
        String productNames = response.jsonPath()
                .getString("goods.name");

        assertThat(productNames)
                .contains(productName);
    }

    @Step("Проверить данные товара: id={expectedId}, имя={expectedName}, цена={expectedPrice}")
    public static void productIs(
            Response response,
            int expectedId,
            String expectedName,
            int expectedPrice
    ) {
        assertThat(response.jsonPath().getInt("id"))
                .isEqualTo(expectedId);

        assertThat(response.jsonPath().getString("name"))
                .isEqualTo(expectedName);

        assertThat(response.jsonPath().getFloat("price"))
                .isEqualTo((float) expectedPrice);
    }
}