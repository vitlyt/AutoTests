package main;

import data.Resume;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;
import static utils.Utils.*;
import static utils.HTTP.*;
import static utils.SchemaProvider.*;
import static utils.MockDataProvider.*;
import static org.testng.Assert.*;

public class SeekerResumesTest extends BaseTest{

    private int id = 0;

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Test
    public void givenValidUserDataWhenGetSeekerResumesThenReturn200()  {

        Response response = getPostResponse("SeekerResumes", getDefaultURI(),
                getSeekerResumesMockData(), getSeekerResumesSchema());

        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertTrue(jsonPath.getInt("data.seekerResumes[0].similarVacanciesCount") > 0);
        assertEquals(jsonPath.getString("data.seekerResumes[0].personal.__typename"), "ResumePersonalInfo");
        assertEquals(jsonPath.getString("data.seekerResumes[0].city.__typename"), "City");
        assertEquals(jsonPath.getString("data.seekerResumes[0].resumeFilling.__typename"), "ResumeFilling");
        assertEquals(jsonPath.getString("data.seekerResumes[0].views.__typename"), "ResumeViewsConnection");
        assertEquals(jsonPath.getString("data.seekerResumes[0].state.privacySettings.__typename"), "ResumePrivacySettings");
        assertEquals(jsonPath.getString("data.seekerResumes[0].__typename"), "ProfResume");
        assertEquals(jsonPath.getString("data.seekerResumes[0].state.state"), "ACTIVE");
        assertEquals(jsonPath.getString("data.seekerResumes[0].state.availabilityState"), "PUBLIC");
        assertFalse(jsonPath.getBoolean("data.seekerResumes[0].state.isAnonymous"));
        assertFalse(jsonPath.getBoolean("data.seekerResumes[0].state.privacySettings.hasHiddenPhones"));
        assertFalse(jsonPath.getBoolean("data.seekerResumes[0].state.isBannedByModerator"));
        assertTrue(jsonPath.getList("data.seekerResumes[0].state.hiddenCompanies").isEmpty());

    }


    @Test(priority = 0)
    public void givenValidUserDataWhenCreateProfResumeAsCopyThenReturn200()  {

        Response response = getPostResponse("CreateProfResumeAsCopy", getDefaultURI(),
                getCreateProfResumeAsCopyMockData(), getCreateProfResumeAsCopySeekerSchema());
        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.createProfResumeAsCopy.errors"));
        assertEquals(jsonPath.getString("data.createProfResumeAsCopy.__typename"), "CreatedAsCopyProfResumeOutput");
        assertEquals(jsonPath.getString("data.createProfResumeAsCopy.profResume.__typename"), "ProfResume");
        assertTrue(matchesDigitGT0Format(jsonPath.getInt("data.createProfResumeAsCopy.profResume.id")));

        setId(jsonPath.getInt("data.createProfResumeAsCopy.profResume.id"));


    }


    @Test(priority = 1, dataProvider = "resume-data-provider", dataProviderClass = Resume.class)
    public void givenValidUserDataWhenUpdateSeekerProfResumePositionThenReturn200(Resume resume)  {

        Response response = getPostResponse("UpdateSeekerProfResumePosition", getDefaultURI(),
                getUpdateSeekerProfResumePositionMockData(getId(), resume.getPosition()), getUpdateSeekerProfResumePositionSchema());
        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.updateSeekerProfResumePosition.errors"));
        assertEquals(jsonPath.getString("data.updateSeekerProfResumePosition.__typename"), "UpdatedProfResumePositionOutput");

    }

    @Test(priority = 2, dataProvider = "resume-data-provider", dataProviderClass = Resume.class)
    public void givenValidUserDataWhenUpdateSeekerProfResumeScheduleThenReturn200(Resume resume)  {

        Response response = getPostResponse("UpdateSeekerProfResumeSchedule", getDefaultURI(),
                getUpdateSeekerProfResumeScheduleMockData(getId(), resume.getScheduleId1(), resume.getScheduleId2()), getUpdateSeekerProfResumeScheduleSchema());

        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.updateSeekerProfResumeSchedule.errors"));
        assertEquals(jsonPath.getString("data.updateSeekerProfResumeSchedule.__typename"), "UpdatedProfResumeScheduleOutput");


    }

    @Test(priority = 3, dataProvider = "resume-data-provider", dataProviderClass = Resume.class)
    public void givenValidUserDataWhenUpdateSeekerProfResumeSalaryThenReturn200(Resume resume) {

        Response response = getPostResponse("UpdateSeekerProfResumeSalary", getDefaultURI(),
                getUpdateSeekerProfResumeSalaryMockData(getId(), resume.getSalary(), resume.getCurrency()), getUpdateSeekerProfResumeSalarySchema());

        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.updateSeekerProfResumeSalary.errors"));
        assertEquals(jsonPath.getString("data.updateSeekerProfResumeSalary.__typename"), "UpdatedProfResumeSalaryOutput");


    }


