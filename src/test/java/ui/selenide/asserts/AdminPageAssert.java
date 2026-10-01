package ui.selenide.asserts;

import io.qameta.allure.Step;
import ui.selenide.pages.AdminPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert {

    private final AdminPage adminPage;

    public AdminPageAssert(AdminPage adminPage) {
        this.adminPage = adminPage;
    }

    @Step("Проверить, что поле названия товара отображается")
    public AdminPageAssert productNameInputIsVisible() {
        adminPage.getProductNameInput()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить, что поле цены товара отображается")
    public AdminPageAssert productPriceInputIsVisible() {
        adminPage.getProductPriceInput()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить, что кнопка добавления товара отображается")
    public AdminPageAssert addProductButtonIsVisible() {
        adminPage.getAddProductButton()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить уведомление об успешном добавлении товара")
    public AdminPageAssert productAddedNotificationIsVisible() {
        adminPage.getNotification()
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));

        return this;
    }

    @Step("Проверить уведомление об успешном обновлении товара")
    public AdminPageAssert productUpdatedNotificationIsVisible() {
        adminPage.getNotification()
                .shouldBe(visible)
                .shouldHave(text("обновлен"));

        return this;
    }
}