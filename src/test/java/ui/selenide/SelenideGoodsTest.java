package ui.selenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.switchTo;

class SelenideGoodsTest extends SelenideBaseTest {

    @Test
    void addProduct() {
        loginAsAdmin();

        $("#n-name")
                .setValue("Selenide Product");

        $("#n-price")
                .setValue("100");

        $("#add-btn")
                .click();

        open(BASE_URL);

        $(".product-card[data-name='Selenide Product']")
                .shouldBe(visible)
                .shouldHave(text("Selenide Product"));
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
                .shouldHave(text("50"));
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

        $(".product-card[data-name='Яблоко']")
                .shouldBe(visible)
                .find("[data-action='add-to-cart']")
                .click();

        $(".product-card[data-name='Яблоко']")
                .find("[data-action='add-to-cart']")
                .click();

        $(".product-card[data-name='Яблоко']")
                .find("[data-action='add-to-cart']")
                .click();

        $(".product-card[data-name='Яблоко']")
                .find("[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .shouldBe(visible)
                .click();

        $("#cart-items")
                .shouldBe(visible)
                .shouldHave(text("Яблоко"));

        $("#total-price")
                .shouldBe(visible)
                .shouldHave(text("400"));

        $("#makeOrder")
                .shouldBe(visible)
                .click();

        String alertText = switchTo()
                .alert()
                .getText();

        if (!alertText.contains(
                "Денег не хватает! Сумма 400 ₽ превышает лимит 300 ₽"
        )) {
            throw new AssertionError(
                    "Неожиданный текст Alert: " + alertText
            );
        }

        switchTo()
                .alert()
                .accept();
    }
}