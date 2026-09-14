package ui.selenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SelenideBaseTest {

    protected static final String BASE_URL = "http://localhost:8080";
    protected static final String ADMIN_LOGIN = "admin";
    protected static final String ADMIN_PASSWORD = "secret123";

    @BeforeEach
    void setUp() {
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