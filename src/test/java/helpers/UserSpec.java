package helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class UserSpec {

    static final Filter FORCE_JSON_RESPONSE_BODY = (reqSpec, respSpec, ctx) -> {
        Response response = ctx.next(reqSpec, respSpec);
        ((RestAssuredResponseOptionsImpl<?>) response).setContentType("application/json");
        return response;
    };

    public static RequestSpecification customRequestSpec = with()
            .filters(FORCE_JSON_RESPONSE_BODY, new AllureRestAssured())
            .urlEncodingEnabled(true)
            .baseUri("https://automationexercise.com")
            .basePath("/api")
            .log().uri()
            .log().headers()
            .log().body();

    public static ResponseSpecification customResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
//            .expectStatusCode()
            .build()
            ;


}
