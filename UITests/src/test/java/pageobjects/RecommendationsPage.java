package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RecommendationsPage extends BasePage{

    private final By REC_PAGE_TAG_LOCATOR = By.tagName("alliance-recommended-page");
    private final By PROFILE_BUTTON_LOCATOR = By.xpath("//a[@href=\"/my/profile\"]");
    private final By RECOMMENDATIONS_BLOCK_LOCATOR = By.xpath("//alliance-recommended-vacancy-list/" + "div[@class='ng-star-inserted santa-min-h-0']");
    private static final String URI = "/my/recommendations\\?afterLogin=true";

    public MyProfilePage goToMyProfilePage(){
        WebElement profileButton = find(PROFILE_BUTTON_LOCATOR);
        profileButton.click();
        return new MyProfilePage();
    }

    public List<WebElement> getRecommendationBlocks(){
        waitExplicitly(RECOMMENDATIONS_BLOCK_LOCATOR,3);
        return findList(RECOMMENDATIONS_BLOCK_LOCATOR);
    }


    @Override
    protected By getLoadedLocator() {
        return REC_PAGE_TAG_LOCATOR;
    }

    public static String getURI() {
        return URI;
    }

}

