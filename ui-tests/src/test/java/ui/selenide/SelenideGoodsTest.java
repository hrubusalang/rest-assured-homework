package ui.selenide;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import ui.selenide.asserts.AdminPageAssert;
import ui.selenide.asserts.LoginPageAssert;
import ui.selenide.asserts.ProductsPageAssert;
import ui.selenide.pages.AdminPage;
import ui.selenide.pages.LoginPage;
import ui.selenide.pages.ProductsPage;

class SelenideGoodsTest extends SelenideBaseTest {

    @Test
    void shouldMakeOrderWithThreeProducts() {
        ProductsPage page = new ProductsPage(BASE_URL);
        ProductsPageAssert checks = new ProductsPageAssert(page);

        page.openPage()
                .addProductToCart("Хлеб")
                .addProductToCart("Хлеб")
                .addProductToCart("Хлеб")
                .openCart();

        checks.cartContainsProduct("Хлеб")
                .totalPriceIs(75)
                .makeOrderButtonIsVisible();

        page.makeOrder();

        checks.orderNotificationIsVisible();
    }

    @Test
    void addProduct() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        AdminPage adminPage = new AdminPage();
        ProductsPage productsPage = new ProductsPage(BASE_URL);
        ProductsPageAssert checks = new ProductsPageAssert(productsPage);

        loginPage.openPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD);

        String name = TestConfig.getProductName();
        int price = TestConfig.getProductPrice();

        adminPage.addProduct(name, price);

        productsPage.openPage();
        checks.productIsVisible(name);
    }

    @Test
    void addProductToCart() {
        ProductsPage page = new ProductsPage(BASE_URL);
        ProductsPageAssert checks = new ProductsPageAssert(page);

        page.openPage()
                .addProductToCart("Хлеб")
                .openCart();

        checks.cartContainsProduct("Хлеб")
                .totalPriceIs(25);
    }

    @Test
    void invalidLogin() {
        LoginPage page = new LoginPage(BASE_URL);
        LoginPageAssert checks = new LoginPageAssert(page);

        page.openPage()
                .login("wrong_user", "wrong_password");

        checks.invalidCredentialsMessageIsVisible();
    }

    @Test
    void cartPersistsAfterRefresh() {
        ProductsPage page = new ProductsPage(BASE_URL);
        ProductsPageAssert checks = new ProductsPageAssert(page);

        page.openPage()
                .addProductToCart("Хлеб");

        checks.cartCountIs("1");

        page.openCart();
        checks.cartContainsProduct("Хлеб");

        page.refreshPage();

        checks.cartCountIs("1");

        page.openCart();
        checks.cartContainsProduct("Хлеб");
    }

    @Test
    void over300ShowsAlert() {
        ProductsPage page = new ProductsPage(BASE_URL);
        ProductsPageAssert checks = new ProductsPageAssert(page);

        String productName = TestConfig.getProductName();

        page.openPage()
                .addProductToCart(productName)
                .addProductToCart(productName)
                .addProductToCart(productName)
                .addProductToCart(productName)
                .openCart();

        checks.cartContainsProduct(productName)
                .totalPriceIs(TestConfig.getProductPrice() * 4);

        page.makeOrder();
    }

    @Test
    void shouldCalculateTotalPriceForDifferentProducts() {
        ProductsPage page = new ProductsPage(BASE_URL);
        ProductsPageAssert checks = new ProductsPageAssert(page);

        page.openPage()
                .addProductToCart("Хлеб")
                .addProductToCart("Молоко")
                .addProductToCart("Сыр")
                .openCart();

        checks.cartContainsProduct("Хлеб")
                .cartContainsProduct("Молоко")
                .cartContainsProduct("Сыр")
                .totalPriceIs(175);
    }

    @Test
    void shouldAddProductAsAdmin() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        AdminPage adminPage = new AdminPage();
        AdminPageAssert checks = new AdminPageAssert(adminPage);

        loginPage.openPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD);

        checks.productNameInputIsVisible()
                .productPriceInputIsVisible()
                .addProductButtonIsVisible();

        adminPage.addProduct("Новый товар", 80);

        checks.productAddedNotificationIsVisible();
    }

    @Test
    void shouldEditProductAsAdmin() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        AdminPage adminPage = new AdminPage();
        AdminPageAssert adminChecks = new AdminPageAssert(adminPage);

        ProductsPage productsPage = new ProductsPage(BASE_URL);
        ProductsPageAssert productChecks =
                new ProductsPageAssert(productsPage);

        loginPage.openPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD);

        adminPage.editProduct(
                "Новый товар",
                "Измененный товар",
                90
        );

        adminChecks.productUpdatedNotificationIsVisible();

        productsPage.openPage();
        productChecks.productIsVisible("Измененный товар");
    }
}