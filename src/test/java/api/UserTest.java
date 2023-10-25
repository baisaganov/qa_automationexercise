package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class UserTest {

    private static final String user_email = System.getProperty("user_registration_email");

    static final Filter FORCE_JSON_RESPONSE_BODY = (reqSpec, respSpec, ctx) -> {
        Response response = ctx.next(reqSpec, respSpec);
        ((RestAssuredResponseOptionsImpl<?>) response).setContentType("application/json");
        return response;
    };

    @Test
    @DisplayName("API 7: POST To Verify Login with valid details")
    @Tag("user_api")
    void postLoginTest(){
        given().urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("email", "test3@gamail.com")
                .multiPart("password", "Password1")
                .when()
                .post("https://automationexercise.com/api/verifyLogin")
                .then().log().all()
                .body("responseCode", is(200))
                .and()
                .body("message", is("User exists!"));
    }

    @Test
    @DisplayName("API 8: POST To Verify Login without email parameter")
    @Tag("user_api")
    void postErrorWithoutEmailLoginTest(){
        given().urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("password", "Password1")
                .when()
                .post("https://automationexercise.com/api/verifyLogin")
                .then().log().all()
                .body("responseCode", is(400))
                .and()
                .body("message", is("Bad request, email or password parameter is missing in POST request."));
    }

    @Test
    @DisplayName("API 9: DELETE To Verify Login")
    @Tag("user_api")
    void deleteToVerifyLoginTest(){
        given()
                .urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .when()
                .delete("https://automationexercise.com/api/verifyLogin")
                .then().log().all()
                .body("responseCode", is(405))
                .and()
                .body("message", is("This request method is not supported."));
    }

    @Test
    @DisplayName("API 10: POST To Verify Login with invalid details")
    @Tag("user_api")
    void postErrorWithInvalidValuesToVerifyLoginTest(){
        given()
                .urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .multiPart("email", "123")
                .multiPart("password", "123")
                .when()
                .post("https://automationexercise.com/api/verifyLogin")
                .then().log().all()
                .body("responseCode", is(404))
                .and()
                .body("message", is("User not found!"));
    }

    @Test
    @DisplayName("API 11: POST To Create/Register User Account")
    @Tag("user_api")
    void postCreateUserTest(){
        given()
                .urlEncodingEnabled(true)
                .log().parameters()
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .multiPart("email", "user_email@reas.fs" + new Random().nextInt())
                .multiPart("password", "123")
                .multiPart("name", "123")
                .multiPart("title", "Mr")
                .multiPart("birth_date", "11")
                .multiPart("birth_month", "11")
                .multiPart("birth_year", "1999")
                .multiPart("firstname", "123")
                .multiPart("lastname", "123")
                .multiPart("company", "123")
                .multiPart("address1", "123")
                .multiPart("address2", "123")
                .multiPart("country", "India")
                .multiPart("zipcode", "123")
                .multiPart("state", "123")
                .multiPart("city", "123")
                .multiPart("mobile_number", "123")
                .when()
                .post("https://automationexercise.com/api/createAccount")
                .then().log().all()
                .body("responseCode", is(201))
                .and()
                .body("message", is("User created!"));
    }


    @Test
    @DisplayName("API 12: DELETE METHOD To Delete User Account")
    @Tag("user_api")
    void deleteUserAccountTest(){
        given()
                .urlEncodingEnabled(true)
                .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
                .multiPart("email", user_email)
                .multiPart("password", "123")
                .when()
                .delete("https://automationexercise.com/api/deleteAccount")
                .then()
                .body("responseCode", is(200))
                .and()
                .body("message", is("Account deleted!"));
    }





}
