package main;

import conf.AuthFilter;
import dataproviders.AuthDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static utils.Utils.*;

import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import static utils.HTTP.Status.*;

public class AuthTest extends BaseTest{


    private static final String COOKIE_TOKEN = "jwt-token";

    @Test(dataProvider = "invalid-auth-data-provider", dataProviderClass = AuthDataProvider.class)
    public void givenInvalidCredentialsWhenLoginThenReturn400Error(Map<String, String> params){

        Response response = getPostResponse(getLoginURI(), params, BAD_REQUEST);

        assertNull(response.getCookie(COOKIE_TOKEN));

        assertEquals(response.getBody().jsonPath().getString("\"model.Password\"[0]"),
                "The Username or Password field is incorrect.");
        assertEquals(response.getBody().jsonPath().getString("\"model.Username\"[0]"),
                "The Username or Password field is incorrect.");
    }


    @Test(dataProvider = "valid-auth-data-provider", dataProviderClass = AuthDataProvider.class)
    public void givenValidCredentialsWhenLoginThenReturn200(Map<String, String> params) {

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
