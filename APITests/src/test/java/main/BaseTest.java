package main;


import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.HTTP.*;

public abstract class BaseTest {

    protected Response getPostResponse(String queryParam, String URI, Object body, String schema, int expectedStatusCode){
        return given().contentType(ContentType.JSON)
                .queryParam("q", queryParam)
                .body(body)
                .when()
                .post(URI).then().assertThat()
                .body(matchesJsonSchemaInClasspath(schema)).statusCode(expectedStatusCode)
                .extract().response();
    }


    protected Object getPostResponse(String queryParam, String URI, Object body, String schema,
                                     int expectedStatusCode, Class mappingClass, String path){
        Response response = getPostResponse(queryParam, URI, body, schema, expectedStatusCode);
        return response.getBody().jsonPath().getObject(path, mappingClass);
    }


    protected Response getPostResponse(String URI, String body, String schema, int expectedStatusCode){
        return given().contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(URI).then().assertThat().statusCode(expectedStatusCode)
                .body(matchesJsonSchemaInClasspath(schema)).
                extract().response();
    }

    protected Response getPostResponse(String URI, Map<String, String> params, int expectedStatusCode){
        return given().contentType(ContentType.JSON)
                .body(params)
                .when()
                .post(URI).then().statusCode(expectedStatusCode)
                .extract().response();
    }



}
