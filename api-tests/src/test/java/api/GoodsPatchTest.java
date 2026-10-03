package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GoodsPatchTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void patchProduct() {

        int productId = createProduct("Груша", 120);

        Response response =
                ProductApi.updateProduct(productId, "Персик", 180);

        GoodsApiAssert.statusCodeIs(response, 200);
        GoodsApiAssert.productIs(
                response,
                productId,
                "Персик",
                180
        );
    }

    @Test
    @Tag("api")
    void patchProductNotFound() {

        int productId = createProduct("Апельсин", 130);

        Response deleteResponse =
                ProductApi.deleteProduct(productId);

        GoodsApiAssert.statusCodeIs(deleteResponse, 200);

        createdProductIds.remove(Integer.valueOf(productId));

        Response patchResponse =
                ProductApi.updateProduct(
                        productId,
                        "Мандарин",
                        140
                );

        GoodsApiAssert.statusCodeIs(patchResponse, 404);
    }

    @Test
    @Tag("api")
    void patchProductBadRequest() {

        int productId = createProduct("Киви", 90);

        Response response =
                ProductApi.updateProduct(
                        productId,
                        "Киви",
                        -100
                );

        GoodsApiAssert.statusCodeIs(response, 400);
    }
}