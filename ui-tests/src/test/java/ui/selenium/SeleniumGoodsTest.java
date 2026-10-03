package ui.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeleniumGoodsTest extends SeleniumBaseTest {

    @Test
    void addProduct() {
        loginAsAdmin();

        driver.findElement(By.id("n-name"))
                .sendKeys("Selenium Product");

        driver.findElement(By.id("n-price"))
                .sendKeys("100");

        driver.findElement(By.id("add-btn"))
                .click();

        driver.get(BASE_URL);

        By product = By.cssSelector(
                ".product-card[data-name='Selenium Product']"
        );

        assertTrue(
                driver.findElement(product).isDisplayed()
        );
    }

    @Test
    void addProductToCart() {
        driver.get(BASE_URL);

        driver.findElement(
                By.cssSelector(
                        ".product-card[data-name='Хлеб'] [data-action='add-to-cart']"
                )
        ).click();

        driver.findElement(By.id("open-cart-btn"))
                .click();

        assertTrue(
                driver.findElement(
                        By.cssSelector("#cart-items .cart-item")
                ).isDisplayed()
        );

        assertEquals(
                "Хлеб",
                driver.findElement(
                        By.cssSelector("#cart-items .cart-item b")
                ).getText()
        );

        assertEquals(
                "50",
                driver.findElement(By.id("total-price"))
                        .getText()
        );
    }

    @Test
    void invalidLogin() {
        driver.get(BASE_URL + "/login");

        driver.findElement(By.id("username"))
                .sendKeys("wrong_user");

        driver.findElement(By.id("password"))
                .sendKeys("wrong_password");

        driver.findElement(
                By.cssSelector("button[type='submit']")
        ).click();

        assertEquals(
                "Неверные учетные данные пользователя",
                driver.findElement(
                        By.cssSelector(".alert.alert-danger")
                ).getText()
        );
    }

    @Test
    void cartPersistsAfterRefresh() {
        driver.get(BASE_URL);

        driver.findElement(
                By.cssSelector(
                        ".product-card[data-name='Хлеб'] [data-action='add-to-cart']"
                )
        ).click();

        assertEquals(
                "1",
                driver.findElement(By.id("cart-count")).getText(),
                "После добавления Хлеба корзина должна содержать 1 товар"
        );

        driver.findElement(By.id("open-cart-btn"))
                .click();

        assertTrue(
                driver.findElement(
                        By.cssSelector("#cart-items .cart-item")
                ).isDisplayed(),
                "Добавленный товар должен отображаться в корзине"
        );

        assertEquals(
                "Хлеб",
                driver.findElement(
                        By.cssSelector("#cart-items .cart-item b")
                ).getText(),
                "В корзине должен находиться Хлеб"
        );

        driver.navigate().refresh();

        String cartCountAfterRefresh = driver.findElement(
                By.id("cart-count")
        ).getText();

        assertEquals(
                "1",
                cartCountAfterRefresh,
                "После обновления страницы корзина должна сохранить товар. Фактически товаров: "
                        + cartCountAfterRefresh
        );

    }

}