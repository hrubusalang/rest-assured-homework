package ui.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class AdminPage {

    private final SelenideElement productNameInput = $("#n-name");
    private final SelenideElement productPriceInput = $("#n-price");
    private final SelenideElement addProductButton = $("#add-btn");
    private final SelenideElement notification = $(".toast");

    @Step("Ввести название товара: {productName}")
    public AdminPage setProductName(String productName) {
        productNameInput.setValue(productName);
        return this;
    }

    @Step("Ввести цену товара: {productPrice}")
    public AdminPage setProductPrice(int productPrice) {
        productPriceInput.setValue(String.valueOf(productPrice));
        return this;
    }

    @Step("Нажать кнопку добавления товара")
    public AdminPage clickAddProductButton() {
        addProductButton.click();
        return this;
    }

    @Step("Добавить товар: {productName}, цена: {productPrice}")
    public AdminPage addProduct(String productName, int productPrice) {
        return setProductName(productName)
                .setProductPrice(productPrice)
                .clickAddProductButton();
    }

    private SelenideElement getProductRow(String productName) {
        return $("input[value='%s']".formatted(productName))
                .closest("tr");
    }

    @Step("Изменить товар {currentProductName} на {newProductName}, цена: {newPrice}")
    public AdminPage editProduct(
            String currentProductName,
            String newProductName,
            int newPrice
    ) {
        SelenideElement productRow = getProductRow(currentProductName);

        productRow
                .$("[id^='nm-']")
                .setValue(newProductName);

        productRow
                .$("[id^='pr-']")
                .setValue(String.valueOf(newPrice));

        productRow
                .$("[data-action='update']")
                .click();

        return this;
    }

    public SelenideElement getProductNameInput() {
        return productNameInput;
    }

    public SelenideElement getProductPriceInput() {
        return productPriceInput;
    }

    public SelenideElement getAddProductButton() {
        return addProductButton;
    }

    public SelenideElement getNotification() {
        return notification;
    }
}