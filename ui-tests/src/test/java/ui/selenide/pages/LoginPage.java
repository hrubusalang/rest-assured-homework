package ui.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final String baseUrl;

    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("button[type='submit']");
    private final SelenideElement errorMessage = $(".alert.alert-danger");

    public LoginPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Step("Открыть страницу авторизации")
    public LoginPage openPage() {
        open(baseUrl + "/login");
        return this;
    }

    @Step("Ввести логин: {username}")
    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку Login")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Авторизоваться под пользователем: {username}")
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }

    public SelenideElement getUsernameInput() {
        return usernameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }
}