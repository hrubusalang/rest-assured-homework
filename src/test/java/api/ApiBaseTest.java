package api;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;

public abstract class ApiBaseTest {

    protected final List<Integer> createdProductIds = new ArrayList<>();

    @Step("Создать тестовый товар: {name}, цена: {price}")
    protected int createProduct(String name, int price) {
        int id = ProductApi.createProduct(name, price);
        createdProductIds.add(id);
        return id;
    }

    @AfterEach
    @Step("Удалить созданные тестовые товары")
    void deleteCreatedProducts() {
        for (Integer id : createdProductIds) {
            ProductApi.deleteProduct(id);
        }

        createdProductIds.clear();
    }
}