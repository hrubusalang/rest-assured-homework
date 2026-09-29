package ui.selenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.switchTo;
import config.TestConfig;

class SelenideGoodsTest extends SelenideBaseTest {

    @Test
    void addProduct() {
        loginAsAdmin();

        String productName = TestConfig.getProductName();
        int productPrice = TestConfig.getProductPrice();

        $("#n-name")
                .setValue(productName);

        $("#n-price")
                .setValue(String.valueOf(productPrice));

        $("#add-btn")
                .click();

        open(BASE_URL);

        $(".product-card[data-name='%s']".formatted(productName))
                .shouldBe(visible)
                .shouldHave(text(productName));
    }

    @Test
    void addProductToCart() {
        open(BASE_URL);

        $(".product-card[data-name='Хлеб']")
                .shouldBe(visible)
                .find("[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .shouldBe(visible)
                .click();

        $("#cart-items .cart-item")
                .shouldBe(visible)
                .shouldHave(text("Хлеб"));

        $("#total-price")
                .shouldBe(visible)
                .shouldHave(text("25"));
    }

    @Test
    void invalidLogin() {
        open(BASE_URL + "/login");

        $("#username")
                .setValue("wrong_user");

        $("#password")
                .setValue("wrong_password");

        $("button[type='submit']")
                .click();

        $(".alert.alert-danger")
                .shouldBe(visible)
                .shouldHave(text("Неверные учетные данные пользователя"));
    }

    @Test
    void cartPersistsAfterRefresh() {
        open(BASE_URL);

        $(".product-card[data-name='Хлеб']")
                .shouldBe(visible)
                .find("[data-action='add-to-cart']")
                .click();

        $("#cart-count")
                .shouldBe(visible)
                .shouldHave(text("1"));

        $("#open-cart-btn")
                .click();

        $("#cart-items .cart-item")
                .shouldBe(visible)
                .shouldHave(text("Хлеб"));

        refresh();

        $("#cart-count")
                .shouldBe(visible)
                .shouldHave(text("1"));

        $("#open-cart-btn")
                .click();

        $("#cart-items .cart-item")
                .shouldBe(visible)
                .shouldHave(text("Хлеб"));
    }

    @Test
    void over300ShowsAlert() {
        open(BASE_URL);

        $(".product-card[data-name='Стартовый товар']")
                .shouldBe(visible)
                .find("[data-action='add-to-cart']")
                .click();

        $(".product-card[data-name='Стартовый товар']")
                .find("[data-action='add-to-cart']")
                .click();

        $(".product-card[data-name='Стартовый товар']")
                .find("[data-action='add-to-cart']")
                .click();

        $(".product-card[data-name='Стартовый товар']")
                .find("[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .shouldBe(visible)
                .click();

        $("#cart-items")
                .shouldBe(visible)
                .shouldHave(text("Стартовый товар"));

        $("#total-price")
                .shouldBe(visible)
                .shouldHave(text("400"));

        $("#makeOrder")
                .shouldBe(visible)
                .click();
    }
}