package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GoodsSmokeTest {

    @Test
    @Tag("smoke")
    @Tag("api")
    void goodsListShouldBeAvailable() {

        Response response = ProductApi.getProducts();

        GoodsApiAssert.statusCodeIs(response, 200);
    }
}