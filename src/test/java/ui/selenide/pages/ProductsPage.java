package ui.selenide.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

public class ProductsPage {

    private final String baseUrl;

    private final SelenideElement cartCount = $("#cart-count");
    private final SelenideElement openCartButton = $("#open-cart-btn");
    private final SelenideElement cartItems = $("#cart-items");
    private final SelenideElement totalPrice = $("#total-price");
    private final SelenideElement makeOrderButton = $("#makeOrder");
    private final SelenideElement orderNotification =
            $("#toast-container .toast");

    public ProductsPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ProductsPage openPage() {
        open(baseUrl);
        return this;
    }

    public ProductsPage addProductToCart(String productName) {
        $(".product-card[data-name='%s']".formatted(productName))
                .find("[data-action='add-to-cart']")
                .click();

        return this;
    }

    public ProductsPage openCart() {
        openCartButton.click();
        return this;
    }

    public ProductsPage makeOrder() {
        makeOrderButton.click();
        return this;
    }

    public ProductsPage refreshPage() {
        refresh();
        return this;
    }

    public SelenideElement getCartCount() {
        return cartCount;
    }

    public SelenideElement getOpenCartButton() {
        return openCartButton;
    }

    public SelenideElement getCartItems() {
        return cartItems;
    }

    public SelenideElement getTotalPrice() {
        return totalPrice;
    }

    public SelenideElement getMakeOrderButton() {
        return makeOrderButton;
    }

    public SelenideElement getOrderNotification() {
        return orderNotification;
    }

    public SelenideElement getProductCard(String productName) {
        return $(".product-card[data-name='%s']".formatted(productName));
    }
}