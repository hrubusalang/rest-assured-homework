package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GoodsAddTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void addProduct() {

        int productId = createProduct("Хлеб", 50);

        Response response = ProductApi.getProduct(productId);

        GoodsApiAssert.statusCodeIs(response, 200);
    }

    @Test
    @Tag("api")
    void addDuplicateProductShouldReturnBadRequest() {

        createProduct("Слива", 150);

        Response response = ProductApi.addProduct("Слива", 150);

        GoodsApiAssert.statusCodeIs(response, 400);
    }
}