    @Test(priority = 4, dataProvider = "resume-data-provider", dataProviderClass = Resume.class)
    public void givenValidUserDataWhenGetSeekerResumeThenReturn200(Resume resume) {


        Response response = getPostResponse("SeekerResume", getDefaultURI(),
                getSeekerResumeMockData(getId()), getSeekerResumeSchema());
        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();


        assertTrue(matchesDateFormat(jsonPath.getString("data.seekerResume.personal.birthDate")));
        assertTrue(jsonPath.getInt("data.seekerResume.personal.age") > 0);
        assertEquals(jsonPath.getString("data.seekerResume.personal.gender"), "MALE");
        assertEquals(jsonPath.getString("data.seekerResume.personal.photoUrl"), getPhotoURL());

        assertEquals(jsonPath.getString("data.seekerResume.contacts.phone.__typename"), "ResumePhone");
        assertTrue(matchesEmailFormat(jsonPath.getString("data.seekerResume.contacts.email")));
        assertTrue(matchesPhoneNumberFormat(jsonPath.getString("data.seekerResume.contacts.phone.value")));
        assertTrue(jsonPath.getBoolean("data.seekerResume.contacts.phone.isConfirmed"));
        assertEquals(jsonPath.getString("data.seekerResume.title"), resume.getPosition());
        assertEquals(jsonPath.getString("data.seekerResume.state.state"), "NOT_CREATED");
        assertEquals(jsonPath.getString("data.seekerResume.state.availabilityState"), "HIDE");
        assertNull(jsonPath.getString("data.seekerResume.state.banInfo.banReason"));
        assertEquals(jsonPath.getString("data.seekerResume.state.searchState"), "ACTIVE");
        assertFalse(jsonPath.getBoolean("data.seekerResume.state.isAllowedToShareWithPartners"));
        assertFalse(jsonPath.getBoolean("data.seekerResume.state.privacySettings.hasHiddenPhones"));
        assertTrue(jsonPath.getString("data.seekerResume.skills").isEmpty());

        assertEquals(jsonPath.getList("data.seekerResume.schedules").size(), 2);
        assertEquals(jsonPath.getString("data.seekerResume.schedules[0].id"), String.valueOf(resume.getScheduleId1()));
        assertEquals(jsonPath.getString("data.seekerResume.schedules[1].id"), String.valueOf(resume.getScheduleId2()));

        assertEquals(jsonPath.getInt("data.seekerResume.salary.amount"), resume.getSalary());
        assertEquals(jsonPath.getString("data.seekerResume.salary.currency"), resume.getCurrency());

        assertFalse(jsonPath.getBoolean("data.seekerResume.state.isAnonymous"));
        assertTrue(jsonPath.getList("data.seekerResume.state.hiddenCompanies").isEmpty());

    }

    @Test
    public void givenInvalidUserDataWhenGetSeekerResumeThenReturnError() {


        Response response = getPostResponse("SeekerResume", getDefaultURI(),
                getSeekerResumeMockData(0), getInvalidUserDataSeekerResumeSchema());
        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertEquals(jsonPath.getString("errors[0].message"), "ProfResume 0 was not found");
        assertEquals(jsonPath.getString("errors[0].path[0]"), "seekerResume");
        assertNull(jsonPath.get("data"));

    }


    @Test(priority = 5)
    public void givenValidUserDataWhenDeleteSeekerProfResumeThenReturn200()  {

        Response response = getPostResponse("DeleteSeekerProfResume", getDefaultURI(),
                getDeleteSeekerProfResumeMockData(getId()), getDeleteSeekerProfResumeSchema());

        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertNull(jsonPath.getString("data.deleteSeekerProfResume.errors"));
        assertEquals(jsonPath.getString("data.deleteSeekerProfResume.__typename"), "DeletedProfResumeOutput");

    }

    @Test(priority = 6)
    public void givenInvalidUserDataWhenDeleteSeekerProfResumeThenReturn200() {

        Response response = getPostResponse("DeleteSeekerProfResume", getDefaultURI(),
                getDeleteSeekerProfResumeMockData(getId()), getDeleteSeekerProfResumeSchema());
        assertEquals(response.statusCode(), Status.OK);

        JsonPath jsonPath = response.getBody().jsonPath();

        assertEquals(jsonPath.getString("data.deleteSeekerProfResume.errors[0].__typename"), "ProfResumeDoesNotExist");

    }




}
