package main;

import dataproviders.ResumeDataProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import mapping.*;
import org.testng.annotations.Test;


import java.util.Arrays;
import java.util.Map;

import static org.testng.Assert.assertTrue;
import static utils.Utils.*;
import static utils.HTTP.*;
import static utils.SchemaProvider.*;
import static utils.MockDataProvider.*;
import static org.testng.Assert.*;

public class SeekerResumesTest extends BaseTest{

    @Test(dataProvider = "resumes-data-provider", dataProviderClass = ResumeDataProvider.class)
    public void givenValidUserDataWhenGetSeekerResumesThenReturn200(SeekerResume expectedSeekerResume)  {

        SeekerResume[] seekerResumes = (SeekerResume[])getPostResponse("SeekerResumes", getDefaultURI(),
                getSeekerResumesMockData(), getSeekerResumesSchema(),
                Status.OK, SeekerResume[].class, "data.seekerResumes");

        SeekerResume actualResume = seekerResumes[0];

        assertEquals(actualResume, expectedSeekerResume);

    }


    @Test(priority = 0)
    public void givenValidUserDataWhenCreateProfResumeAsCopyThenReturn200()  {
        Map<String, String> data = getCreateProfResumeAsCopyMockData();
        ProfResumeRequest createProfResumeAsCopy = new ProfResumeRequest(new Input(data.get("resumeId")),
               data.get(OPERATION_NAME_PROPERTY), data.get(QUERY_PROPERTY));

        Response response = getPostResponse(data.get(OPERATION_NAME_PROPERTY), getDefaultURI(),
                createProfResumeAsCopy, getCreateProfResumeAsCopySeekerSchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath().setRootPath("data.createProfResumeAsCopy");

        assertNull(jsonPath.getString("errors"));
        assertEquals(jsonPath.getString("__typename"), "CreatedAsCopyProfResumeOutput");
        jsonPath.setRootPath("data.createProfResumeAsCopy.profResume");
        assertEquals(jsonPath.getString("__typename"), "ProfResume");
        assertTrue(matchesDigitGT0Format(jsonPath.getInt("id")));
        ResumeDataProvider.setId(jsonPath.getInt("id"));
    }


    @Test(priority = 1, dataProvider = "resume-data-provider", dataProviderClass = ResumeDataProvider.class)
    public void givenValidUserDataWhenUpdateSeekerProfResumePositionThenReturn200(DetailedSeekerResume seekerResume, Integer id)  {
        Map<String, String> data = getUpdateSeekerProfResumePositionMockData();
        UpdateSeekerProfResumePositionRequest updateSeekerProfResumePosition = new UpdateSeekerProfResumePositionRequest(
                new UpdateSeekerProfResumePositionRequest.PositionInput(String.valueOf(id), seekerResume.getTitle()),
                data.get(OPERATION_NAME_PROPERTY), data.get(QUERY_PROPERTY)
        );

        Response response = getPostResponse(data.get(OPERATION_NAME_PROPERTY), getDefaultURI(),
                updateSeekerProfResumePosition, getUpdateSeekerProfResumePositionSchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.updateSeekerProfResumePosition.errors"));
        assertEquals(jsonPath.getString("data.updateSeekerProfResumePosition.__typename"), "UpdatedProfResumePositionOutput");

    }

    @Test(priority = 2, dataProvider = "resume-data-provider", dataProviderClass = ResumeDataProvider.class)
    public void givenValidUserDataWhenUpdateSeekerProfResumeScheduleThenReturn200(DetailedSeekerResume seekerResume, Integer id)  {
        Map<String, String> data = getUpdateSeekerProfResumeScheduleMockData();
        UpdateSeekerProfResumeScheduleRequest updateSeekerProfResumeSchedule = new UpdateSeekerProfResumeScheduleRequest(
                new UpdateSeekerProfResumeScheduleRequest.SchedulesInput(String.valueOf(id),
                        Arrays.asList(seekerResume.getSchedules().get(0).getId(), seekerResume.getSchedules().get(1).getId())),
                data.get(OPERATION_NAME_PROPERTY),
                data.get(QUERY_PROPERTY)

        );

        Response response = getPostResponse(data.get(OPERATION_NAME_PROPERTY), getDefaultURI(),
                updateSeekerProfResumeSchedule, getUpdateSeekerProfResumeScheduleSchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.updateSeekerProfResumeSchedule.errors"));
        assertEquals(jsonPath.getString("data.updateSeekerProfResumeSchedule.__typename"), "UpdatedProfResumeScheduleOutput");

    }

    @Test(priority = 3, dataProvider = "resume-data-provider", dataProviderClass = ResumeDataProvider.class)
    public void givenValidUserDataWhenUpdateSeekerProfResumeSalaryThenReturn200(DetailedSeekerResume seekerResume, Integer id) {
        Map<String, String> data = getUpdateSeekerProfResumeSalaryMockData();
        UpdateSeekerProfResumeSalaryRequest updateSeekerProfResumeSalary = new UpdateSeekerProfResumeSalaryRequest(
                new UpdateSeekerProfResumeSalaryRequest.SalaryInput(String.valueOf(id),
                        new UpdateSeekerProfResumeSalaryRequest.SalaryInput.Salary(seekerResume.getSalary().getAmount(),
                                seekerResume.getSalary().getCurrency())),
                data.get(OPERATION_NAME_PROPERTY),
                data.get(QUERY_PROPERTY)

        );

        Response response = getPostResponse(data.get(OPERATION_NAME_PROPERTY), getDefaultURI(),
                updateSeekerProfResumeSalary, getUpdateSeekerProfResumeSalarySchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.updateSeekerProfResumeSalary.errors"));
        assertEquals(jsonPath.getString("data.updateSeekerProfResumeSalary.__typename"), "UpdatedProfResumeSalaryOutput");

    }


    @Test(priority = 4, dataProvider = "resume-data-provider", dataProviderClass = ResumeDataProvider.class)
    public void givenValidUserDataWhenGetSeekerResumeThenReturn200(DetailedSeekerResume expectedSeekerResume, Integer id) {
        DetailedSeekerResume actualSeekerResume = (DetailedSeekerResume)getPostResponse("SeekerResume", getDefaultURI(),
                getSeekerResumeMockData(id), getSeekerResumeSchema(),
                Status.OK, DetailedSeekerResume.class, "data.seekerResume");

        assertEquals(actualSeekerResume, expectedSeekerResume);
    }

    @Test
    public void givenInvalidUserDataWhenGetSeekerResumeThenReturnError() {
        Response response = getPostResponse("SeekerResume", getDefaultURI(),
                getSeekerResumeMockData(0), getInvalidUserDataSeekerResumeSchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertEquals(jsonPath.getString("errors[0].message"), "ProfResume 0 was not found");
        assertEquals(jsonPath.getString("errors[0].path[0]"), "seekerResume");
        assertNull(jsonPath.get("data"));

    }


    @Test(priority = 5, dataProvider = "resume-data-provider-id", dataProviderClass = ResumeDataProvider.class)
    public void givenValidUserDataWhenDeleteSeekerProfResumeThenReturn200(Integer id)  {
        Map<String, String> data = getDeleteSeekerProfResumeMockData();
        ProfResumeRequest deleteProfResume = new ProfResumeRequest(new Input(String.valueOf(id)),
        data.get(OPERATION_NAME_PROPERTY), data.get(QUERY_PROPERTY));
        Response response = getPostResponse(data.get(OPERATION_NAME_PROPERTY), getDefaultURI(),
                deleteProfResume, getDeleteSeekerProfResumeSchema(), Status.OK);
        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.deleteSeekerProfResume.errors"));
        assertEquals(jsonPath.getString("data.deleteSeekerProfResume.__typename"), "DeletedProfResumeOutput");

    }

    @Test(priority = 6, dataProvider = "resume-data-provider-id", dataProviderClass = ResumeDataProvider.class)
    public void givenInvalidUserDataWhenDeleteSeekerProfResumeThenReturn200(Integer id) {

        Map<String, String> data = getDeleteSeekerProfResumeMockData();
        ProfResumeRequest deleteProfResume = new ProfResumeRequest(new Input(String.valueOf(id)),
                data.get(OPERATION_NAME_PROPERTY), data.get(QUERY_PROPERTY));

        Response response = getPostResponse("DeleteSeekerProfResume", getDefaultURI(),
                deleteProfResume, getDeleteSeekerProfResumeSchema(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertEquals(jsonPath.getString("data.deleteSeekerProfResume.errors[0].__typename"), "ProfResumeDoesNotExist");

    }

}
