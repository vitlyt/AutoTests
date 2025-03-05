import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MyProfilePage;
import pageobjects.RecommendationsPage;
import static org.testng.Assert.*;

public class RecommendationsTest extends BaseTest{

    private RecommendationsPage recommendationsPage;

    @BeforeClass
    public void setup(){
        recommendationsPage = new RecommendationsPage();
    }

    @Test(priority = 0)
    public void testRecommendationsArePresent(){
        assertTrue(recommendationsPage.getRecommendationBlocks().size() > 1);
    }

    @Test(priority = 1)
    public void testMyProfilePageLoaded(){
        MyProfilePage myProfilePage = recommendationsPage.goToMyProfilePage();
        assertTrue(myProfilePage.isLoaded());
    }

    @Override
    protected String getMainPageURI() {
        return RecommendationsPage.getURI();
    }
}
