package main;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;


import static org.testng.Assert.*;
import static utils.SchemaProvider.*;
import static utils.Utils.*;
import static utils.MockDataProvider.*;
import static utils.HTTP.*;

public class SearchVacanciesTest extends BaseTest{

    @Test
    public void givenRequestBodyWhenGetPublishedVacanciesListThenReturn200Error()  {

        Response response = getPostResponse("getPublishedVacanciesList", getDefaultURI(),
                getPublishedVacanciesListMockData(), getPublishedVacanciesListSchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertFalse(jsonPath.getList("data.publishedVacancies.items").isEmpty(), "data.publishedVacancies is empty");
        assertTrue(jsonPath.getInt("data.publishedVacancies.totalCount") > 0, "data.publishedVacancies.totalCount is less equal than 0");

    }

    @Test
    public void givenRequestBodyWhenGetPublishedVacancyThenReturn200Error()  {

        Response response = getPostResponse("getPublishedVacancy", getDefaultURI(),
                getPublishedVacancyMockData(), getPublishedVacancySchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertTrue(jsonPath.getInt("data.publishedVacancy.id") > 0, "data.publishedVacancy.id is less equal than 0");
        assertFalse(jsonPath.getString("data.publishedVacancy.title").isEmpty(), "data.publishedVacancy.title is empty");

    }

}
