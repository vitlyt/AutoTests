package main;


import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.HTTP.*;

public abstract class BaseTest {

    protected Response getPostResponse(String queryParam, String URI, String body, String schema){
        return given().contentType(ContentType.JSON)
                .queryParam("q", queryParam)
                .body(body)
                .when()
                .post(URI).then().assertThat()
                .body(matchesJsonSchemaInClasspath(schema))
                .extract().response();
    }

    protected Response getPostResponse(String URI, String body, String schema){
        return given().contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(URI).then().assertThat()
                .body(matchesJsonSchemaInClasspath(schema)).
                extract().response();
    }

    protected Response getPostResponse(String URI, Map<String, String> params){
        return given().contentType(ContentType.JSON)
                .body(params)
                .when()
                .post(URI).then()
                .extract().response();
    }



}
