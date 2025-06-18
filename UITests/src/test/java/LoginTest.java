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

    @Test(dataProvider = "auth-data-provider", dataProviderClass = AuthDataProvider.class)
    public void testLogin(Map<String, String> params){
        BasePage page = loginPage.login(params.get(AuthDataProvider.USERNAME_FIELD), params.get(AuthDataProvider.PASSWORD_FIELD));

        if(Boolean.parseBoolean(params.get(AuthDataProvider.IS_VALID_FIELD)))
            assertTrue(page instanceof RecommendationsPage && page.isLoaded());
        else
            assertEquals(loginPage.getErrorText(), "Ви ввели невірний пароль або логін");
    }

    @Override
    protected String getMainPageURI() {
        return LoginPage.getURI();
    }
}
