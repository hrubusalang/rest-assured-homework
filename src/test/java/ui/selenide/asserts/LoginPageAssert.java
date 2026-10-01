package ui.selenide.asserts;

import ui.selenide.pages.LoginPage;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;

public class LoginPageAssert {

    private final LoginPage loginPage;

    public LoginPageAssert(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public LoginPageAssert usernameInputIsVisible() {
        loginPage.getUsernameInput()
                .shouldBe(visible);

        return this;
    }

    public LoginPageAssert passwordInputIsVisible() {
        loginPage.getPasswordInput()
                .shouldBe(visible);

        return this;
    }

    public LoginPageAssert loginButtonIsVisible() {
        loginPage.getLoginButton()
                .shouldBe(visible);

        return this;
    }

    public LoginPageAssert usernameIs(String username) {
        loginPage.getUsernameInput()
                .shouldHave(value(username));

        return this;
    }

    public LoginPageAssert passwordIs(String password) {
        loginPage.getPasswordInput()
                .shouldHave(value(password));

        return this;
    }
}