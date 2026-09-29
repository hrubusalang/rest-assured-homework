package ui.selenide;

import config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SelenideBaseTest {

    protected static final String BASE_URL = TestConfig.getBaseUrl();
    protected static final String ADMIN_LOGIN = TestConfig.getAdminLogin();
    protected static final String ADMIN_PASSWORD = TestConfig.getAdminPassword();

    @BeforeEach
    void setUp() {
        timeout = TestConfig.getTimeout();

        System.out.println("=== Test configuration ===");
        System.out.println("Base URL: " + TestConfig.getBaseUrl());
        System.out.println("API URL: " + TestConfig.getApiUrl());
        System.out.println("Timeout: " + TestConfig.getTimeout());
        System.out.println("Logging: " + TestConfig.isLoggingEnabled());
        System.out.println("Product name: " + TestConfig.getProductName());
        System.out.println("Product price: " + TestConfig.getProductPrice());
        System.out.println("==========================");

        open(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    protected void loginAsAdmin() {
        open(BASE_URL + "/login");

        $("#username")
                .setValue(ADMIN_LOGIN);

        $("#password")
                .setValue(ADMIN_PASSWORD);

        $("button[type='submit']")
                .click();

        $("#n-name")
                .shouldBe(visible);
    }
}