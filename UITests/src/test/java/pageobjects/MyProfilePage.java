package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class MyProfilePage extends BasePage{


    private final By CREATE_RESUME_BUTTON = By.xpath("//span[contains(text(), 'Створити резюме')]/ancestor::button");
    private final By NEW_RESUME_BUTTON = By.xpath("//button[contains(text(), 'Нове резюме')]");
    private static final String URI = "/my/profile";


    private final By RESUME_CARD_LOCATOR_ROOT = By.xpath("//alliance-jobseeker-resume-list-desktop//alliance-resume-card-desktop");
    private final By RESUME_P_TITLE = By.xpath(".//p[@class='resume-title']");

    private final By VERTICAL_DOTS_MENU_BUTTON = By.xpath("./parent::div/following-sibling::div//santa-vertical-dots-menu");
    private final By REMOVE_RESUME_BUTTON = By.xpath("//span[contains(text(), 'Видалити')]");
    private static final String POSITION_BLOCK_LOCATOR = "//p[@class='resume-title'][contains(text(), '%s')]";
    private final By RESUME_REMOVED_LABEL = By.xpath("//p[contains(text(), 'Резюме видалено')]");
    private final By LOGOUT_SPAN = By.xpath("//span[contains(text(), 'Вийти з акаунту')]");



    @Override
    protected By getLoadedLocator() {
        return CREATE_RESUME_BUTTON;
    }



    public CreateResumePage clickOnCreateResumeButtonAndGoToCreateResumePage(){
        find(CREATE_RESUME_BUTTON).click();
        waitExplicitly(NEW_RESUME_BUTTON, 2);
        find(NEW_RESUME_BUTTON).click();
        return new CreateResumePage();
    }


    public void clickOnRemoveResume(String position){

        WebElement positionBlockLocator = find(buildPositionBlockLocator(position));

        positionBlockLocator.findElement(VERTICAL_DOTS_MENU_BUTTON).click();

        waitExplicitly(REMOVE_RESUME_BUTTON);
        find(REMOVE_RESUME_BUTTON).click();
        waitExplicitly(REMOVE_RESUME_BUTTON);
        find(REMOVE_RESUME_BUTTON).click();

    }

    public boolean resumeIsRemoved(){
        waitExplicitly(RESUME_REMOVED_LABEL);
        return find(RESUME_REMOVED_LABEL).isDisplayed();
    }

    public List<String> getResumeTitles() {

        List<WebElement> resumes = findList(RESUME_CARD_LOCATOR_ROOT);

        return resumes.stream().map(resume -> resume.findElement(RESUME_P_TITLE).getText())
                .map(Utils::normalizeStr).collect(Collectors.toList());
    }

    public RecommendationsPage logout()  {
       find(LOGOUT_SPAN).click();
       return new RecommendationsPage();
    }


    private static By buildPositionBlockLocator(String position){
        return By.xpath(String.format(POSITION_BLOCK_LOCATOR, position));
    }


    public static String getURI() {
        return URI;
    }
}
