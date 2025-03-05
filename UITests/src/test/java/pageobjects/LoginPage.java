package pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private final By LOGIN_PAGE_LOCATOR = By.xpath("//p[text()=\"Увійдіть на сайт\"]");
    private final By EMAIL_FIELD_LOCATOR = By.id("otp-username");
    private final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@type='password']");
    private final By LOGIN_BUTTON_LOCATOR = By.xpath("//div[contains(text(), 'Увійти')]//parent::button[@type='button']");

    private final By INVALID_LOGIN_OR_PASSWORD_LABEL = By.xpath("//div//santa-error-msg/following-sibling::p");
    private static final String URI = "/auth/login";

    private String errorText;

    public String getErrorText() {
        return errorText;
    }


    public BasePage login(String email, String password)  {
        errorText = null;
        setText(EMAIL_FIELD_LOCATOR, email);

        waitExplicitly(PASSWORD_FIELD_LOCATOR);
        setText(PASSWORD_FIELD_LOCATOR, password);

        WebElement loginButton = find(LOGIN_BUTTON_LOCATOR);
        loginButton.click();

        if(elementIsAbsent(INVALID_LOGIN_OR_PASSWORD_LABEL)){
           return new RecommendationsPage();
        }
        errorText = find(INVALID_LOGIN_OR_PASSWORD_LABEL).getText();
        return this;

    }

    public static String getURI() {
        return URI;
    }

    @Override
    protected By getLoadedLocator() {
        return LOGIN_PAGE_LOCATOR;
    }
}
