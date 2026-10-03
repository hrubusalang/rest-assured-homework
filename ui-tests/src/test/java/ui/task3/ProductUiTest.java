package ui.task3;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import api.ProductApi;
import org.junit.jupiter.api.AfterEach;

class ProductUiTest {

    private static final String BASE_URL = "http://localhost:8080";
    private Integer createdProductId;

    @AfterEach
    void deleteTestProduct() {
        if (createdProductId != null) {
            ProductApi.deleteProduct(createdProductId);
        }
    }

    @Test
    void addThreeProductsAndPlaceOrder() {
        open(BASE_URL);

        $(".product-card[data-id='3']")
                .find("button[data-action='add-to-cart']")
                .click();

        $(".product-card[data-id='3']")
                .find("button[data-action='add-to-cart']")
                .click();

        $(".product-card[data-id='3']")
                .find("button[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .click();

        $("#makeOrder")
                .click();

        $("body")
                .shouldHave(text("Заказ принят в обработку!"));
    }

    @Test
    void differentProductsShouldHaveCorrectTotalPrice() {
        open(BASE_URL);

        $(".product-card[data-id='3']")
                .find("button[data-action='add-to-cart']")
                .click();

        $(".product-card[data-name='Яблоко']")
                .find("button[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .shouldBe(visible)
                .click();

        $("#cart-items")
                .shouldBe(visible)
                .shouldHave(text("Хлеб"))
                .shouldHave(text("Яблоко"));

        $("#total-price")
                .shouldBe(visible)
                .shouldHave(text("150"));
    }

    @Test
    void adminShouldAddProductAndShowNotification() {
        open(BASE_URL + "/login");

        $("#username")
                .shouldBe(visible)
                .setValue("admin");

        $("#password")
                .shouldBe(visible)
                .setValue("secret123");

        $("button[type='submit']")
                .shouldBe(visible)
                .click();

        $("#n-name")
                .shouldBe(visible);

        $("#n-name")
                .setValue("Task3 Product");

        $("#n-price")
                .setValue("111");

        $("#add-btn")
                .shouldBe(visible)
                .click();

        $("body")
                .shouldHave(text("Товар успешно добавлен!"));
    }

    @Test
    void adminShouldEditProductAndStoreShouldShowChanges() {
        createdProductId = ProductApi.createProduct(
                "Task3 API Product",
                123
        );

        open(BASE_URL + "/login");

        $("#username")
                .setValue("admin");

        $("#password")
                .setValue("secret123");

        $("button[type='submit']")
                .click();

        $("#nm-" + createdProductId)
                .shouldBe(visible)
                .setValue("Task3 Updated Product");

        $("#pr-" + createdProductId)
                .setValue("222");

        $(".btn-upd[data-id='" + createdProductId + "']")
                .shouldBe(visible)
                .click();

        open(BASE_URL);

        $(".product-card[data-id='" + createdProductId + "']")
                .shouldBe(visible)
                .shouldHave(text("Task3 Updated Product"))
                .shouldHave(text("222"));
    }
}