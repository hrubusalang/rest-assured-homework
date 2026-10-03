package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GoodsDeleteTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void deleteProduct() {

        int productId = createProduct("Яблоко", 100);

        Response response = ProductApi.deleteProduct(productId);

        GoodsApiAssert.statusCodeIs(response, 200);

        createdProductIds.remove(Integer.valueOf(productId));
    }

    @Test
    @Tag("api")
    void deleteProductNotFound() {

        int productId = createProduct("Банан", 80);

        Response deleteResponse = ProductApi.deleteProduct(productId);

        GoodsApiAssert.statusCodeIs(deleteResponse, 200);

        createdProductIds.remove(Integer.valueOf(productId));

        Response secondDeleteResponse =
                ProductApi.deleteProduct(productId);

        GoodsApiAssert.statusCodeIs(secondDeleteResponse, 404);
    }
}