package ui;

import config.TestConfig;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.assertj.core.api.Assertions.assertThat;

class UiSmokeTest {

    @Test
    @Tag("smoke")
    @Tag("ui")
    void shopPageShouldOpen() {
        openShopPage();
        checkPageTitle();
    }

    @Step("Открыть главную страницу магазина")
    void openShopPage() {
        open(TestConfig.getBaseUrl());
    }

    @Step("Проверить, что у страницы есть заголовок")
    void checkPageTitle() {
        assertThat(title()).isNotBlank();
    }
}