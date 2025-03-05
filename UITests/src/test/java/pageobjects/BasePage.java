package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public abstract class BasePage {

    private static WebDriver driver;


    public static void setDriver(WebDriver driver){
        BasePage.driver = driver;
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected List<WebElement> findList(By locator){
        return driver.findElements(locator);
    }

    protected void setText(By locator, String text){
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void reload(){
        driver.navigate().refresh();
    }

    protected void waitExplicitly(By locator, int secs){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitExplicitlyInvisibility(By locator, int secs){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitExplicitly(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean elementIsAbsent(By locator){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        return findList(locator).isEmpty();
    }


    abstract protected By getLoadedLocator();

    public boolean isLoaded() {
        waitExplicitly(getLoadedLocator(),2);
        return find(getLoadedLocator()).isDisplayed();
    }

}
