import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.BasePage;
import pageobjects.LoginPage;
import pageobjects.RecommendationsPage;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setup(){
        loginPage = new LoginPage();
    }

    @Test(priority = 0)
    public void testLoginFailed(){
        loginPage.login("nonexixtent@mail.com", "test");
        assertEquals(loginPage.getErrorText(), "Ви ввели невірний пароль або логін");
    }


    @Test(priority = 1)
    public void testLoginSucceeded(){
        loginPage.reload();
        BasePage page = loginPage.login(System.getProperty("username"), System.getProperty("password"));
        assertTrue(page instanceof RecommendationsPage && page.isLoaded());
    }

    @Override
    protected String getMainPageURI() {
        return LoginPage.getURI();
    }
}
