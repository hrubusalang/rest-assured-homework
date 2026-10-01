package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GoodsGetTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void getProduct() {
        int productId = createProduct("Молоко", 80);

        Response response = ProductApi.getProduct(productId);

        GoodsApiAssert.statusCodeIs(response, 200);
    }

    @Test
    @Tag("api")
    void getProductNotFound() {
        int productId = createProduct("Сыр", 200);

        Response deleteResponse = ProductApi.deleteProduct(productId);

        GoodsApiAssert.statusCodeIs(deleteResponse, 200);

        createdProductIds.remove(Integer.valueOf(productId));

        Response getResponse = ProductApi.getProduct(productId);

        GoodsApiAssert.statusCodeIs(getResponse, 404);
    }
}