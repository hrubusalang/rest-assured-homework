package ui.selenide;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import ui.selenide.asserts.AdminPageAssert;
import ui.selenide.asserts.ProductsPageAssert;
import ui.selenide.pages.AdminPage;
import ui.selenide.pages.LoginPage;
import ui.selenide.pages.ProductsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

class SelenideGoodsTest extends SelenideBaseTest {

    @Test
    void shouldMakeOrderWithThreeProducts() {
        ProductsPage productsPage = new ProductsPage(BASE_URL);
        ProductsPageAssert productsPageAssert =
                new ProductsPageAssert(productsPage);

        productsPage
                .openPage()
                .addProductToCart("Хлеб")
                .addProductToCart("Хлеб")
                .addProductToCart("Хлеб")
                .openCart();

        productsPageAssert
                .cartContainsProduct("Хлеб")
                .totalPriceIs(75)
                .makeOrderButtonIsVisible();

        productsPage.makeOrder();

        productsPageAssert.orderNotificationIsVisible();
    }

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

    @Test
    void shouldCalculateTotalPriceForDifferentProducts() {
        ProductsPage productsPage = new ProductsPage(BASE_URL);
        ProductsPageAssert productsPageAssert =
                new ProductsPageAssert(productsPage);

        productsPage
                .openPage()
                .addProductToCart("Хлеб")
                .addProductToCart("Молоко")
                .addProductToCart("Сыр")
                .openCart();

        productsPageAssert
                .cartContainsProduct("Хлеб")
                .cartContainsProduct("Молоко")
                .cartContainsProduct("Сыр")
                .totalPriceIs(175);
    }

    @Test
    void shouldAddProductAsAdmin() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        AdminPage adminPage = new AdminPage();
        AdminPageAssert adminPageAssert = new AdminPageAssert(adminPage);

        loginPage
                .openPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD);

        adminPageAssert
                .productNameInputIsVisible()
                .productPriceInputIsVisible()
                .addProductButtonIsVisible();

        adminPage.addProduct("Новый товар", 80);

        adminPageAssert.productAddedNotificationIsVisible();
    }

    @Test
    void shouldEditProductAsAdmin() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        AdminPage adminPage = new AdminPage();
        AdminPageAssert adminPageAssert = new AdminPageAssert(adminPage);
        ProductsPage productsPage = new ProductsPage(BASE_URL);
        ProductsPageAssert productsPageAssert =
                new ProductsPageAssert(productsPage);

        loginPage
                .openPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD);

        adminPage.editProduct(
                "Новый товар",
                "Измененный товар",
                90
        );

        adminPageAssert.productUpdatedNotificationIsVisible();

        productsPage.openPage();

        productsPageAssert.productIsVisible("Измененный товар");
    }
}