package api;

import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;

public abstract class ApiBaseTest {

    protected final List<Integer> createdProductIds = new ArrayList<>();

    protected int createProduct(String name, int price) {
        int id = ProductApi.createProduct(name, price);
        createdProductIds.add(id);
        return id;
    }

    @AfterEach
    void deleteCreatedProducts() {
        for (Integer id : createdProductIds) {
            ProductApi.deleteProduct(id);
        }

        createdProductIds.clear();
    }
}