package ui.selenide.asserts;

import ui.selenide.pages.AdminPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert {

    private final AdminPage adminPage;

    public AdminPageAssert(AdminPage adminPage) {
        this.adminPage = adminPage;
    }

    public AdminPageAssert productNameInputIsVisible() {
        adminPage.getProductNameInput()
                .shouldBe(visible);

        return this;
    }

    public AdminPageAssert productPriceInputIsVisible() {
        adminPage.getProductPriceInput()
                .shouldBe(visible);

        return this;
    }

    public AdminPageAssert addProductButtonIsVisible() {
        adminPage.getAddProductButton()
                .shouldBe(visible);

        return this;
    }

    public AdminPageAssert productAddedNotificationIsVisible() {
        adminPage.getNotification()
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));

        return this;
    }

    public AdminPageAssert productUpdatedNotificationIsVisible() {
        adminPage.getNotification()
                .shouldBe(visible)
                .shouldHave(text("обновлен"));

        return this;
    }
}