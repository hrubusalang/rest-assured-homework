package ui.selenide.asserts;

import ui.selenide.pages.ProductsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ProductsPageAssert {

    private final ProductsPage productsPage;

    public ProductsPageAssert(ProductsPage productsPage) {
        this.productsPage = productsPage;
    }

    public ProductsPageAssert productIsVisible(String productName) {
        productsPage.getProductCard(productName)
                .shouldBe(visible)
                .shouldHave(text(productName));

        return this;
    }

    public ProductsPageAssert cartCountIs(String expectedCount) {
        productsPage.getCartCount()
                .shouldBe(visible)
                .shouldHave(text(expectedCount));

        return this;
    }

    public ProductsPageAssert openCartButtonIsVisible() {
        productsPage.getOpenCartButton()
                .shouldBe(visible);

        return this;
    }

    public ProductsPageAssert cartContainsProduct(String productName) {
        productsPage.getCartItems()
                .shouldBe(visible)
                .shouldHave(text(productName));

        return this;
    }

    public ProductsPageAssert totalPriceIs(int expectedPrice) {
        productsPage.getTotalPrice()
                .shouldBe(visible)
                .shouldHave(text(String.valueOf(expectedPrice)));

        return this;
    }

    public ProductsPageAssert makeOrderButtonIsVisible() {
        productsPage.getMakeOrderButton()
                .shouldBe(visible);

        return this;
    }

    public ProductsPageAssert orderNotificationIsVisible() {
        productsPage.getOrderNotification()
                .shouldBe(visible)
                .shouldHave(text("Заказ принят в обработку!"));

        return this;
    }
}