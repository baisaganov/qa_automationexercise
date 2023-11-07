package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static helpers.UserSpec.customRequestSpec;
import static helpers.UserSpec.customResponseSpec;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class ProductsTest {



    @Test
    @DisplayName("API 1: Get All Products List")
    @Tag("products_api")
    void getAllProductsTest(){
        given(customRequestSpec)
                .get("/productsList")
                .then()
                .spec(customResponseSpec)
                .statusCode(200)
                .body("products.size()", greaterThan(30));
    }

    @Test
    @DisplayName("API 2: POST To All Products List")
    @Tag("products_api")
    void postAllProductsTest(){
        given(customRequestSpec)
                .when()
                .post("/productsList")
                .then()
                .spec(customResponseSpec)
                .body("responseCode", is(405))
                .and()
                .body("message", equalTo("This request method is not supported."));
    }

    @Test
    @DisplayName("API 3: Get All Brands List")
    @Tag("products_api")
    void getAllBrandsListTest(){
        given(customRequestSpec)
                .urlEncodingEnabled(true)
                .get("/brandsList")
                .then()
                .spec(customResponseSpec)
                .body("responseCode", is(200))
                .and()
                .body("brands.size()", greaterThan(30));
    }

    @Test
    @DisplayName("API 4: PUT To All Brands List")
    @Tag("products_api")
    void putToAllBrandsListTest(){
        given(customRequestSpec)
                .urlEncodingEnabled(true)
                .put("/brandsList")
                .then()
                .spec(customResponseSpec)
                .body("responseCode", is(405))
                .and()
                .body("message", is("This request method is not supported."));
    }

    @Test
    @DisplayName("API 5: POST To Search Product")
    @Tag("products_api")
    void postToSearchProductTest(){
        given(customRequestSpec)
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("search_product", "top")
                .when()
                .post("/searchProduct")
                .then()
                .spec(customResponseSpec)
                .body("responseCode", is(200))
                .and()
                .body("products.size()", greaterThan(10));
    }

    @Test
    @DisplayName("API 6: POST To Search Product without search_product parameter")
    @Tag("products_api")
    void postErrorToSearchProductTest(){
        given(customRequestSpec)
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .when()
                .post("/searchProduct")
                .then()
                .spec(customResponseSpec)
                .body("responseCode", is(400));
    }
}
