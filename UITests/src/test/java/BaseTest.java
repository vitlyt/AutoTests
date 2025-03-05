
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageobjects.*;
import static utils.Utils.*;


public abstract class BaseTest {

    private static WebDriver driver;

    @BeforeSuite
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // driver to be passed as external var
        BasePage.setDriver(driver);

    }

    @BeforeClass
    public void startTests(){
        String pageURI = getURI(getMainPageURI());
        if(!driver.getCurrentUrl().matches(pageURI)){
            driver.get(pageURI);
        }
    }

    protected void goBack(){
        driver.navigate().back();
    }

    protected abstract String getMainPageURI();


    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
