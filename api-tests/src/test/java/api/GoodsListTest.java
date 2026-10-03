package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GoodsListTest extends ApiBaseTest {

    @Test
    @Tag("api")
    void getGoodsList() {

        createProduct("Хлеб", 50);

        Response response = ProductApi.getProducts();

        GoodsApiAssert.statusCodeIs(response, 200);
        GoodsApiAssert.productListContains(response, "Хлеб");
    }

    @Test
    @Tag("api")
    void getGoodsListReqSpec() {

        createProduct("Слива", 150);

        Response response = ProductApi.getProducts();

        GoodsApiAssert.statusCodeIs(response, 200);
        GoodsApiAssert.productListContains(response, "Слива");
    }

    @Test
    @Tag("api")
    void addProduct() {

        createProduct("Яблоко", 100);

        Response response = ProductApi.getProducts();

        GoodsApiAssert.statusCodeIs(response, 200);
        GoodsApiAssert.productListContains(response, "Яблоко");
    }

    @Test
    @Tag("api")
    void addProductAssertJ() {

        createProduct("Банан", 80);

        Response response = ProductApi.getProducts();

        GoodsApiAssert.statusCodeIs(response, 200);
        GoodsApiAssert.productListContains(response, "Банан");
    }
}