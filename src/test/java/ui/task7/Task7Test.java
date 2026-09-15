package ui.task7;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

class Task7Test {

    private static final String BASE_URL = "http://localhost:8080";

    @Test
    void dragProductToCart() {
        open(BASE_URL);

        WebElement product = $(".product-card[data-name='Хлеб']")
                .shouldBe(visible)
                .toWebElement();

        WebElement cart = $("#open-cart-btn")
                .shouldBe(visible)
                .toWebElement();

        new Actions(getWebDriver())
                .dragAndDrop(product, cart)
                .perform();

        $("#open-cart-btn")
                .click();

        $("#cart-items .cart-item")
                .shouldBe(visible)
                .shouldHave(text("Хлеб"));
    }

    @Test
    void removeProductFromCart() {
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

        $("#cart-items [data-action='remove']")
                .shouldBe(visible)
                .click();

        $("#cart-items .cart-item")
                .shouldNotBe(visible);
    }
}