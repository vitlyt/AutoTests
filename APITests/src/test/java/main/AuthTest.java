package main;

import conf.AuthFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static utils.Utils.*;

import java.util.HashMap;
import java.util.Map;


import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import static utils.HTTP.Status.*;

public class AuthTest extends BaseTest{

    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private static final String COOKIE_TOKEN = "jwt-token";

    @Test
    public void givenInvalidCredentialsWhenLoginThenReturn400Error(){
        Map<String, String> params = new HashMap<>(){{
            put(USERNAME_FIELD, "nonexistent@test.com");
            put(PASSWORD_FIELD, "password123");
        }};

        Response response = getPostResponse(getLoginURI(), params, BAD_REQUEST);

        assertNull(response.getCookie(COOKIE_TOKEN));

        assertEquals(response.getBody().jsonPath().getString("\"model.Password\"[0]"),
                "The Username or Password field is incorrect.");
        assertEquals(response.getBody().jsonPath().getString("\"model.Username\"[0]"),
                "The Username or Password field is incorrect.");
    }


    @Test
    public void givenValidCredentialsWhenLoginThenReturn200() {
        Map<String, String> params = new HashMap<>(){{
            put(USERNAME_FIELD, System.getProperty("username"));
            put(PASSWORD_FIELD, System.getProperty("password"));
        }};

        Response response = getPostResponse(getLoginURI(), params, OK);

        String jwsToken = response.getCookie(COOKIE_TOKEN);

        RestAssured.filters(new AuthFilter(jwsToken));
    }


    @Test
    public void givenGetRequestWhenLogoutThenReturn204()  {

        Response response = given().contentType(ContentType.JSON)
                .when()
                .get(getLogoutURI()).then().extract().response();

        assertEquals(response.statusCode(), NO_CONTENT);
        assertTrue(response.getCookie(COOKIE_TOKEN).isEmpty());

    }


}
