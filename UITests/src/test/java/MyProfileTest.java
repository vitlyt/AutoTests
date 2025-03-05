import data.Resume;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.CreateResumePage;
import pageobjects.MyProfilePage;
import pageobjects.RecommendationsPage;

import java.util.List;

import static org.testng.Assert.*;
import static utils.Utils.normalizeStr;
import static utils.Utils.parseInt;

public class MyProfileTest extends BaseTest {

    private CreateResumePage createResumePage;
    private MyProfilePage myProfilePage;

    @Override
    protected String getMainPageURI() {
        return MyProfilePage.getURI();
    }


    @BeforeClass
    public void setUp() {
        myProfilePage = new MyProfilePage();
    }

    @Test(priority = 0)
    public void testCreateResumePageOpened(){
        createResumePage = myProfilePage.clickOnCreateResumeButtonAndGoToCreateResumePage();
        assertTrue(createResumePage.isLoaded());
    }

    @Test(priority = 1)
    public void testCreateResumeFailed(){
        createResumePage.clickAddDesiredPosition();
        createResumePage.setDesiredPosition("a");
        createResumePage.setDesiredEmploymentType("");
        assertTrue(createResumePage.positionIsInvalid());
        createResumePage.savePositionData();
        assertEquals(createResumePage.getEmploymentTypeErrorText(), "Виберіть тип зайнятості");
        createResumePage.cancelPositionData();
    }

    @Test(priority = 2, dataProvider = "resume-data-provider", dataProviderClass = Resume.class)
    public void testResumeCreated(Resume resume){
        createResumePage.clickAddDesiredPosition();
        createResumePage.setDesiredPosition(resume.getPosition());
        createResumePage.setDesiredSalary(resume.getSalary());
        createResumePage.setDesiredEmploymentType(resume.getEmploymentType());
        createResumePage.savePositionData();
        createResumePage.saveAllData();
        assertTrue(createResumePage.dataIsSaved());
        assertEquals(createResumePage.getDesiredPosition(), resume.getPosition());
        assertEquals(parseInt(createResumePage.getDesiredSalary()), resume.getSalary());
        assertEquals(normalizeStr(createResumePage.getDesiredEmploymentType()), resume.getEmploymentType().toLowerCase());
    }

    @Test(priority = 3, dataProvider = "resume-data-provider", dataProviderClass = Resume.class)
    public void testResumesArePresent(Resume resume){
        goBack();
        assertTrue(myProfilePage.isLoaded());
        List<String> titles = myProfilePage.getResumeTitles();
        assertTrue(titles.contains(resume.getPosition()));

    }

    @Test(priority = 4, dataProvider = "resume-data-provider", dataProviderClass = Resume.class)
    public void testResumeRemoved(Resume resume){
        myProfilePage.clickOnRemoveResume(resume.getPosition());
        assertTrue(myProfilePage.resumeIsRemoved());
        List<String> titles = myProfilePage.getResumeTitles();
        assertFalse(titles.contains(resume.getPosition()));

    }

    @Test(priority = 5)
    public void testLogoutSuccessful(){
        RecommendationsPage recommendationsPage = myProfilePage.logout();
        assertTrue(recommendationsPage.isLoaded());
    }

}
