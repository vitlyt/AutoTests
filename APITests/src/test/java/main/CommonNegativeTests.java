package main;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static utils.Utils.*;
import static utils.MockDataProvider.*;
import static utils.SchemaProvider.getErrorSchema;
import static utils.HTTP.*;

public class CommonNegativeTests extends BaseTest {

    @Test
    public void givenInValidOperationNameWhenPostRequestThenReturn500Error() {
        Response response = getPostResponse(getDefaultURI(),
                getInvalidOperationMockData(), getErrorSchema(), Status.INTERNAL_SERVER_ERROR);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertEquals(jsonPath.getString("errors[0].message"), "Unknown operation named \"test\".");
        assertEquals(jsonPath.getString("errors[0].extensions.code"), "INVALID_GRAPHQL");

    }

    @Test
    public void givenInValidBodyQueryWhenPostRequestThenReturn400Error()  {
        Response response = getPostResponse(getDefaultURI(),
                getInvalidQueryMockData(), getErrorSchema(), Status.BAD_REQUEST);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertEquals(jsonPath.getString("errors[0].message"), "Syntax Error: Unexpected Name \"mutatio\".");
        assertFalse(jsonPath.getList("errors[0].locations").isEmpty());
        assertEquals(jsonPath.getString("errors[0].extensions.code"), "GRAPHQL_PARSE_FAILED");
    }

    @Test
    public void givenEmptyBodyWhenPostRequestThenReturn400Error()   {
        Response response = getPostResponse(getDefaultURI(),
                "", getErrorSchema(), Status.BAD_REQUEST);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertEquals(jsonPath.getString("errors[0].message"), "POST body missing, invalid Content-Type, or JSON object has no keys.");
        assertEquals(jsonPath.getString("errors[0].extensions.code"), "BAD_REQUEST");
    }
}
