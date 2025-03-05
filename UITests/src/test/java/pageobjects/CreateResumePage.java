package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static utils.Utils.*;

import java.util.List;

public class CreateResumePage extends BasePage {

    private static final String URI_PATTERN = "/my/resumes/\\d+";
    private final By CV_BUILDER_LOCATOR = By.tagName("alliance-cv-builder-page");
    private final By DESIRED_POSITION_INPUT = By.xpath("//label[contains(text(), 'Ким хочете працювати')]/ancestor::div[@class='outline']/preceding-sibling::input");
    private final By DESIRED_SALARY_INPUT = By.xpath("//label[contains(text(), 'Скільки хочете заробляти')]/ancestor::div[@class='outline']/preceding-sibling::input");

    private final By DESIRED_EMPLOYMENT_TYPE_INPUTS = By.xpath("//h3[contains(text(), 'Тип зайнятості')]/following-sibling::lib-cv-schedules//santa-checkbox");
    private final By DESIRED_EMPLOYMENT_TYPE_INPUT = By.xpath(".//input[@type='checkbox']");
    private final By DESIRED_EMPLOYMENT_TYPE_INPUT_SPAN = By.xpath(".//span[@class='santa-typo-regular']");
    private final By SAVE_POSITION_BUTTON = By.xpath("//span[contains(text(), 'Зберегти')]/ancestor::button");
    private final By CANCEL_POSITION_BUTTON = By.xpath("//span[contains(text(), 'Скасувати')]/ancestor::button");
    private final By SAVE_ALL_BUTTON = By.xpath("//button[contains(text(), 'Зберегти')]");
    private final By RESUME_SAVED_LABEL = By.xpath("//p[contains(text(), 'Резюме збережено')]");
    private final String DESIRED_POSITION_LABEL_ROOT = "//h3[contains(text(), 'Бажана посада')]/parent::div/following-sibling::";
    private final By DESIRED_POSITION_LABEL = By.xpath(DESIRED_POSITION_LABEL_ROOT.concat("div/div[1]"));
    private final By DESIRED_SALARY_LABEL = By.xpath(DESIRED_POSITION_LABEL_ROOT.concat("div/div[2]//span[1]"));
    private final By DESIRED_EMPLOYMENT_TYPE_LABEL = By.xpath(DESIRED_POSITION_LABEL_ROOT.concat("div/div[3]//span"));
    private final By ADD_DESIRED_POSITION_BUTTON = By.xpath(DESIRED_POSITION_LABEL_ROOT.concat("santa-button"));

    private final By POSITION_RED_INPUT = By.xpath("//form/santa-suggest-input[contains(@class, 'ng-invalid')]");
    private final By ERROR_BLOCK = By.xpath("//div/div[@class='error-block ng-star-inserted']");

    @Override
    protected By getLoadedLocator() {
        return CV_BUILDER_LOCATOR;
    }

    public void clickAddDesiredPosition(){
        waitExplicitly(ADD_DESIRED_POSITION_BUTTON);
        find(ADD_DESIRED_POSITION_BUTTON).click();
        waitExplicitly(DESIRED_POSITION_INPUT);
    }

    public boolean positionIsInvalid(){
        waitExplicitly(POSITION_RED_INPUT);
        return find(POSITION_RED_INPUT).isDisplayed();
    }

    public String getEmploymentTypeErrorText(){
        return find(ERROR_BLOCK).getText();
    }

    public void setDesiredPosition(String position){
        setText(DESIRED_POSITION_INPUT, position + Keys.ENTER);
    }

    public void setDesiredSalary(int salary){
        setText(DESIRED_SALARY_INPUT, String.valueOf(salary));
    }

    public void setDesiredEmploymentType(String employmentType){

        List<WebElement> checkboxes = findList(DESIRED_EMPLOYMENT_TYPE_INPUTS);
        checkboxes.forEach(checkbox -> {

            String text = normalizeStr(checkbox.findElement(DESIRED_EMPLOYMENT_TYPE_INPUT_SPAN).getText());
            boolean isSelected = checkbox.findElement(DESIRED_EMPLOYMENT_TYPE_INPUT).isSelected();

            if(text.equals(employmentType) && !isSelected || isSelected) {
                checkbox.click();
            }

        });

    }
    

    public void savePositionData(){
        find(SAVE_POSITION_BUTTON).click();
    }

    public void cancelPositionData(){
        find(CANCEL_POSITION_BUTTON).click();
    }

    public void saveAllData(){
        find(SAVE_ALL_BUTTON).click();
    }

    public boolean dataIsSaved(){
        waitExplicitly(RESUME_SAVED_LABEL, 2);
        waitExplicitlyInvisibility(DESIRED_POSITION_INPUT, 2);
        return elementIsAbsent(DESIRED_POSITION_INPUT);
    }

    public String getDesiredPosition(){
        return find(DESIRED_POSITION_LABEL).getText();
    }

    public String getDesiredSalary(){
        return find(DESIRED_SALARY_LABEL).getText();
    }

    public String getDesiredEmploymentType(){
        return find(DESIRED_EMPLOYMENT_TYPE_LABEL).getText();
    }


    public static String getURI() {
        return URI_PATTERN;
    }
}
