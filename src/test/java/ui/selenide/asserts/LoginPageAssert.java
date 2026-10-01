package ui.selenide.asserts;

import io.qameta.allure.Step;
import ui.selenide.pages.LoginPage;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;

public class LoginPageAssert {

    private final LoginPage loginPage;

    public LoginPageAssert(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Step("Проверить, что поле логина отображается")
    public LoginPageAssert usernameInputIsVisible() {
        loginPage.getUsernameInput()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить, что поле пароля отображается")
    public LoginPageAssert passwordInputIsVisible() {
        loginPage.getPasswordInput()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить, что кнопка Login отображается")
    public LoginPageAssert loginButtonIsVisible() {
        loginPage.getLoginButton()
                .shouldBe(visible);

        return this;
    }

    @Step("Проверить значение поля логина: {username}")
    public LoginPageAssert usernameIs(String username) {
        loginPage.getUsernameInput()
                .shouldHave(value(username));

        return this;
    }

    @Step("Проверить значение поля пароля")
    public LoginPageAssert passwordIs(String password) {
        loginPage.getPasswordInput()
                .shouldHave(value(password));

        return this;
    }
}