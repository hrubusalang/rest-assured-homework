package ui.selenide.asserts;

import io.qameta.allure.Step;
import ui.selenide.pages.ProductsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ProductsPageAssert {

    private final ProductsPage productsPage;

    public ProductsPageAssert(ProductsPage productsPage) {
        this.productsPage = productsPage;
    }

    @Step("Проверить, что товар отображается: {productName}")
    public ProductsPageAssert productIsVisible(String productName) {
        productsPage.getProductCard(productName)
                .shouldBe(visible)
                .shouldHave(text(productName));

        return this;
    }

    @Step("Проверить количество товаров в корзине: {expectedCount}")
    public ProductsPageAssert cartCountIs(String expectedCount) {
        productsPage.getCartCount()
                .shouldBe(visible)
                .shouldHave(text(expectedCount));

        return this;
    }

    @Step("Проверить, что кнопка открытия корзины отображается")
    public ProductsPageAssert openCartButtonIsVisible() {
        productsPage.getOpenCartButton()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить, что корзина содержит товар: {productName}")
    public ProductsPageAssert cartContainsProduct(String productName) {
        productsPage.getCartItems()
                .shouldBe(visible)
                .shouldHave(text(productName));

        return this;
    }

    @Step("Проверить итоговую стоимость корзины: {expectedPrice}")
    public ProductsPageAssert totalPriceIs(int expectedPrice) {
        productsPage.getTotalPrice()
                .shouldBe(visible)
                .shouldHave(text(String.valueOf(expectedPrice)));

        return this;
    }

    @Step("Проверить, что кнопка оформления заказа отображается")
    public ProductsPageAssert makeOrderButtonIsVisible() {
        productsPage.getMakeOrderButton()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить уведомление об успешном оформлении заказа")
    public ProductsPageAssert orderNotificationIsVisible() {
        productsPage.getOrderNotification()
                .shouldBe(visible)
                .shouldHave(text("Заказ принят в обработку!"));

        return this;
    }
}