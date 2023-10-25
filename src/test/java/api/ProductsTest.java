package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class ProductsTest {

    static final Filter FORCE_JSON_RESPONSE_BODY = (reqSpec, respSpec, ctx) -> {
        Response response = ctx.next(reqSpec, respSpec);
        ((RestAssuredResponseOptionsImpl<?>) response).setContentType("application/json");
        return response;
    };

    @Test
    @DisplayName("API 1: Get All Products List")
    @Tag("products_api")
    void getAllProductsTest(){
        given()
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .get("https://automationexercise.com/api/productsList")
                .then()
                .statusCode(200)
                .body("products.size()", greaterThan(30));
    }

    @Test
    @DisplayName("API 2: POST To All Products List")
    @Tag("products_api")
    void postAllProductsTest(){
        given().urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .when()
                .post("https://automationexercise.com/api/productsList")
                .then()
                .body("responseCode", is(405))
                .and()
                .body("message", equalTo("This request method is not supported."));
    }

    @Test
    @DisplayName("API 3: Get All Brands List")
    @Tag("products_api")
    void getAllBrandsListTest(){
        given().urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .get("https://automationexercise.com/api/brandsList")
                .then()
                .body("responseCode", is(200))
                .and()
                .body("brands.size()", greaterThan(30));
    }

    @Test
    @DisplayName("API 4: PUT To All Brands List")
    @Tag("products_api")
    void putToAllBrandsListTest(){
        given().urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .put("https://automationexercise.com/api/brandsList")
                .then()
                .body("responseCode", is(405))
                .and()
                .body("message", is("This request method is not supported."));
    }

    @Test
    @DisplayName("API 5: POST To Search Product")
    @Tag("products_api")
    void postToSearchProductTest(){
        given().urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("search_product", "top")
                .when()
                .post("https://automationexercise.com/api/searchProduct")
                .then().log().all()
                .body("responseCode", is(200))
                .and()
                .body("products.size()", greaterThan(10));
    }

    @Test
    @DisplayName("API 6: POST To Search Product without search_product parameter")
    @Tag("products_api")
    void postErrorToSearchProductTest(){
        given().urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .when()
                .post("https://automationexercise.com/api/searchProduct")
                .then().log().all()
                .body("responseCode", is(400));
    }
}
