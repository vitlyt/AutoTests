import dataproviders.AuthDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.BasePage;
import pageobjects.LoginPage;
import pageobjects.RecommendationsPage;

import java.util.Map;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setup(){
        loginPage = new LoginPage();
    }

    @Test(priority = 0, dataProvider = "invalid-auth-data-provider", dataProviderClass = AuthDataProvider.class)
    public void testLoginFailed(Map<String, String> params){
        loginPage.login(params.get(AuthDataProvider.USERNAME_FIELD), AuthDataProvider.PASSWORD_FIELD);
        assertEquals(loginPage.getErrorText(), "Ви ввели невірний пароль або логін");
    }


    @Test(priority = 1, dataProvider = "valid-auth-data-provider", dataProviderClass = AuthDataProvider.class)
    public void testLoginSucceeded(Map<String, String> params){
        loginPage.reload();
        BasePage page = loginPage.login(params.get(AuthDataProvider.USERNAME_FIELD), AuthDataProvider.PASSWORD_FIELD);
        assertTrue(page instanceof RecommendationsPage && page.isLoaded());
    }

    @Override
    protected String getMainPageURI() {
        return LoginPage.getURI();
    }
}
